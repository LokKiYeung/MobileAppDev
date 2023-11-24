package com.example.group2_comp304lab4.ui.test

import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import com.example.group2_comp304lab4.model.Test
import com.example.group2_comp304lab4.repository.TestRepository
import kotlinx.coroutines.launch

class TestViewModel(private val repository: TestRepository) : ViewModel() {
    val allTests: LiveData<List<Test>> = repository.allTests.asLiveData()
    fun getAllTestsByPatientId(patientId: Long): LiveData<List<Test>> =
        repository.getAllByPatientId(patientId).asLiveData()

    @WorkerThread
    fun insert(test: Test) = viewModelScope.launch {
        repository.insert(test)
    }

    @WorkerThread
    fun update(test: Test) = viewModelScope.launch {
        repository.update(test)
    }
}

class TestViewModelFactory(private val repository: TestRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            return TestViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}