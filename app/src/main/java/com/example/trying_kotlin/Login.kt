package com.example.trying_kotlin

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

fun sarvarCall(userID: String, password: String) {
    Log.i("Login Karo Bhai", "User ID: $userID Password: $password")
    println("User ID: $userID Password: $password")

    val sarvar = "https://rare-comparative-onion-requested.trycloudflare.com/login"

    try {
        val url: URL = URI.create(sarvar).toURL()
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        val responseCode: Int = connection.responseCode
        println("Response Code: $responseCode")

        if (responseCode == HttpURLConnection.HTTP_OK) {
            val reader: BufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?
            val response = StringBuilder()
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            println("Response Data: $response")
        }
        else {
            println("Request failed with response code: $responseCode")
        }

        connection.disconnect()
    } catch (e: Exception) {
        println("Custom Error: ${e.printStackTrace()}")
    }
}

@Composable
fun LoginUI(navCon: NavController) {

    var userID by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))

        Text("Login to your account")
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = userID,
            onValueChange = {userID = it},
            label = { Text("User ID") }
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            sarvarCall(userID, password)
        }) {
            Text("Login")
        }

        Spacer(Modifier.height(16.dp))
        TextButton(onClick = {}) {
            Text("Forgot Password?")
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            navCon.navigate("Tabs")
        }) {
            Text("Login as a Guest")
        }
    }
}