package com.drm.firebaselogin.utils

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation

fun Activity.showToastLong(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}

fun Activity.showToastShort(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun printDebug(msg:String){
    println("---> $msg")
}


fun Activity.goToActivity(destinationActivity: Class<*>){
    val intent = Intent(this,destinationActivity)
    startActivity(intent)
//    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
}

fun Activity.goToActivityWithFinish(destinationActivity: Class<*>){
    val intent = Intent(this,destinationActivity)
    startActivity(intent)
    finish()
//    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
}

fun isValidIndianPhoneNumber(phoneNumber: String): Boolean {
    val regex = Regex("^[6-9][0-9]{9}\$")
    return regex.matches(phoneNumber)
}

fun Activity.showSoftKeyBoard(view: View) {
    val inputMethodManager =
        this.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, 0)
}

fun Activity.hideSoftKeyBoard() {
    val inputMethodManager =
        this.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


@Composable
fun String.ShowOutLinedField(content: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(content, onValueChange = {
        onValueChange.invoke(it)
    }, label = { Text(this@ShowOutLinedField) })
}

@Composable
fun String.ShowOutLinedFieldForPassword(content: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(content, onValueChange = {
        onValueChange.invoke(it)
    }, label = { Text(this@ShowOutLinedFieldForPassword) }, visualTransformation = PasswordVisualTransformation())
}
