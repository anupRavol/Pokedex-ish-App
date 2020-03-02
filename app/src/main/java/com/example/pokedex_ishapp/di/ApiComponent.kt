package com.example.pokedex_ishapp.di

import com.example.pokedex_ishapp.model.CharactersApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service :CharactersApiService)
}