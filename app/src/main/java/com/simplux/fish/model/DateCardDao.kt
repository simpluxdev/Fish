package com.simplux.fish.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DateCardDao {

    @Insert
    suspend fun insertDateCard(dateCard: DateCard)

    @Delete
    suspend fun deleteDateCard(dateCard: DateCard)

    @Query("SELECT * FROM date_card_table ORDER BY date ASC")
    fun dateCards(): LiveData<List<DateCard>>

}