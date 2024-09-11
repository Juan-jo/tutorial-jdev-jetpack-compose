package com.jdev.jdevcompose.instagramapp.core.di

import com.jdev.jdevcompose.instagramapp.login.data.network.LoginClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    @Named("DogApi")
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/")//://dog.ceo/api/breeds/image/random
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerLoginClient(@Named("DogApi") retrofit: Retrofit): LoginClient {
        return retrofit.create(LoginClient::class.java)
    }
}