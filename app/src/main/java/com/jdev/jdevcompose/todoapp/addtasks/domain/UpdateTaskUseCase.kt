package com.jdev.jdevcompose.todoapp.addtasks.domain

import com.jdev.jdevcompose.todoapp.addtasks.data.TaskRepository
import com.jdev.jdevcompose.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.update(taskModel)
    }
}