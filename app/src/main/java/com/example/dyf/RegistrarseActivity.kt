package com.example.dyf

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.dyf.screens.RegistrarseScreen
import com.example.dyf.ui.theme.DyfTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RegistrarseActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE)

        // Obtener usuarios del SharedPreferences
        val usuariosJson = sharedPreferences.getString("usuarios", "[]")
        val usuariosListType = object : TypeToken<MutableList<Map<String, String>>>() {}.type
        val usuarios = gson.fromJson<MutableList<Map<String, String>>>(usuariosJson, usuariosListType)

        setContent {
            DyfTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegistrarseScreen()
                }
            }
        }
    }
}
