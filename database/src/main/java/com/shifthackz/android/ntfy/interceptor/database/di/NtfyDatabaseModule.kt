package com.shifthackz.android.ntfy.interceptor.database.di

import androidx.room.Room
import com.shifthackz.android.ntfy.interceptor.database.NtfyDatabase
import com.shifthackz.android.ntfy.interceptor.database.NtfyDatabaseImpl
import com.shifthackz.android.ntfy.interceptor.database.NtfyRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DB_NAME = "ntfy_interceptor.db"

val ntfyDatabaseModule = module {
    single {
        Room
            .databaseBuilder(androidContext(), NtfyRoomDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration(false)
            .build()
    }

    factory<NtfyDatabase> {
        NtfyDatabaseImpl(get())
    }

    factory<NtfyDatabase.Notification> {
        NtfyDatabaseImpl(get()).NotificationImpl()
    }

    factory<NtfyDatabase.Log> {
        NtfyDatabaseImpl(get()).LogImpl()
    }
}
