package com.jdev.jdevcompose.todoapp.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.jdev.jdevcompose.todoapp.addtasks.data.TaskDao
import com.jdev.jdevcompose.todoapp.addtasks.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTaskDao(
        todoDatabase: TodoDatabase
    ): TaskDao {
        return todoDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun providerTodoDatabase(@ApplicationContext appContext: Context): TodoDatabase {
        return Room.databaseBuilder(
            appContext, TodoDatabase::class.java, "TaskDatabase"
        ).build()
    }
}