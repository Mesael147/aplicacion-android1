package com.example.trabajosdesarrollo.items

object ResistorData {
    val colors = listOf(
        "Negro",  // 0
        "Marrón", // 1
        "Rojo",   // 2
        "Naranja",// 3
        "Amarillo",// 4
        "Verde",  // 5
        "Azul",   // 6
        "Violeta",// 7
        "Gris",   // 8
        "Blanco"  // 9
    )

    val tolerances = listOf(
        "Dorado",  // ±5%
        "Plateado",// ±10%
        "Ninguno"  // ±20%
    )


    fun getToleranceValue(tolerance: String): String {
        return when (tolerance) {
            "Dorado" -> "±5%"
            "Plateado" -> "±10%"
            "Ninguno" -> "±20%"
            else -> "±20%"
        }
    }


    fun getMultiplier(band: Int): Int {
        return when (band) {
            0 -> 1
            1 -> 10
            2 -> 100
            3 -> 1000
            4 -> 10000
            5 -> 100000
            6 -> 1000000
            7 -> 10000000
            8 -> 100000000
            9 -> 1000000000
            else -> 1
        }
    }
}
