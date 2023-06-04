package com.simgesengun.itunes.app.di

import com.simgesengun.itunes.app.di.annotation.DefaultDispatcher
import com.simgesengun.itunes.app.di.annotation.IODispatcher
import com.simgesengun.itunes.app.di.annotation.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {
    @Singleton
    @Provides
    fun providesCoroutineScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ) = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @DefaultDispatcher
    @Provides
    @Singleton
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IODispatcher
    @Provides
    @Singleton
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    @Singleton
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}