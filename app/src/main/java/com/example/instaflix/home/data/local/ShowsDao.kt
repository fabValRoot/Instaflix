package com.example.instaflix.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(
        show: ShowEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfShows(
        show: List<ShowEntity>
    )

    @Query("SELECT * FROM showentity WHERE id = :id")
    suspend fun getShowById(id: Int): ShowEntity

    @Query("SELECT * FROM showentity WHERE category = :category AND showType = :showType")
    suspend fun getShowsByCategoryAndType(category: String, showType: String): List<ShowEntity>
}
