package com.shubham.famapp.data

import com.shubham.famapp.data.entity.FamCardEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FamApi {
    @GET("afabf1e3-3638-4f28-a2f3-32a5c7de01ba")
    fun getCardDataAsync() : Deferred<Response<FamCardEntity>>
}