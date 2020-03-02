package com.example.pokedex_ishapp.model

data class ApiResponse(
    val info: Info?,
    val results: List<Result>?
)