package com.jdev.jdevcompose.todoapp.addtasks.ui

import com.jdev.jdevcompose.todoapp.addtasks.ui.model.TaskModel

interface TaskUiState {
    object Loading: TaskUiState
    data class Error(val throwable: Throwable): TaskUiState
    data class Success(val tasks: List<TaskModel>): TaskUiState
}