package com.simplux.fish.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DateCard::class],version = 1, exportSchema = false)
abstract class DateCardDatabase: RoomDatabase() {
    abstract val dateCardDao: DateCardDao

    companion object {
        @Volatile
        private var INSTANCE: DateCardDatabase? = null

        fun getInstance(context: Context): DateCardDatabase {
            synchronized(this@Companion) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,DateCardDatabase::class.java,"date_card_database").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}