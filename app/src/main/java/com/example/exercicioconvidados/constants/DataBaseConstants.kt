package com.example.exercicioconvidadosroom.constants

class DataBaseConstants private constructor() {

    object Guest{
        const val TABLE_NAME = "Guest"

        object Columns{
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }

    }
}