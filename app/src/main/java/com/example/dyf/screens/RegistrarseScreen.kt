package com.example.dyf.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarseScreen() {
    // Textos de entrada
    var rut by remember { mutableStateOf("") }
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var validarPassword by remember { mutableStateOf("") }
    var recibirNotificaciones by remember { mutableStateOf("Sí") }

    // Estados de error
    var rutError by remember { mutableStateOf<String?>(null) }
    var nombreCompletoError by remember { mutableStateOf<String?>(null) }
    var correoError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var validarPasswordError by remember { mutableStateOf<String?>(null) }

    // Opciones para (DropdownMenu)
    val opcionesNotificaciones = listOf("Sí", "No")
    var expanded by remember { mutableStateOf(false) }

    // Validar formulario
    fun validateForm(): Boolean {
        var isValid = true

        rutError = if (rut.isBlank()) "Campo requerido" else null
        nombreCompletoError = if (nombreCompleto.isBlank()) "Campo requerido" else null
        correoError = if (correo.isBlank()) "Campo requerido" else null
        passwordError = if (password.isBlank()) "Campo requerido" else null
        validarPasswordError = if (validarPassword.isBlank()) "Campo requerido" else if (password != validarPassword) "Las contraseñas no coinciden" else null

        isValid = rutError == null && nombreCompletoError == null && correoError == null && passwordError == null && validarPasswordError == null

        return isValid
    }

    // Fondo
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F0F0)
    ) {
        //Centrado en la pantalla con desplazamiento
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            // Campo de entrada de Rut
            OutlinedTextField(
                value = rut,
                onValueChange = { rut = it },
                label = { Text("Rut") },
                isError = rutError != null,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = if (rutError != null) Color(0xFFFF5449) else Color(0xFFFFC107),
                    cursorColor = Color(0xFFFFC107)
                )
            )
            if (rutError != null) {
                Text(
                    text = rutError!!,
                    color = Color(0xFFFF5449),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de entrada de Nombre Completo
            OutlinedTextField(
                value = nombreCompleto,
                onValueChange = { nombreCompleto = it },
                label = { Text("Nombre Completo") },
                isError = nombreCompletoError != null,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = if (nombreCompletoError != null) Color(0xFFFF5449) else Color(0xFFFFC107),
                    cursorColor = Color(0xFFFFC107)
                )
            )
            if (nombreCompletoError != null) {
                Text(
                    text = nombreCompletoError!!,
                    color = Color(0xFFFF5449),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de entrada de Correo
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo Electrónico") },
                isError = correoError != null,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = if (correoError != null) Color(0xFFFF5449) else Color(0xFFFFC107),
                    cursorColor = Color(0xFFFFC107)
                )
            )
            if (correoError != null) {
                Text(
                    text = correoError!!,
                    color = Color(0xFFFF5449),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de entrada de Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                isError = passwordError != null,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = if (passwordError != null) Color(0xFFFF5449) else Color(0xFFFFC107),
                    cursorColor = Color(0xFFFFC107)
                )
            )
            if (passwordError != null) {
                Text(
                    text = passwordError!!,
                    color = Color(0xFFFF5449),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de validación de Password
            OutlinedTextField(
                value = validarPassword,
                onValueChange = { validarPassword = it },
                label = { Text("Validar Contraseña") },
                isError = validarPasswordError != null,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = if (validarPasswordError != null) Color(0xFFFF5449) else Color(0xFFFFC107),
                    cursorColor = Color(0xFFFFC107)
                )
            )
            if (validarPasswordError != null) {
                Text(
                    text = validarPasswordError!!,
                    color = Color(0xFFFF5449),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ComboBox (DropdownMenu) para recibir notificaciones
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = recibirNotificaciones,
                    onValueChange = { },
                    label = { Text("¿Desea recibir notificaciones?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFFFFC107),
                        cursorColor = Color(0xFFFFC107)
                    ),
                    enabled = false
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    opcionesNotificaciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                recibirNotificaciones = opcion
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Registrarse
            Button(
                onClick = {
                    if (validateForm()) {

                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC107)
                )
            ) {
                Text("Registrarse", color = Color(0xFF000000))
            }
        }
    }
}