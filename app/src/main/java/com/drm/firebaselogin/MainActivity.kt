package com.drm.firebaselogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drm.firebaselogin.presentation.login_screen.LoginScreen
import com.drm.firebaselogin.presentation.login_screen.SignInViewModel
import com.drm.firebaselogin.presentation.signup_screen.SignUpScreen
import com.drm.firebaselogin.presentation.signup_screen.SignUpViewModel
import com.drm.firebaselogin.repository.AuthRepository
import com.drm.firebaselogin.ui.theme.FirebaseLoginTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val signInViewModel :SignInViewModel by viewModels()
    private val signUpViewModel :SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navFragment = rememberNavController()
            NavHost(navFragment,Routes.LOGIN) {
                composable(Routes.LOGIN){
                    LoginScreen(navFragment,signInViewModel)
                }
                composable(Routes.SIGNUP) {
                    SignUpScreen(signUpViewModel)
                }
            }
        }
    }
}

