package cl.maxramirez.android.restauranteapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.maxramirez.android.restauranteapp.model.CalculadoraPedidos
import cl.maxramirez.android.restauranteapp.model.Platillo

class MainActivity : AppCompatActivity() {
    private val pastelDeChoclo = Platillo("Pastel de Choclo", 12000)
    private val cazuela = Platillo("Cazuela", 10000)
    private val calculadora = CalculadoraPedidos()
    private var incluirPropina = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etCantidadPastel = findViewById<EditText>(R.id.etCantidadPastelDeChoclo)
        val etCantidadCazuela = findViewById<EditText>(R.id.etCantidadCazuela)
        val switchPropina = findViewById<Switch>(R.id.switchPropina)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        val actualizarTotales = {
            val subtotal = calculadora.calcularSubtotal(listOf(pastelDeChoclo, cazuela))
            val propina = calculadora.calcularPropina(subtotal, incluirPropina)
            val total = calculadora.calcularTotal(subtotal, propina)
            tvResultado.text = "Total: $$total (Propina: $$propina)"
        }

        etCantidadPastel.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                pastelDeChoclo.cantidad = s.toString().toIntOrNull() ?: 0
                actualizarTotales()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etCantidadCazuela.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                cazuela.cantidad = s.toString().toIntOrNull() ?: 0
                actualizarTotales()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            incluirPropina = isChecked
            actualizarTotales()
        }
    }
}
