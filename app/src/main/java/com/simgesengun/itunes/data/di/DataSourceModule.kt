package com.simgesengun.itunes.data.di

import android.content.Context
import androidx.room.Room
import com.simgesengun.itunes.data.datasource.locale.AppDB
import com.simgesengun.itunes.data.datasource.locale.DatabaseConstants.APP_DB
import com.simgesengun.itunes.data.datasource.remote.ITunesApiDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideAppDB(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        AppDB::class.java,
        APP_DB
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideITunesApiDS(retrofit: Retrofit): ITunesApiDS =
        retrofit.create(ITunesApiDS::class.java)

    @Provides
    fun provideTrackDao(appDB: AppDB) = appDB.trackDao()

}