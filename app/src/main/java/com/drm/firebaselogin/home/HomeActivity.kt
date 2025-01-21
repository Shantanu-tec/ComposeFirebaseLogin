package com.drm.firebaselogin.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.drm.firebaselogin.presentation.home_screen.HomeScreen
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : ComponentActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen(firebaseAuth)
        }
    }
}

