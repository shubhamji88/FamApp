package com.shubham.famapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.famapp.domain.model.CardGroupModel
import com.shubham.famapp.domain.model.FamCardModel
import com.shubham.famapp.domain.usecase.GetCardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val cardDataUseCase: GetCardDataUseCase
) : ViewModel() {
    val data = MutableLiveData<FamCardModel?>()
    fun call(){

        viewModelScope.launch {
            data.postValue(cardDataUseCase.invoke(GetCardDataUseCase.Params("")).getOrNull())
        }
    }
}