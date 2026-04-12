package com.example.todoapp.app

import android.app.Application
import com.example.todoapp.core.database.di.databaseModule
import com.example.todoapp.core.database.local.dao.TaskDao
import com.example.todoapp.feature_tasks.di.taskModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                taskModule
            )
        }

        initData()
    }

    private fun initData() {
        val dao: TaskDao = org.koin.java.KoinJavaComponent
            .getKoin()
            .get()

        CoroutineScope(Dispatchers.IO).launch {
            DataLoader(this@App, dao).loadInitialData()
        }
    }
}