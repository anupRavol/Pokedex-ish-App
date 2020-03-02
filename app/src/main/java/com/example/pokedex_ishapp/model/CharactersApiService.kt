package com.example.pokedex_ishapp.model

import com.example.pokedex_ishapp.di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CharactersApiService {

    @Inject
    lateinit var api : CharacterApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCharacters() :Single<ApiResponse>{
        return api.getCharacters()
    }
}