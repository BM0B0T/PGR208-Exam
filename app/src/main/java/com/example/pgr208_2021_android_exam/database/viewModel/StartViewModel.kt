package com.example.pgr208_2021_android_exam.database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pgr208_2021_android_exam.database.db.DataBase
import com.example.pgr208_2021_android_exam.database.db.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TransactionRepository
    val successLiveData: MutableLiveData<Boolean?> = MutableLiveData(null)

    init {
        val transactionDao = DataBase.getDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        start()
    }


    private fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            successLiveData.postValue(repository.start())
        }
    }
}
