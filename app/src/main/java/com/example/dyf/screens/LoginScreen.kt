package com.example.dyf.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dyf.LoginActivity
import com.example.dyf.OlvidasteActivity
import com.example.dyf.R
import com.example.dyf.RegistrarseActivity
import com.example.dyf.data.UserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(usuarios: List<UserData>) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Errores
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    // Fondo
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F0F0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.dyf),
                contentDescription = "Logo DyF",
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(bottom = 32.dp)
            )

            // Texto Correo
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = null
                },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                isError = emailError != null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFFC107),
                    cursorColor = Color(0xFFFFC107)
                )
            )
            if (emailError != null) {
                Text(
                    text = emailError ?: "",
                    color = Color(0xFFBB0000),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto Password
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = null
                },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                isError = passwordError != null,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFFC107),
                    cursorColor = Color(0xFFFFC107)
                )
            )
            if (passwordError != null) {
                Text(
                    text = passwordError ?: "",
                    color = Color(0xFFBB0000),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Iniciar Sesión
            Button(
                onClick = {
                    // Validar que los campos no estén vacíos
                    var valid = true
                    if (email.isBlank()) {
                        emailError = "El correo no puede estar vacío"
                        valid = false
                    }
                    if (password.isBlank()) {
                        passwordError = "La contraseña no puede estar vacía"
                        valid = false
                    }

                    if (valid) {
                        // Validar si el correo está registrado
                        val usuario = usuarios.find { it.correo == email && it.password == password }

                        if (usuario == null) {
                            Toast.makeText(context, "Cuenta no registrada o contraseña incorrecta", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
                            // Aquí podrías iniciar otra actividad o cambiar de pantalla
                            // Por ejemplo:
                            // val intent = Intent(context, HomeActivity::class.java)
                            // context.startActivity(intent)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC107)
                )
            ) {
                Text("Iniciar Sesión", color = Color(0xFF000000))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto para recuperación de contraseña y registro
            Text(
                "¿Olvidaste tu contraseña?",
                color = Color(0xFF969088),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    val intent = Intent(context, OlvidasteActivity::class.java)
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Registrarse",
                color = Color(0xFF969088),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    val intent = Intent(context, RegistrarseActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}
