package cl.maxramirez.android.restauranteapp.model

data class Platillo(
    val nombre: String,
    val precio: Int,
    var cantidad: Int = 0
)
