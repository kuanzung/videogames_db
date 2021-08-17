package com.example.videogamesdb_mobile.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.videogamesdb_mobile.models.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM game_table ORDER BY name ASC")
    fun getSortedGames(): Flow<List<Game>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAll()
}