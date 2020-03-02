package com.example.pokedex_ishapp.di

import com.example.pokedex_ishapp.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {
    fun inject(viewModel: ListViewModel)
}