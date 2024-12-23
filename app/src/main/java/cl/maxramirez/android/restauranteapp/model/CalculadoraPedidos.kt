package cl.maxramirez.android.restauranteapp.model

class CalculadoraPedidos {
    fun calcularSubtotal(platillos: List<Platillo>): Int {
        return platillos.sumOf { it.precio * it.cantidad }
    }

    fun calcularPropina(subtotal: Int, incluirPropina: Boolean): Int {
        return if (incluirPropina) (subtotal * 0.1).toInt() else 0
    }

    fun calcularTotal(subtotal: Int, propina: Int): Int {
        return subtotal + propina
    }
}
