package com.example.exercicioconvidadosroom.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.exercicioconvidados.repository.GuestDAO
import com.example.exercicioconvidadosroom.constants.DataBaseConstants
import com.example.exercicioconvidadosroom.model.Guest

// Sem o Room
/**class GuestDataBase(context: Context): SQLiteOpenHelper(context, name, null, version) {

    companion object{
        private const val name = "guestdb"
        private const val version = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //db.execSQL("Create Table Guest (" + "id integer primary key autoincrement, " + "name text, " + "presence integer);" )
        //db.execSQL("""Create Table Guest (
        //            |id integer primary key autoincrement,
        //            |name text,
        //            |presence integer);""".trimMargin() )

        db.execSQL("""Create Table ${DataBaseConstants.Guest.TABLE_NAME} (  
            |${DataBaseConstants.Guest.Columns.ID} integer primary key autoincrement, 
            |${DataBaseConstants.Guest.Columns.NAME} text, 
            |${DataBaseConstants.Guest.Columns.PRESENCE} integer);""".trimMargin() )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}*/

//Com o Room
@Database(entities = [Guest::class], version = 1)
abstract class GuestDataBase(): RoomDatabase(){

    abstract fun guestDAO() :GuestDAO

    companion object{
        private lateinit var instance:GuestDataBase

        fun getDataBase(context: Context): GuestDataBase{
            if (!::instance.isInitialized) {
                synchronized(GuestDataBase::class){
                    instance = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations(migration_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }

        private val migration_1_2: Migration = object : Migration(1 , 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM Guest")
            }

        }
    }

}