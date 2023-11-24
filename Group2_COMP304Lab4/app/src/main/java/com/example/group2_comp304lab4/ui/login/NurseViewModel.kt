package com.example.group2_comp304lab4.ui.login


import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import com.example.group2_comp304lab4.model.Nurse
import com.example.group2_comp304lab4.repository.NurseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NurseViewModel(private val repository: NurseRepository) : ViewModel() {

    private val _authenticatedUser = MutableLiveData<Nurse>()
    val authenticatedUser: LiveData<Nurse> get() = _authenticatedUser

    private val _authenticationError = MutableLiveData<String>()
    val authenticationError: LiveData<String> get() = _authenticationError

    @WorkerThread
    fun insert(nurse: Nurse) = viewModelScope.launch {
        repository.insert(nurse)
    }

    // function to authenticate user
    fun authenticateUser(username: Long, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                // get user from database by username and password
                val user = repository.getNurse(username, password)

                // Update the LiveData on the main thread
                viewModelScope.launch(Dispatchers.Main) {
                    _authenticatedUser.value = user
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    _authenticationError.postValue("Authentication failed: ${e.message}")
                }
            }
        }
    }
}

class NurseViewModelFactory(private val repository: NurseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NurseViewModel::class.java)) {
            return NurseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}