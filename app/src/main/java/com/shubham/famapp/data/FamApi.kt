package com.shubham.famapp.data

import com.shubham.famapp.data.entity.FamCardEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FamApi {
    @GET("v3/fefcfbeb-5c12-4722-94ad-b8f92caad1ad")
    fun getCardDataAsync() : Deferred<Response<FamCardEntity>>
}