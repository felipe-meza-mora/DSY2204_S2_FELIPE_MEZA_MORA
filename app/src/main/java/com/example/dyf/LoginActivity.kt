package com.example.dyf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.dyf.data.UserPreferences
import com.example.dyf.screens.LoginScreen
import com.example.dyf.ui.theme.DyfTheme

class LoginActivity : ComponentActivity() {
    // Lista de usuarios
    private val usuarios = mutableListOf<UserPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener usuarios desde el intent (si existe)
        val usersFromIntent = intent.getSerializableExtra("usuarios") as? List<UserPreferences>
        if (usersFromIntent != null) {
            usuarios.addAll(usersFromIntent)
        }

        setContent {
            DyfTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}
