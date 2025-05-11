package com.example.exercicioconvidadosroom.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercicioconvidadosroom.R
import com.example.exercicioconvidadosroom.constants.DataBaseConstants
import com.example.exercicioconvidadosroom.databinding.FragmentAllGuestsBinding
import com.example.exercicioconvidadosroom.model.Guest
import com.example.exercicioconvidadosroom.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recyclerAllGuests: RecyclerView
    val allGuestsViewModel by lazy { ViewModelProvider(this).get(AllGuestsViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerAllGuests = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
        recyclerAllGuests.layoutManager = LinearLayoutManager(context)
        /**recyclerAllGuests.adapter = GuestsAdapter(listOf())**/




        allGuestsViewModel.getAll()

        allGuestsViewModel.guests.observe(viewLifecycleOwner) {
            recyclerAllGuests.adapter = object: GuestsAdapter(it, requireContext()){
                override fun deleteGuest(guestId: Int) {
                    allGuestsViewModel.delete(guestId)
                    allGuestsViewModel.getAll()
                }
                override fun updateGuest(guestId:Int){
                    var intent = Intent(context, GuestFormActivity::class.java)
                    intent.putExtra(DataBaseConstants.Guest.Columns.ID, guestId)
                    startActivity(intent)
                }
            }
            for (x in it){
                Log.v("retorno_sql", "${x.id} ${x.nome} ${x.presence}")
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.getAll()
    }
}

abstract class GuestsAdapter(guestList: List<Guest>, context: Context): RecyclerView.Adapter<GuestsViewHolder>() {

    private val list = guestList
    private val contextlixo = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val cardview = LayoutInflater.from(parent.context).inflate(R.layout.cardview_guests, parent, false)
        return GuestsViewHolder(cardview)

    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        var presence = list.get(position).presence
        holder.textGuestName.text = list.get(position).nome

        if (presence) holder.textGuestPresence.text = "Presente" else holder.textGuestPresence.text = "Ausente"

        holder.cardviewGuest.setOnClickListener {
            updateGuest(list.get(position).id)
        }

        holder.cardviewGuest.setOnLongClickListener{
            openAlertDialogRemove(list.get(position).id)

            true
        }


    }

    fun openAlertDialogRemove(guestId:Int){

        val dialog = AlertDialog.Builder(contextlixo)
            .setTitle("Deseja remover o convidado?")
            .setNegativeButton("Não", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                }
            })
            .setPositiveButton("Sim", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    deleteGuest(guestId)
                }

            })
            .setCancelable(true) // permite fechar clicando fora
            .create()



        dialog.show()
    }

    abstract fun deleteGuest(guestId:Int)

    abstract  fun updateGuest(guestId:Int)



}

class GuestsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    var textGuestName: TextView
    var textGuestPresence: TextView
    var cardviewGuest: CardView
    init {
        textGuestName = itemView.findViewById<TextView>(R.id.text_cardview_guest_name)
        textGuestPresence = itemView.findViewById<TextView>(R.id.text_cardview_guest_presence)
        cardviewGuest = itemView.findViewById<CardView>(R.id.cardview_guests)
    }



}