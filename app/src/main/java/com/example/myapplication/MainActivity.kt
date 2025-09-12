package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {

    // 👉 Reemplaza por tus coordenadas (temporalmente a mano)
    private val MY_LAT = -33.4500   // Tu latitud
    private val MY_LON = -70.6500   // Tu longitud

    // 👉 Reemplaza por la Plaza de Armas (bodega) de TU ciudad
    private val PLAZA_LAT = -33.4372
    private val PLAZA_LON = -70.6506

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1) Distancia (km) con Haversine
        val distanceKm = haversineKm(MY_LAT, MY_LON, PLAZA_LAT, PLAZA_LON)

        // 2) Reglas de negocio (prueba distintos montos)
        val totalCompra = 52_000
        val costoDespacho = calcularCostoDespacho(totalCompra, distanceKm)

        // 3) Evidencia para informe (Logcat: filtra por TAG = Semana5)
        Log.d("Semana5", "Distancia(km)=${"%.3f".format(distanceKm)}")
        Log.d("Semana5", "Total=$totalCompra -> CostoDespacho=$costoDespacho")

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaResumen(
                        distanceKm = distanceKm,
                        totalCompra = totalCompra,
                        costoDespacho = costoDespacho,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/** Haversine en kilómetros */
fun haversineKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val R = 6371.0 // Radio de la Tierra en km
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = sin(dLat / 2).pow(2.0) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2).pow(2.0)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return R * c
}

/** Reglas del caso de negocio */
fun calcularCostoDespacho(total: Int, distanciaKm: Double): Int {
    return when {
        total >= 50_000 && distanciaKm <= 20.0 -> 0
        total in 25_000..49_999 -> (150.0 * distanciaKm).roundToInt()
        else -> (300.0 * distanciaKm).roundToInt()
    }
}

@Composable
fun PantallaResumen(
    distanceKm: Double,
    totalCompra: Int,
    costoDespacho: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("Semana 5 – Despacho")
        Text("Distancia a Bodega: ${"%.3f".format(distanceKm)} km")
        Text("Total de compra: $$totalCompra")
        Text("Costo de despacho: $$costoDespacho")
        if (totalCompra >= 50_000 && distanceKm <= 20.0) {
            Text("✅ Despacho GRATIS (≥ $50.000 e ≤ 20 km)")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaResumen() {
    MyApplicationTheme {
        PantallaResumen(
            distanceKm = 1.234,
            totalCompra = 52_000,
            costoDespacho = 0
        )
    }
}
