package com.example.dreamlog

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= arrayOf(Dream::class), version = 1, exportSchema = false)
public abstract class DreamRoomDatabase : RoomDatabase() {

    abstract fun dreamDAO():DreamDAO // getter

    companion object{
        @Volatile
        private var INSTANCE:DreamRoomDatabase? = null

        fun getDatabase(context: Context): DreamRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DreamRoomDatabase::class.java,
                    "dream_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}