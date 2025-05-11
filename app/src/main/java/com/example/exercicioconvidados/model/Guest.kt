package com.example.exercicioconvidadosroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
class Guest {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "nome")
    var nome: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = false
}