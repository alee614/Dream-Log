package com.example.dreamlog

import android.app.Application

class DreamApplication : Application() {
    val database by lazy { DreamRoomDatabase.getDatabase(this) }
    val repository by lazy {DreamRepository(database.dreamDAO())}
}