package com.example.dreamlog

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dream_table")
class Dream(@ColumnInfo(name="title") val title:String,
                        @ColumnInfo(name="content") val content:String,
                        @ColumnInfo(name="reflection") val reflection:String,
                        @ColumnInfo(name="emotion") val emotion:String) {
    // don't need to put it in when initalizing
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name="id") var id:Int = 0
}