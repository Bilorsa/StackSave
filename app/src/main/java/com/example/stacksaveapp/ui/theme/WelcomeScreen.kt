package com.example.stacksaveapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(onLoginClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("StackSave", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onLoginClick, modifier = Modifier.fillMaxWidth()) {
            Text("Sign up with Email")
        }
    }
}