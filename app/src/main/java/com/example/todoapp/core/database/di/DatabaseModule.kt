package com.example.todoapp.core.database.di

import androidx.room.Room
import com.example.todoapp.core.database.local.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single {
        get<AppDatabase>().taskDao()
    }
}