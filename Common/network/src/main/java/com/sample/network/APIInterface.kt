package com.sample.network

import com.sample.model.GenericResponse
import retrofit2.http.GET

interface APIInterface {

    @GET("/bins/1dcbc0")
    suspend fun getSongList(): GenericResponse<List<String>>


}
