package com.drm.firebaselogin.presentation.home_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drm.firebaselogin.MainActivity
import com.drm.firebaselogin.utils.goToActivityWithFinish
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Activity.HomeScreen(firebaseAuth: FirebaseAuth){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(Modifier.height(60.dp))

        Text(text = "Welcome to Home Screen",fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(20.dp))

        Text(text = "User Email Address :",fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(30.dp))

        Text(text = "${firebaseAuth.currentUser?.email}",fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(50.dp))


        Button(onClick = {
            firebaseAuth.signOut()
            goToActivityWithFinish(MainActivity::class.java)
        }) {
            Text(text = "Logout")
        }


    }
}