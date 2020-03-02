package com.example.pokedex_ishapp.di

import com.example.pokedex_ishapp.model.CharacterApi
import com.example.pokedex_ishapp.model.CharactersApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://rickandmortyapi.com"

    @Provides
    fun provideApi():CharacterApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }

    @Provides
    fun provideCharacterApiService() : CharactersApiService{
        return CharactersApiService()
    }
}