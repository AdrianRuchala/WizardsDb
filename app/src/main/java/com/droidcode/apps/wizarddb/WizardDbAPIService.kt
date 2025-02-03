package com.droidcode.apps.wizarddb

import com.droidcode.apps.wizarddb.data.House
import com.droidcode.apps.wizarddb.data.Spell
import retrofit2.http.GET
import retrofit2.http.Headers

interface WizardDbAPIService {
    @Headers("accept: text/plain")
    @GET("/Houses")
    suspend fun getHouses(): List<House>

    @Headers("accept: text/plain")
    @GET("/Spells")
    suspend fun getSpells(): List<Spell>
}
