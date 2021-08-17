package com.example.videogamesdb_mobile.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "initial_release_year") val initial_release_year: Int,
    @ColumnInfo(name = "image_url") val image_url: String,
) {
    override fun toString(): String {
        return "Game(_id='$_id', name='$name', description='$description', initial_release_year=$initial_release_year, image_url='$image_url')"
    }

}
