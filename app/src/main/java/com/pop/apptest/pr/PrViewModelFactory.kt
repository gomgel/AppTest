package com.pop.apptest.pr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pop.apptest.iftest.PrViewModel

class PrViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrViewModel::class.java)) {
            return PrViewModel(
                repository = PrRepository(datasource = PrDatasource())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}