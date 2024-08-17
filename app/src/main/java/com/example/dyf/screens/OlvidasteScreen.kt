import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.dyf.LoginActivity
import com.example.dyf.data.UserData
import com.example.dyf.data.UserPreferences

@Composable
fun OlvidasteScreen(userPreferences: UserPreferences) { // Recibe la lista de usuarios
    // Estado de los campos
    var correo by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }

    // Estados para errores de validación
    var correoError by remember { mutableStateOf<String?>(null) }
    var rutError by remember { mutableStateOf<String?>(null) }
    var password by remember { mutableStateOf<String?>(null) }
    var errorMensaje by remember { mutableStateOf<String?>(null) }
    var userList by remember { mutableStateOf<List<UserData>>(emptyList()) }

    // Contexto para mostrar Toasts
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        userPreferences.userPreferencesFlow.collect { users ->
            userList = users
        }
    }

    fun vibrate(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Para API 26 y superiores
                val vibrationEffect = VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)
                vibrator.vibrate(vibrationEffect)
            } else {
                // Para versiones anteriores
                @Suppress("DEPRECATION")
                vibrator.vibrate(500) // Duración en milisegundos
            }
        }
    }

    // Función para validar el formulario
    fun validateForm(): Boolean {
        var isValid = true

        correoError = if (correo.isBlank()) "Campo requerido" else null
        rutError = if (rut.isBlank()) "Campo requerido" else null

        isValid = correoError == null && rutError == null

        if (isValid) {
            val user = userList.find { it.correo == correo && it.rut == rut }
            if (user == null) {
                correoError = "Datos erróneos"
                rutError = "Datos erróneos"
                isValid = false
            } else {
                password = user.password
            }
        }

        return isValid
    }

    fun recuperar(){
        if (validateForm()) {

            val usuario = userList.find { it.correo == correo && it.rut == rut }
            if (usuario != null) {
                // Aquí puedes mostrar la contraseña del usuario de alguna manera
                //Toast.makeText(context, "Contraseña: ${usuario.password}", Toast.LENGTH_SHORT).show()
                dialogMessage = "Tu contraseña es: ${usuario.password}"
                showDialog = true
                vibrate(context)
            } else {
                errorMensaje = "Los datos son erróneos"
                dialogMessage = "Los datos son erróneos"
                showDialog = true
                vibrate(context)
            }

        }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Campo de entrada de Correo Electrónico" },
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
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Campo de entrada del Rut" },
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
                        recuperar()
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

        if(showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false},
                title = { Text("Resultado")},
                text = { Text(dialogMessage, color = Color(0xFF000000))},
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)),
                        onClick = {
                            showDialog = false
                            context.startActivity(Intent(context, LoginActivity::class.java))
                        }
                    ){
                        Text("Aceptar", color = Color(0xFF000000))
                    }
                }
            )
        }
    }
}