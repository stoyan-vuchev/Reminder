package com.stoyanvuchev.reminder.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.stoyanvuchev.reminder.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksLocalDatabaseDao {

    @Query("SELECT * FROM tasks_table ORDER BY id DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Upsert
    suspend fun upsertTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks_table WHERE id = :id LIMIT 1")
    suspend fun getTaskById(id: Long): TaskEntity?

    @Query("DELETE FROM tasks_table WHERE id = :id")
    suspend fun deleteTaskById(id: Long)

}