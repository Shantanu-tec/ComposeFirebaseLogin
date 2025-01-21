package com.drm.firebaselogin.presentation.signup_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drm.firebaselogin.presentation.login_screen.SignInState
import com.drm.firebaselogin.repository.AuthRepository
import com.drm.firebaselogin.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) :ViewModel(){

    private var _email : MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String>
        get() =  _email

    private var _password : MutableLiveData<String> = MutableLiveData()
    val password: LiveData<String>
        get() =  _password


    private var _confirmPassword : MutableLiveData<String> = MutableLiveData()
    val confirmPassword: LiveData<String>
        get() =  _confirmPassword


    fun setEmail(email:String){
        _email.postValue(email)
    }

    fun setPassword(password:String){
        _password.postValue(password)
    }

    fun setConfirmPassword(confirmPassword:String){
        _confirmPassword.postValue(confirmPassword)
    }

    val _signUpState = Channel<SignInState>()
    val signUpState = _signUpState.receiveAsFlow()


    fun registerUser(email:String,password:String) = viewModelScope.launch {
        authRepository.registerUser(email, password).collect { result ->

            when(result){
                is Resource.Loading ->{
                    _signUpState.send(SignInState(isLoading = true))
                }
                is Resource.Success ->{
                    _signUpState.send(SignInState(isSuccess = "Registration Success"))
                }
                is Resource.Error ->{
                    _signUpState.send(SignInState(isSuccess = result.message))
                }
            }

        }
    }
}