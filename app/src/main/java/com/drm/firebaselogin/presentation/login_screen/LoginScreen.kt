package com.drm.firebaselogin.presentation.login_screen

import android.app.Activity
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.drm.firebaselogin.R
import com.drm.firebaselogin.Routes
import com.drm.firebaselogin.home.HomeActivity
import com.drm.firebaselogin.utils.ShowOutLinedField
import com.drm.firebaselogin.utils.ShowOutLinedFieldForPassword
import com.drm.firebaselogin.utils.goToActivityWithFinish
import com.drm.firebaselogin.utils.showToastLong
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Activity.LoginScreen(navFragment: NavHostController, viewModel: SignInViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val email by viewModel.email.observeAsState("")
        val password by viewModel.password.observeAsState("")

        setObserver(viewModel){
            viewModel.setEmail("")
            viewModel.setPassword("")
        }

        Spacer(Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.mipmap.splash_icon),
            contentDescription = "Spalsh Icon",
            modifier = Modifier.size(200.dp)
        )

        Spacer(Modifier.height(20.dp))

        Text(text = "Welcome to Login Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(20.dp))

        "Email".ShowOutLinedField(email) {
            viewModel.setEmail(it)
        }

        Spacer(Modifier.height(20.dp))

        "Password".ShowOutLinedFieldForPassword(password) {
            viewModel.setPassword(it)
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            validate(email = email, password = password, viewModel = viewModel)
        }) {
            Text(text = "Login")
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have an account?", fontSize = 15.sp)
            Spacer(Modifier.width(5.dp))
            Text(
                "Please SignUp!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navFragment.navigate(Routes.SIGNUP)
                }
            )
        }
    }
}



fun Activity.validate(email: String, password: String, viewModel: SignInViewModel) {
    if (email.isEmpty()) {
        showToastLong("Email is Empty")
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        showToastLong("Email is not valid")
    } else if (password.isEmpty()) {
        showToastLong("Password is Empty")
    } else if (password.length != 8) {
        showToastLong("Password must be of 8 character")
    } else {
        viewModel.loginUser(email, password)
    }
}

fun Activity.setObserver(
    viewModel: SignInViewModel,
    onCallBack: () -> Unit
) {
    CoroutineScope(Dispatchers.Main).launch {
        viewModel.signInState.collect { state ->
            if (state.isLoading) {
                showToastLong("Loading")
            } else if (!state.isSuccess.isNullOrEmpty()) {
                onCallBack.invoke()
                showToastLong(state.isSuccess)
                goToActivityWithFinish(HomeActivity::class.java)
            } else {
                showToastLong(state.isError.toString())
            }
        }
    }
}