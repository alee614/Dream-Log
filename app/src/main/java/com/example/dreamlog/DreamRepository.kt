package com.example.dreamlog

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class DreamRepository (private val dreamDao: DreamDAO){

    val allDreams : Flow<List<Dream>> = dreamDao.getDreams()

    suspend fun insert(dream:Dream){
        dreamDao.insert(dream)
    }

    suspend fun update(title:String, content:String, reflection:String, emotion:String, id:Int) {
        dreamDao.update(title, content, reflection, emotion, id)
    }

    suspend fun delete(id:Int){
        dreamDao.delete(id)
    }

    fun findDream(id:Int) : Flow<Dream> {
        return dreamDao.findDream(id)
    }


}