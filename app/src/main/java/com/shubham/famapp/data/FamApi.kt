package com.shubham.famapp.data

import com.shubham.famapp.data.entity.FamCardEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FamApi {
    @GET("d64c5505-10e2-4fb1-8356-9bd275ce88d1")
    fun getCardDataAsync() : Deferred<Response<FamCardEntity>>
}