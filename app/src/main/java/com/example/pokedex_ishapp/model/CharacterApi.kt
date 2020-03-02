package com.example.pokedex_ishapp.model

import io.reactivex.Single
import retrofit2.http.GET

interface CharacterApi {
    @GET("api/character/")
    fun getCharacters(): Single<ApiResponse>
}