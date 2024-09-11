package com.jdev.jdevcompose.todoapp.addtasks.domain

import com.jdev.jdevcompose.todoapp.addtasks.data.TaskRepository
import com.jdev.jdevcompose.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks

}
