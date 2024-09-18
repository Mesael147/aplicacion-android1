package com.example.trabajosdesarrollo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trabajosdesarrollo.ui.theme.TrabajosDesarrolloTheme

@Composable
fun Interfaz() {
    // Colores para las bandas de la resistencia
    val bandColors = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    // Tolerancias
    val toleranceColors = listOf("Dorado", "Plateado", "Ninguno")


    var selectedBand1 by remember { mutableStateOf("") }
    var selectedBand2 by remember { mutableStateOf("") }
    var selectedMultiplier by remember { mutableStateOf("") }
    var selectedTolerance by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calculadora de Resistencias", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenuComponent("Banda 1", bandColors) { selectedBand1 = bandColors[it] }
        DropdownMenuComponent("Banda 2", bandColors) { selectedBand2 = bandColors[it] }
        DropdownMenuComponent("Multiplicador", bandColors) { selectedMultiplier = bandColors[it] }
        DropdownMenuComponent("Tolerancia", toleranceColors) { selectedTolerance = toleranceColors[it] }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            result = calculateResistance(selectedBand1, selectedBand2, selectedMultiplier, selectedTolerance)
        }) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (result.isNotEmpty()) {
            Text(text = result, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun DropdownMenuComponent(label: String, items: List<String>, onItemSelected: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    Column {
        Text(label)
        Spacer(modifier = Modifier.height(4.dp))

        Button(onClick = { expanded = true }) {
            Text(if (selectedText.isEmpty()) "Seleccionar" else selectedText)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    selectedText = item
                    onItemSelected(index)
                    expanded = false
                }) {
                    Text(text = item)
                }
            }
        }
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {

}

fun calculateResistance(band1: String, band2: String, multiplier: String, tolerance: String): String {
    val colorValues = mapOf(
        "Negro" to 0, "Marrón" to 1, "Rojo" to 2, "Naranja" to 3, "Amarillo" to 4,
        "Verde" to 5, "Azul" to 6, "Violeta" to 7, "Gris" to 8, "Blanco" to 9
    )

    val multiplierValues = mapOf(
        "Negro" to 1, "Marrón" to 10, "Rojo" to 100, "Naranja" to 1_000,
        "Amarillo" to 10_000, "Verde" to 100_000, "Azul" to 1_000_000,
        "Violeta" to 10_000_000, "Gris" to 100_000_000, "Blanco" to 1_000_000_000
    )

    val toleranceValues = mapOf(
        "Dorado" to "±5%", "Plateado" to "±10%", "Ninguno" to "±20%"
    )

    val firstDigit = colorValues[band1] ?: return "Error en Banda 1"
    val secondDigit = colorValues[band2] ?: return "Error en Banda 2"
    val multiplierValue = multiplierValues[multiplier] ?: return "Error en Multiplicador"
    val toleranceValue = toleranceValues[tolerance] ?: return "Error en Tolerancia"

    val resistanceValue = (firstDigit * 10 + secondDigit) * multiplierValue
    return "$resistanceValue Ω $toleranceValue"
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrabajosDesarrolloTheme {
        Interfaz()
    }
}