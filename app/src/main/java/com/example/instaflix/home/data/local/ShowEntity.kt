package com.example.instaflix.home.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShowEntity(
    @PrimaryKey val id: Int,
    val smallImgPath: String,
    val language: String,
    val overview: String,
    val popularity: Double,
    val largeImgPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val name: String,
    val category: String,
    val showType: String,
)
