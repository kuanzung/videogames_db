package com.example.videogamesdb_mobile.service

import com.example.videogamesdb_mobile.models.Game
import com.example.videogamesdb_mobile.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface IWebservice {
    @GET(Constants.API_GET_GAMES)
    fun getGames(): Call<List<Game>>
}