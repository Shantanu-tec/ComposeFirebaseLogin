package com.drm.firebaselogin


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.drm.firebaselogin.home.HomeActivity
import com.drm.firebaselogin.presentation.splash_screen.SplashScreen
import com.drm.firebaselogin.utils.goToActivityWithFinish
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)

        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)
        setContent {
            SplashScreen()
        }

        lifecycleScope.launch {
            delay(2000)
            if (firebaseAuth.currentUser!=null) {
                goToActivityWithFinish(HomeActivity::class.java)
            }else{
                goToActivityWithFinish(MainActivity::class.java)
            }

        }
    }
}

