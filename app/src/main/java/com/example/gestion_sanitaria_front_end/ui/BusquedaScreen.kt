package com.example.gestion_sanitaria_front_end.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gestion_sanitaria_front_end.R

// TODO: pantalla a desarrollar por un compañero. Mantener la firma (onVolver) para la navegación.
@Composable
fun BusquedaScreen(onVolver: () -> Unit) {
    PantallaPendiente(titulo = stringResource(R.string.home_boton_buscar), onVolver = onVolver)
}
