package com.example.exercicioconvidadosroom.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.exercicioconvidadosroom.constants.DataBaseConstants
import com.example.exercicioconvidadosroom.model.Guest

class GuestFormRepository(context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

    /**
    companion object{

        private lateinit var repository: GuestFormRepository

        fun getInstance(context: Context): GuestFormRepository {
            if (!Companion::repository.isInitialized){
                repository = GuestFormRepository(context)
            }
            return repository
        }

    }
    */

    fun insert(guest: Guest){
        guestDataBase.insert(guest)
        /**
        val db = guestDataBase.writableDatabase

        val presence = if (guest.presence) 1 else 0

        val values = ContentValues()
        values.put(DataBaseConstants.Guest.Columns.NAME, guest.nome)
        values.put(DataBaseConstants.Guest.Columns.PRESENCE, presence)

        db.insert(DataBaseConstants.Guest.TABLE_NAME, null, values)*/
    }

    fun update(guest:Guest){
        guestDataBase.update(guest)
        /**
        if (guest != null){
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.Guest.Columns.NAME, guest.nome)
            values.put(DataBaseConstants.Guest.Columns.PRESENCE, presence)

            // onde id = guest.id
            val selection = "${DataBaseConstants.Guest.Columns.ID} = ?"
            val args = arrayOf(guest?.id.toString())

            db.update(DataBaseConstants.Guest.TABLE_NAME, values, selection, args)*/
        }



    @SuppressLint("Range")
    fun selectItemForUpdate(guestId: Int?): Guest{
        return guestDataBase.selectItemForUpdate(guestId)
        /**
        val db = guestDataBase.readableDatabase
        var guest:Guest? = null

        var querySql = "select * from ${DataBaseConstants.Guest.TABLE_NAME}"
        var queryWhere = ""
        if (guestId != null){
            queryWhere = " where ${DataBaseConstants.Guest.Columns.ID} = $guestId limit 1"
        }

        val selection = arrayOf(
            DataBaseConstants.Guest.Columns.ID,
            DataBaseConstants.Guest.Columns.NAME,
            DataBaseConstants.Guest.Columns.PRESENCE
        )

        var cursor = db.rawQuery(querySql.plus(queryWhere), arrayOf())
        // var cursor = db.query(false,DataBaseConstants.Guest.TABLE_NAME, selection, null, null, null, null, null, null, null)

        try {
            if (cursor != null && cursor.count > 0){
                if (cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.Columns.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.Columns.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.Columns.PRESENCE))

                    guest = Guest(id, name, presence == 1)

                }
            }
            cursor.close()

        }
        catch (t: Throwable) {
            Log.e("ERRO_SQL", "Erro: ${t.message}", t)
            cursor.close()
        }
        return guest*/
    }

    fun delete(guestId:Int){
        val guest: Guest
        guest = selectItemForUpdate(guestId)
        guestDataBase.delete(guest)
        /**
        val db = guestDataBase.writableDatabase


        // onde id = guest.id
        val selection = "${DataBaseConstants.Guest.Columns.ID} = ?"
        val args = arrayOf(guestId.toString())

        db.delete(DataBaseConstants.Guest.TABLE_NAME, selection, args)
        */
    }

    @SuppressLint("Range")
    fun select(guestPresence: Boolean): MutableList<Guest>{
        return guestDataBase.select(guestPresence)
        /**
        val db = guestDataBase.readableDatabase
        var guestList = mutableListOf<Guest>()

        var querySql = "select * from ${DataBaseConstants.Guest.TABLE_NAME}"
        var queryWhere = ""
        if (guestPresence != null){
            queryWhere = " where ${DataBaseConstants.Guest.Columns.PRESENCE} = ${if (guestPresence) 1 else 0}"
        }

        val selection = arrayOf(
            DataBaseConstants.Guest.Columns.ID,
            DataBaseConstants.Guest.Columns.NAME,
            DataBaseConstants.Guest.Columns.PRESENCE
        )

        var cursor = db.rawQuery(querySql.plus(queryWhere), arrayOf())
        // var cursor = db.query(false,DataBaseConstants.Guest.TABLE_NAME, selection, null, null, null, null, null, null, null)

        try {
            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.Columns.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.Columns.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.Columns.PRESENCE))

                    var guest = Guest(id, name, presence == 1)
                    guestList.add(guest)

                }
            }
            cursor.close()

        }
        catch (t: Throwable) {
            Log.e("ERRO_SQL", "Erro: ${t.message}", t)
            cursor.close()
        }
        return guestList
    }*/



}

    fun selectAll(): MutableList<Guest>{
        return guestDataBase.selectAll()
    }

}