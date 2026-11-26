package com.example.gestion_sanitaria_front_end.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestion_sanitaria_front_end.R

/** Rutas de navegación de la app. */
object Rutas {
    const val HOME = "home"
    const val LOGIN = "login"
    const val LISTAR = "listar_enfermeros"
    const val BUSCAR = "buscar_enfermeros"
}

/**
 * Grafo de navegación:
 *   Home <-> Login, Home <-> Listar enfermeros, Home <-> Búsqueda.
 * Cada pantalla recibe un callback onVolver para regresar a Home.
 */
@Composable
fun Navegacion(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val volver: () -> Unit = { navController.popBackStack() }

    NavHost(navController = navController, startDestination = Rutas.HOME, modifier = modifier) {
        composable(Rutas.HOME) {
            HomeScreen(
                onIrLogin = { navController.navigate(Rutas.LOGIN) },
                onIrListar = { navController.navigate(Rutas.LISTAR) },
                onIrBuscar = { navController.navigate(Rutas.BUSCAR) }
            )
        }
        composable(Rutas.LOGIN) { LoginScreen(onVolver = volver) }
        composable(Rutas.LISTAR) { ListaEnfermerosScreen(onVolver = volver) }
        composable(Rutas.BUSCAR) { BusquedaScreen(onVolver = volver) }
    }
}

/** Marcador temporal para pantallas aún no desarrolladas. Borrar cuando ya no se use. */
@Composable
fun PantallaPendiente(titulo: String, onVolver: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(titulo)
        Text(stringResource(R.string.pantalla_pendiente))
        Button(onClick = onVolver) { Text(stringResource(R.string.volver)) }
    }
}
