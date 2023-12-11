package com.capstone.interviewku.ui.activities.forgetpass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.interviewku.data.AuthRepository
import com.capstone.interviewku.util.SingleEvent
import com.capstone.interviewku.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _passwordResetState = MutableLiveData<Result<Unit>>()
    val passwordResetState: LiveData<Result<Unit>>
        get() = _passwordResetState

    fun requestPasswordReset(email: String) = viewModelScope.launch {
        _passwordResetState.value = Result.Loading

        try {
            authRepository.requestPasswordReset(email)
            _passwordResetState.value = Result.Success(Unit)
        } catch (e: Exception) {
            _passwordResetState.value = Result.Error(SingleEvent(e))
            }
        }
}