package com.example.attendancechecker.di

import android.app.Application
import androidx.room.Room
import com.example.attendancechecker.data.local.ACDao
import com.example.attendancechecker.data.local.ACDatabase
import com.example.attendancechecker.data.repository.MainRepositoryImpl
import com.example.attendancechecker.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideACDatabase(
        application: Application
    ): ACDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ACDatabase::class.java,
            name = "ac.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideACDao(
        acDatabase: ACDatabase
    ): ACDao {
        return acDatabase.acDao
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        acDao: ACDao
    ): MainRepository {
        return MainRepositoryImpl(acDao = acDao)
    }



}