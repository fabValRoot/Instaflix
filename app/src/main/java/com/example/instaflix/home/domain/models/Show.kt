package com.example.instaflix.home.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    val smallImgPath: String,
    val id: Int,
    val language: String,
    val overview: String,
    val popularity: Double,
    val largeImgPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val name: String,
    val category: String,
    val showType: String
) : Parcelable
