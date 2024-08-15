package com.example.dyf

import android.content.Intent
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

class RegistrarseActivity : ComponentActivity() {

    //ARREGLO USUARIOS
    private val usuarios = mutableListOf<Map<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DyfTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegistrarseScreen{ rut, nombreCompleto, correo, password, recibirNotificaciones ->
                        // Agregar usuario al arreglo
                        val nuevoUsuario = mapOf(
                            "rut" to rut,
                            "nombreCompleto" to nombreCompleto,
                            "correo" to correo,
                            "password" to password,
                            "recibirNotificaciones" to recibirNotificaciones
                        )
                        usuarios.add(nuevoUsuario)

                        //Mensaje de confirmación
                        Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show()

                        // Pasar el arreglo de usuarios a LoginActivity
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.putExtra("usuarios", ArrayList(usuarios)) // Convertir la lista a ArrayList
                        startActivity(intent)

                        // Finalizar la actividad actual
                        finish()

                    }
                }
            }
        }
    }
}

