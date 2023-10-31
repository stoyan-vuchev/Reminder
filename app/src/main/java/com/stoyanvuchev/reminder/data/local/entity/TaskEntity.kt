package com.stoyanvuchev.reminder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val description: String,
    val alertTimestamp: Long?,
    val completed: Boolean
)