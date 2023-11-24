package com.example.group2_comp304lab4.ui.patient

import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import com.example.group2_comp304lab4.model.Patient
import com.example.group2_comp304lab4.repository.PatientRepository
import kotlinx.coroutines.launch

class PatientViewModel(private val repository: PatientRepository) : ViewModel() {
    val allPatients: LiveData<List<Patient>> = repository.allPatients.asLiveData()
    fun getAllPatientsByNurseId(nurseId: Long): LiveData<List<Patient>> =
        repository.getAllByNurseId(nurseId).asLiveData()

    @WorkerThread
    fun insert(patient: Patient) = viewModelScope.launch {
        repository.insert(patient)
    }

    @WorkerThread
    fun update(patient: Patient) = viewModelScope.launch {
        repository.update(patient)
    }
}

class PatientViewModelFactory(private val repository: PatientRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            return PatientViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}