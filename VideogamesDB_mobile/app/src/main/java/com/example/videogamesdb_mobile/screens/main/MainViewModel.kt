package com.example.videogamesdb_mobile.screens.main

import androidx.lifecycle.*
import com.example.videogamesdb_mobile.models.Game
import com.example.videogamesdb_mobile.repositories.GameRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: GameRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allGamesDao: LiveData<List<Game>> = repository.allGamesDao.asLiveData()

    val allGames: LiveData<List<Game>> = repository.allGames

    suspend fun getGameList() {
        return repository.getGameList()
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(game: Game) = viewModelScope.launch {
        repository.insert(game)
    }
}

class GameViewModelFactory(private val repository: GameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}