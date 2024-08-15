package com.example.dyf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.dyf.screens.LoginScreen
import com.example.dyf.ui.theme.DyfTheme

class LoginActivity : ComponentActivity() {
    // Suponiendo que aquí guardamos el arreglo de usuarios
    private val usuarios = mutableListOf<Map<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aquí podrías recibir los usuarios desde RegistrarseActivity si los pasas por Intent
        val usersFromIntent = intent.getSerializableExtra("usuarios") as? List<Map<String, String>>
        if (usersFromIntent != null) {
            usuarios.addAll(usersFromIntent)
        }

        setContent {
            DyfTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(usuarios)
                }
            }
        }
    }

    // Método para devolver el arreglo de usuarios
    fun getUsuarios(): List<Map<String, String>> {
        return usuarios
    }
}