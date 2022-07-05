package com.shubham.famapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shubham.famapp.data.FamApi
import com.shubham.famapp.data.repository.RemoteRepoImpl
import com.shubham.famapp.domain.repository.RemoteRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideOkhttp():OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    @Named("base_url")
    fun providesBaseURL():String = "https://run.mocky.io/"

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,@Named("base_url") baseURL: String):Retrofit = Retrofit
        .Builder()
        .baseUrl(baseURL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesRemoteRepo(famApi: FamApi): RemoteRepo = RemoteRepoImpl(famApi)

    @Provides
    @Singleton
    fun provideFamApi(retrofit: Retrofit):FamApi = retrofit.create(FamApi::class.java)
}