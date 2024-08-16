import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import android.widget.Toast

@Composable
fun OlvidasteScreen() {
    // Estado de los campos
    var correo by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }

    // Estados para errores de validación
    var correoError by remember { mutableStateOf<String?>(null) }
    var rutError by remember { mutableStateOf<String?>(null) }

    // Contexto para mostrar Toasts
    val context = LocalContext.current

    // Función para validar el formulario
    fun validateForm(): Boolean {
        var isValid = true

        correoError = if (correo.isBlank()) "Campo requerido" else null
        rutError = if (rut.isBlank()) "Campo requerido" else null

        isValid = correoError == null && rutError == null

        return isValid
    }

    // Composable
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F0F0) // Color de fondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Recuperar Contraseña",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            OutlinedTextField(
                value = rut,
                onValueChange = { rut = it },
                label = { Text("RUT") },
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

            Button(
                onClick = {
                    if (validateForm()) {
                        // Acción de recuperar contraseña (ej. mostrar mensaje de éxito)
                        Toast.makeText(context, "Recuperar contraseña", Toast.LENGTH_SHORT).show()
                        // Aquí podrías agregar la lógica para enviar un correo o verificar los datos
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC107)
                )
            ) {
                Text("Recuperar Contraseña", color = Color(0xFF000000))
            }
        }
    }
}

