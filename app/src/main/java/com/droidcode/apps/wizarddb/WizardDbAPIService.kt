package com.droidcode.apps.wizarddb

import com.droidcode.apps.wizarddb.data.House
import retrofit2.http.GET
import retrofit2.http.Headers

interface WizardDbAPIService {
    @Headers("accept: text/plain")
    @GET("/Houses")
    suspend fun getHouse(): List<House>
}
