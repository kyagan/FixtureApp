package com.kaanyagan.fixtureapp.persistence;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TeamEntity::class, FixtureEntity::class], version = 4, exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {


    val DB_NAME: String = "app_database"

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kaan.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


    abstract fun appDao(): AppDao

}
