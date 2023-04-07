package com.example.molfartask.presentation.subliminal.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.molfartask.R
import com.example.molfartask.base.BaseViewModel
import com.example.molfartask.data.entity.Record
import com.example.molfartask.domain.Result
import com.example.molfartask.domain.usecase.GetRecordsUseCase
import com.example.molfartask.utils.ErrorHandler
import com.example.molfartask.utils.ResourceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SubliminalViewModel @Inject constructor(
    errorHandler: ErrorHandler,
    private val getRecordsUseCase: GetRecordsUseCase,
    private val resourceManager: ResourceManager
) : BaseViewModel(errorHandler) {

    private var data: Result<List<Record>>? = null
    private val _filteredLiveData = MutableLiveData<Result<List<Record>>>()
    val filteredLiveData: LiveData<Result<List<Record>>> get() = _filteredLiveData

    fun getRecords(category: String? = null) {
        _filteredLiveData.value = Result.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data = getRecordsUseCase.getRecords()
                _filteredLiveData.postValue(data!!)
            } catch (t: Throwable) {
                sendNotifier(t)
            }
        }
        category?.let {
            filterRecordsByCategory(it)
        }
    }

    fun filterRecordsByCategory(category: String) {
        val filteredList = data
            ?.takeIf { res ->
                res is Result.Success
            }
            ?.data?.filter {
                it.field.category.contains(category) ||
                        category == resourceManager.getString(R.string.all_together)
            }

        filteredList?.let { _filteredLiveData.value = Result.Success(it) }
    }

}