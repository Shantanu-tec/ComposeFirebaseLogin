package com.drm.firebaselogin.repository

import com.drm.firebaselogin.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email:String,password:String):Flow<Resource<AuthResult>>
    fun registerUser(email:String,password:String):Flow<Resource<AuthResult>>
}