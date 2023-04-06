package com.example.molfartask.presentation.subliminal.fragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.molfartask.base.BaseViewModel
import com.example.molfartask.entity.Record
import com.example.molfartask.presentation.Result
import com.example.molfartask.usecase.GetRecordsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SubliminalViewModel @Inject constructor(
    private val getRecordsUseCase: GetRecordsUseCase
) : BaseViewModel() {

    private var data: Result<List<Record>>? = null
    private val _filteredLiveData = MutableLiveData<Result<List<Record>>>()
    val filteredLiveData: LiveData<Result<List<Record>>> get() = _filteredLiveData

    fun getRecords(context: Context, category: String? = null) {
        if (!hasConnection(context)) {
            _filteredLiveData.value = Result.Error("No Internet Connection")
            return
        }
        _filteredLiveData.value = Result.Loading()

        viewModelScope.launch {
            data = getRecordsUseCase.getRecords()
            _filteredLiveData.value = data!!
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
                it.fields.categories.contains(category) || category == "all together"
            }

        filteredList?.let { _filteredLiveData.value = Result.Success(it) }
    }

}