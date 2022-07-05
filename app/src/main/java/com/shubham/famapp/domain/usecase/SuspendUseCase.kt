package com.shubham.famapp.domain.usecase



import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<R, Params : BaseUseCase.Parameters>(
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Any, R>() {

    suspend operator fun invoke(parameters: Params): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters)
            }
        } catch (e: Exception) {
            Log.d("errror",e.toString())
            Result.failure(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: Params): Result<R>

}

abstract class BaseUseCase<in T,out S> {
    open class Parameters
}