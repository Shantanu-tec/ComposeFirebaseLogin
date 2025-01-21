package com.drm.firebaselogin.presentation.login_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drm.firebaselogin.repository.AuthRepository
import com.drm.firebaselogin.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _email : MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String>
        get() =  _email

    private var _password : MutableLiveData<String> = MutableLiveData()
    val password: LiveData<String>
        get() =  _password


    fun setEmail(email:String){
        _email.postValue(email)
    }

    fun setPassword(password:String){
        _password.postValue(password)
    }

    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()


    fun loginUser(email:String,password:String) = viewModelScope.launch {
        authRepository.loginUser(email, password).collect { result ->

            when(result){
                is Resource.Loading ->{
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Success ->{
                    _signInState.send(SignInState(isSuccess = "Sign in Success"))
                }
                is Resource.Error ->{
                    _signInState.send(SignInState(isSuccess = result.message))
                }
            }

        }
    }

}