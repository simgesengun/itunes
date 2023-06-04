package com.simgesengun.itunes.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.simgesengun.itunes.BuildConfig
import com.simgesengun.itunes.network.NetworkConstants.BASE_URL
import com.simgesengun.itunes.network.NetworkConstants.REQUEST_TIMEOUT_DURATION_SECONDS
import com.simgesengun.itunes.network.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideHttpClient(
        chuckerInterceptor: ChuckerInterceptor
    ) = OkHttpClient.Builder().apply {
        connectTimeout(REQUEST_TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
        readTimeout(REQUEST_TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
        writeTimeout(REQUEST_TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            addInterceptor(chuckerInterceptor)
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
    }.build()

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext appContext: Context
    ) = ChuckerInterceptor.Builder(appContext).build()
}