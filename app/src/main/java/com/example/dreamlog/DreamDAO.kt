package com.example.dreamlog

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface DreamDAO {

    @Query("SELECT * FROM dream_table ORDER BY id ASC")
    fun getDreams() : Flow<List<Dream>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dream:Dream)

    @Query("UPDATE dream_table SET title=:title, content=:content, reflection=:reflection, emotion=:emotion WHERE id=:id")
    suspend fun update(title:String, content:String, reflection:String, emotion:String, id:Int)

    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun delete(id:Int)

    @Query("SELECT * FROM dream_table WHERE id=:id")
    fun findDream(id:Int) : Flow<Dream>
}