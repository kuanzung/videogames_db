package com.example.videogamesdb_mobile

import android.app.Application
import com.example.videogamesdb_mobile.database.GameDatabase
import com.example.videogamesdb_mobile.repositories.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { GameDatabase.getDatabase(applicationContext, applicationScope) }
    val repository by lazy { GameRepository(database.gameDao()) }
}