package com.example.exercicioconvidados.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.exercicioconvidadosroom.model.Guest

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: Guest): Long

    @Update
    fun update(guest:Guest): Int

    @Delete
    fun delete(guest: Guest)

    @Query("SELECT * FROM Guest WHERE id = :guestId")
    fun selectItemForUpdate(guestId: Int?): Guest

    @Query("SELECT * FROM Guest WHERE presence = :guestPresence")
    fun select(guestPresence: Boolean): MutableList<Guest>
    //fun select(guestPresence: Boolean? = null): MutableList<Guest>

    @Query("SELECT * FROM Guest")
    fun selectAll(): MutableList<Guest>



}