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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_sanitaria_front_end.R

/**
 * Pantalla inicial de la aplicación.
 * Desde aquí se navega a Login, Listar enfermeros y Búsqueda.
 */
@Composable
fun HomeScreen(
    onIrLogin: () -> Unit,
    onIrListar: () -> Unit,
    onIrBuscar: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Interacción con mutableState: mostrar/ocultar la información de la app
    var mostrarInfo by rememberSaveable { mutableStateOf(false) }

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
            contentDescription = stringResource(R.string.home_logo_descripcion),
            modifier = Modifier.size(120.dp)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.home_titulo),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.azul_sanitario_oscuro)
        )
        Text(
            text = stringResource(R.string.home_subtitulo),
            fontSize = 16.sp,
            color = colorResource(R.color.texto_secundario),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        val colorBoton = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.azul_sanitario),
            contentColor = colorResource(R.color.white)
        )

        Button(onClick = onIrLogin, colors = colorBoton, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.home_boton_login))
        }
        Spacer(Modifier.height(12.dp))
        Button(onClick = onIrListar, colors = colorBoton, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.home_boton_listar))
        }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = onIrBuscar, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.home_boton_buscar),
                color = colorResource(R.color.azul_sanitario)
            )
        }

        Spacer(Modifier.height(24.dp))

        TextButton(onClick = { mostrarInfo = !mostrarInfo }) {
            Text(
                text = stringResource(
                    if (mostrarInfo) R.string.home_ocultar_info else R.string.home_mostrar_info
                ),
                color = colorResource(R.color.verde_salud)
            )
        }

        if (mostrarInfo) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.azul_claro)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.home_info),
                    color = colorResource(R.color.texto_principal),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onIrLogin = {}, onIrListar = {}, onIrBuscar = {})
}
