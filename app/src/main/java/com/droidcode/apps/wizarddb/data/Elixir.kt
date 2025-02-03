package com.droidcode.apps.wizarddb.data

data class Elixir(
    val id: String,
    val name: String,
    val effect: String?,
    val sideEffects: String?,
    val characteristics: String?,
    val time: String?,
    val difficulty: String?,
    val ingredients: List<Ingredients>,
    val inventors: List<Inventors>,
    val manufacturer: String?
)
