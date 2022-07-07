package com.shubham.famapp.data

import com.shubham.famapp.data.entity.FamCardEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FamApi {
    @GET("v3/04a04703-5557-4c84-a127-8c55335bb3b4")
    fun getCardDataAsync() : Deferred<Response<FamCardEntity>>
}