package com.jdev.jdevcompose.todoapp.addtasks.data

import com.jdev.jdevcompose.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items ->
        items.map {
            TaskModel(
                id = it.id,
                task = it.task,
                selected = it.selected
            )
        }
    }

    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(taskModel.toData()
        )
    }

    suspend fun update(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.toData())
    }

    suspend fun delete(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel.toData())
    }
}

// fun de extensión
private fun TaskModel.toData(): TaskEntity {
    return TaskEntity(
        id = this.id,
        task = this.task,
        selected = this.selected
    )
}