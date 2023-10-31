package com.stoyanvuchev.reminder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stoyanvuchev.reminder.data.local.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
abstract class TasksLocalDatabase : RoomDatabase() {

    abstract val dao: TasksLocalDatabaseDao

    /*companion object {

        fun createInstance(context: Context): TasksLocalDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = TasksLocalDatabase::class.java,
                name = "tasks_db"
            ).build()
        }

    }*/

}