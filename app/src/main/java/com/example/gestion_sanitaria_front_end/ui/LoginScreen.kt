package com.example.gestion_sanitaria_front_end.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_sanitaria_front_end.R

/**
 * Pantalla de validación de login de un enfermero.
 * Todavía no se conecta con el back-end: el botón Entrar solo comprueba
 * que los campos no estén vacíos y muestra un mensaje.
 */
@Composable
fun LoginScreen(onVolver: () -> Unit, modifier: Modifier = Modifier) {
    // Valores que cambian con la interacción del usuario (mutableState)
    var usuario by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var mostrarPassword by rememberSaveable { mutableStateOf(false) }
    // null = aún no se ha pulsado Entrar, true = login correcto, false = faltan datos
    var loginCorrecto by rememberSaveable { mutableStateOf<Boolean?>(null) }

    val coloresCampo = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResource(R.color.azul_sanitario),
        focusedLabelColor = colorResource(R.color.azul_sanitario),
        cursorColor = colorResource(R.color.azul_sanitario),
        unfocusedBorderColor = colorResource(R.color.texto_secundario),
        unfocusedLabelColor = colorResource(R.color.texto_secundario),
        focusedTextColor = colorResource(R.color.texto_principal),
        unfocusedTextColor = colorResource(R.color.texto_principal)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.fondo_app))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo_sanitario),
            contentDescription = stringResource(R.string.login_logo_descripcion),
            modifier = Modifier.size(100.dp)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.login_titulo),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.azul_sanitario_oscuro)
        )
        Text(
            text = stringResource(R.string.login_subtitulo),
            fontSize = 16.sp,
            color = colorResource(R.color.texto_secundario),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = {
                usuario = it
                loginCorrecto = null
            },
            label = { Text(stringResource(R.string.login_usuario)) },
            singleLine = true,
            colors = coloresCampo,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                loginCorrecto = null
            },
            label = { Text(stringResource(R.string.login_password)) },
            singleLine = true,
            colors = coloresCampo,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            // Si mostrarPassword es false, la contraseña se ve con puntos
            visualTransformation = if (mostrarPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            // Icono de ojo: al pulsarlo cambia el valor de mostrarPassword
            trailingIcon = {
                IconButton(onClick = { mostrarPassword = !mostrarPassword }) {
                    Icon(
                        painter = painterResource(
                            if (mostrarPassword) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                        ),
                        contentDescription = stringResource(
                            if (mostrarPassword) R.string.login_ocultar_password else R.string.login_mostrar_password
                        ),
                        tint = colorResource(R.color.azul_sanitario)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { loginCorrecto = usuario.isNotBlank() && password.isNotBlank() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.azul_sanitario),
                contentColor = colorResource(R.color.white)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.login_boton_entrar))
        }

        // Mensaje según el resultado de pulsar Entrar
        when (loginCorrecto) {
            true -> {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.login_correcto, usuario),
                    color = colorResource(R.color.verde_salud),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            false -> {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.login_error_vacio),
                    color = colorResource(R.color.rojo_cruz),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            null -> {}
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(onClick = onVolver, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.volver),
                color = colorResource(R.color.azul_sanitario)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onVolver = {})
}
