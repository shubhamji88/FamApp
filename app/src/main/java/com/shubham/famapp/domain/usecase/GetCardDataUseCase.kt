package com.shubham.famapp.domain.usecase

import android.util.Log
import com.shubham.famapp.data.entity.convertToDomain
import com.shubham.famapp.di.IoDispatcher
import com.shubham.famapp.domain.InternetNotAvailable
import com.shubham.famapp.domain.model.FamCardModel
import com.shubham.famapp.domain.repository.RemoteRepo
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCardDataUseCase @Inject constructor(
    private val remoteRepo: RemoteRepo,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : SuspendUseCase<FamCardModel, GetCardDataUseCase.Params>(coroutineDispatcher) {

    data class Params (val none: String): BaseUseCase.Parameters()

    override suspend fun execute(parameters: Params): Result<FamCardModel> {
        val remoteData = remoteRepo.getCardDataAsync().await()
        if(!remoteData.isSuccessful ){
            return Result.failure(InternetNotAvailable())
        }
        Log.d("recieved",remoteData.body()!!.toString())
        return Result.success(remoteData.body()!!.convertToDomain())
    }
}