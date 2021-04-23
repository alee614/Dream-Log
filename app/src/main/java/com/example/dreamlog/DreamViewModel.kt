package com.example.dreamlog

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DreamViewModel (private val repository: DreamRepository) : ViewModel() {
    val allDreams: LiveData<List<Dream>> = repository.allDreams.asLiveData()

    fun insert(dream:Dream) = viewModelScope.launch {
        repository.insert(dream)
    }

    fun delete(id:Int) = viewModelScope.launch {
        repository.delete(id)
    }

   fun find(id:Int) : LiveData<Dream> = repository.findDream(id).asLiveData()


    fun update(title:String, content:String, reflection:String, emotion:String, id:Int) = viewModelScope.launch {
            repository.update(title, content, reflection, emotion, id)
        }
    }



class DreamViewModelFactory(private val repository: DreamRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DreamViewModel::class.java)){
            return DreamViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")

    }

}