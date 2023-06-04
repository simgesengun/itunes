package com.simgesengun.itunes.util.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.simgesengun.itunes.util.gson.DateDeserializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Date
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {

    @Provides
    @Singleton
    fun provideGson(
        dateDeserializer: DateDeserializer
    ): Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, dateDeserializer)
        .setPrettyPrinting()
        .create()
}