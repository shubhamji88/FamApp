package com.shubham.famapp.domain.repository
import com.shubham.famapp.data.entity.FamCardEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface RemoteRepo  {
    suspend fun getCardDataAsync() : Deferred<Response<FamCardEntity>>
}