package com.stoyanvuchev.reminder.data.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.stoyanvuchev.reminder.data.local.entity.TaskEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksLocalDatabaseTest {

    private lateinit var db: TasksLocalDatabase
    private lateinit var dao: TasksLocalDatabaseDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, TasksLocalDatabase::class.java).build()
        dao = db.dao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertEntity() = runTest {

        val expected = TaskEntity(
            id = 1698784332000L,
            title = "Title",
            description = "Description",
            alertTimestamp = null,
            completed = false
        )

        dao.upsertTask(expected)

        val actual = dao.getTaskById(expected.id)

        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun updateEntity() = runTest {

        val task = TaskEntity(
            id = 1698784332001L,
            title = "Title",
            description = "Description",
            alertTimestamp = null,
            completed = false
        )

        dao.upsertTask(task)

        val initial = dao.getTaskById(task.id)
        if (initial != null) {

            val expected = initial.copy(completed = true)
            dao.upsertTask(expected)

            val actual = dao.getTaskById(expected.id)
            assertThat(actual?.completed).isEqualTo(expected.completed)

        } else {
            throw Exception("Task with id: ${task.id} does not exist!")
        }

    }

    @Test
    fun deleteEntity() = runTest {

        val task = TaskEntity(
            id = 1698784332002L,
            title = "Title",
            description = "Description",
            alertTimestamp = null,
            completed = false
        )

        dao.upsertTask(task)

        dao.deleteTaskById(task.id)

        assertThat(dao.getTaskById(task.id)).isNull()

    }

}