package com.simplux.fish.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "date_card_table")
data class DateCard(

    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo("description")
    val description: String = "",

    @ColumnInfo("emoji")
    val emoji: String = "",

    @ColumnInfo("date")
    val date: Long = 0L
)