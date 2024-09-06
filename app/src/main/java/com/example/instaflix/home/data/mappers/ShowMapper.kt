package com.example.instaflix.home.data.mappers

import com.example.instaflix.home.data.local.ShowEntity
import com.example.instaflix.home.data.remote.dto.ShowDTo
import com.example.instaflix.home.domain.models.Show

fun ShowEntity.toShow(
    showType: String,
    category: String
): Show {
    return Show(
        smallImgPath = smallImgPath,
        id = id,
        language = language,
        overview = overview,
        popularity = popularity,
        largeImgPath = largeImgPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        name = name,
        category = category,
        showType = showType
    )
}

fun ShowDTo.toShowEntity(
    showType: String,
    category: String
): ShowEntity {
    return ShowEntity(
        smallImgPath = backdrop_path ?: "",
        id = id ?: 1,
        language = original_language ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        largeImgPath = poster_path ?: "",
        releaseDate = release_date ?: "",
        title = title ?: "",
        voteAverage = vote_average ?: 0.0,
        name = name ?: "",
        category = category,
        showType = showType

    )
}

fun ShowDTo.toShow(
    showType: String,
    category: String
): Show {
    return Show(
        smallImgPath = backdrop_path ?: "",
        id = id ?: 1,
        language = original_language ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        largeImgPath = poster_path ?: "",
        releaseDate = release_date ?: "",
        title = title ?: "",
        voteAverage = vote_average ?: 0.0,
        name = name ?: "",
        category = category,
        showType = showType
    )
}

fun Show.toShowEntity(): ShowEntity {
    return ShowEntity(
        smallImgPath = smallImgPath,
        id = id,
        language = language,
        overview = overview,
        popularity = popularity,
        largeImgPath = largeImgPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        name = name,
        category = category,
        showType = showType
    )

}
