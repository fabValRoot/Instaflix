package com.example.instaflix.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShowEntity::class],
    version = 1
)

abstract class ShowsDatabase : RoomDatabase() {
    abstract val showsDao: ShowsDao
}
