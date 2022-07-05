package com.shubham.famapp.data.repository

import com.shubham.famapp.data.FamApi
import com.shubham.famapp.data.entity.FamCardEntity
import com.shubham.famapp.domain.repository.RemoteRepo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
   private val famApi: FamApi
) : RemoteRepo {
    override suspend fun getCardDataAsync(): Deferred<Response<FamCardEntity>> {
        return famApi.getCardDataAsync()
    }
}