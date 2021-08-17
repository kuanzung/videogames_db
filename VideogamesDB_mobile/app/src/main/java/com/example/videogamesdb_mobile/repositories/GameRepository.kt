package com.example.videogamesdb_mobile.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.videogamesdb_mobile.database.GameDao
import com.example.videogamesdb_mobile.models.Game
import com.example.videogamesdb_mobile.service.IWebservice
import com.example.videogamesdb_mobile.service.NetworkService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameRepository(private val gameDao: GameDao) {

    private var webservice: IWebservice = NetworkService.data

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allGamesDao: Flow<List<Game>> = gameDao.getSortedGames()


    // retrofit get all games
    val allGames = MutableLiveData<List<Game>>()

    suspend fun getGameList() {
        val call: Call<List<Game>> = webservice.getGames()

        delay(10000)

        call.enqueue(object : Callback<List<Game>> {
            override fun onResponse(
                @NotNull call: Call<List<Game>>,
                @NotNull response: Response<List<Game>>
            ) {
                Log.d("log", response.body().toString())
                allGames.value = response.body()
            }

            override fun onFailure(@NotNull call: Call<List<Game>>, @NotNull t: Throwable) {
                Log.d("log", t.message.toString())
            }
        })
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(game: Game) {
        gameDao.insert(game)
    }
}