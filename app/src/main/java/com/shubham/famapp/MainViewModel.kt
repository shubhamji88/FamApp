package com.shubham.famapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.famapp.domain.model.FamCardModel
import com.shubham.famapp.domain.usecase.GetCardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val cardDataUseCase: GetCardDataUseCase
) : ViewModel() {
    private val _cardData = MutableLiveData<FamCardModel?>()
    val cardData : LiveData<FamCardModel?>
        get() = _cardData
    fun call(){
        viewModelScope.launch {
            _cardData.postValue(cardDataUseCase.invoke(GetCardDataUseCase.Params("")).getOrNull())
        }
    }
}