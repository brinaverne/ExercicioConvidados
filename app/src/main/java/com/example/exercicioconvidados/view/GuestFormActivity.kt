package com.example.exercicioconvidadosroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.exercicioconvidadosroom.model.Guest
import com.example.exercicioconvidadosroom.R
import com.example.exercicioconvidadosroom.constants.DataBaseConstants
import com.example.exercicioconvidadosroom.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity() {

    lateinit var btnSave: Button
    lateinit var viewModel: GuestFormViewModel
    lateinit var radioPresent: RadioButton
    lateinit var radioAbsent: RadioButton
    lateinit var editName: EditText
    var guest: Guest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)
        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)


        editName = findViewById(R.id.edit_name)
        radioPresent = findViewById(R.id.radio_present)
        radioAbsent = findViewById(R.id.radio_absent)
        btnSave = findViewById(R.id.button_save)


        radioPresent.isChecked = true


        var idUpdate: Int? = null
        if (intent.hasExtra(DataBaseConstants.Guest.Columns.ID)){
            idUpdate = intent.getIntExtra(DataBaseConstants.Guest.Columns.ID, 0)
        }

        if(idUpdate != null){
            viewModel.selectItemForUpdate(idUpdate)

        }

        viewModel.guests.observe(this@GuestFormActivity){
            guest = it
            editName.setText(it.nome)
            btnSave.text = "Update"
            if (it.presence) radioPresent.isChecked = true else radioAbsent.isChecked = true
        }


        btnSave.setOnClickListener {
            if (editName.text.toString() != ""){
                val name = editName.text.toString()
                val presence = radioPresent.isChecked

                if (guest?.id != null){
                    guest?.nome = name
                    guest?.presence = presence
                    viewModel.update(guest!!)

                }else{
                    val model = Guest().apply {
                        this.id = 0
                        this.nome = name
                        this.presence = presence
                    }

                    viewModel.insert(model)
                }


                Toast.makeText(this, "Convidado cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                editName.setText("")

            }else{
                editName.error = "Informe seu nome!"
            }
        }

    }
}