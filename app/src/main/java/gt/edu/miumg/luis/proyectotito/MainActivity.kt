package gt.edu.miumg.luis.proyectotito

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import gt.edu.miumg.luis.proyectotito.ManejoDeCasillas.ToTiTo


class MainActivity : AppCompatActivity() {

    private val juego = ToTiTo()

    private var botones: Array<Button> =  arrayOf()

    private lateinit var jugadorUno: TextView
    private lateinit var jugadorDos: TextView
    private lateinit var jugadorGanador: TextView

    private lateinit var botonReiniciar: Button

    private var nombreDelGanador: String? = null
    private var simboloDelGanador: String? = null

//    var turno:Int = 1
//    var empate:Int = 0
//    var contadorJugadorUno:Int = 0
//    var contadorJugadorDos:Int = 0
//    var contadorEmpates:Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        botones = arrayOf(
            findViewById(R.id.botonUno),
            findViewById(R.id.botonDos),
            findViewById(R.id.botonTres),
            findViewById(R.id.botonCuatro),
            findViewById(R.id.botonCinco),
            findViewById(R.id.botonSeis),
            findViewById(R.id.botonSiete),
            findViewById(R.id.botonOcho),
            findViewById(R.id.botonNueve)
        )
        jugadorUno = findViewById(R.id.textViewNombreJugadorUno)
        jugadorDos = findViewById(R.id.textViewNombreJugadorDos)
        jugadorGanador = findViewById(R.id.textViewJugadorGanador)
        botonReiniciar = findViewById(R.id.buttonReiniciar)

        actualizarTurnoDelJugador()

        for (i in botones.indices) {
            botones[i].setOnClickListener {
                botones[i].setTextColor(Color.BLACK)
                botones[i].textSize = 30f
                val fila = i / 3
                val columna = i % 3
                if (juego.comprobarMovimiento(fila, columna)) {
                    // Actualiza la interfaz gráfica según el movimiento
                    val simboloDelJugador = if (juego.getTurnoDelJugador() == 1) "X" else "O"
                    botones[i].text = simboloDelJugador // Asigna "X" o "O" al botón
                    botones[i].isEnabled = false
                    animarBoton(botones[i], simboloDelJugador)
                    val ganador = juego.comprobarGanador()
                    if (ganador != 0) {
                        //botones[i].setTextColor(Color.BLACK)
                        desactivarTablero()
                        nombreDelGanador = if (ganador == 1) "Jugador 1" else "Jugador 2"
                        simboloDelGanador = simboloDelJugador
                        mostrarMensajeAlGanador()
                        //mostrarMensajeAlGanador(ganador)
                        Toast.makeText(this, "Hubo un Ganador", Toast.LENGTH_SHORT).show()
                    } else if (juego.comprobarTableroLleno()) {
                        // Tablero lleno (empate)
                        // Muestra un mensaje y reinicia el juego
                        //actualizarTurnoDelJugador(esEmpate = true)
                        actualizarTurnoDelJugador(esEmpate = true)
                        Toast.makeText(this, "El tablero se lleno, reinicia el juego", Toast.LENGTH_SHORT).show()
                    } else {
                        actualizarTurnoDelJugador()
                    }
                }
            }
        }
        botonReiniciar.setOnClickListener {
            reinicarJuego()
        }

//        var botonUno:Button = findViewById(R.id.botonUno)
//        botonUno.setOnClickListener {//
//            botonUno.textSize = 30f
//            botonUno.setTextColor(Color.BLUE)
//            botonUno.text = "X"
//            casillaUno() }
//
//        var botonDos:Button = findViewById(R.id.botonDos)
//        botonDos.setOnClickListener {
//            botonDos.textSize = 30f
//            botonDos.setTextColor(Color.RED)
//            botonDos.text = "O"
//            casillaDos() }
//
//        var botonTres:Button = findViewById(R.id.botonTres)
//        botonTres.setOnClickListener {
//            botonTres.textSize = 30f
//            botonTres.setTextColor(Color.RED)
//            botonTres.text = "O"
//            casillaTres() }
//
//        var botonCuatro:Button = findViewById(R.id.botonCuatro)
//        botonCuatro.setOnClickListener {
//            botonCuatro.textSize = 30f
//            botonCuatro.setTextColor(Color.BLUE)
//            botonCuatro.text = "X"
//            casillaCuatro() }
//
//        var botonCinco:Button = findViewById(R.id.botonCinco)
//        botonCinco.setOnClickListener {
//            botonCinco.textSize = 30f
//            botonCinco.setTextColor(Color.BLUE)
//            botonCinco.text = "X"
//            casillaCinco() }
//
//        var botonSeis:Button = findViewById(R.id.botonSeis)
//        botonSeis.setOnClickListener {
//            botonSeis.textSize = 30f
//            botonSeis.setTextColor(Color.RED)
//            botonSeis.text = "O"
//            casillaSeis()
//        }
//
//        var botonSiete:Button = findViewById(R.id.botonSiete)
//        botonSiete.setOnClickListener {
//            botonSiete.textSize = 30f
//            botonSiete.setTextColor(Color.RED)
//            botonSiete.text = "O"
//            casillaSiete()
//        }
//
//        var botonOcho:Button = findViewById(R.id.botonOcho)
//        botonOcho.setOnClickListener {
//            botonOcho.textSize = 30f
//            botonOcho.setTextColor(Color.BLUE)
//            botonOcho.text = "X"
//            casillaOcho()
//        }
//
//        var botonNueve:Button = findViewById(R.id.botonNueve)
//        botonNueve.setOnClickListener {
//            botonNueve.textSize = 30f
//            botonNueve.setTextColor(Color.BLUE)
//            botonNueve.text = "X"
//            casillaNueve()
//            if (botonUno.text == botonCinco.text && botonNueve.text == botonCinco.text
//                && botonNueve.text == botonUno.text){
//                Toast.makeText(this, "El jugador 1 ha ganado :D", Toast.LENGTH_SHORT).show()
//            }
//        }

        Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show()
        // La actividad se esta creando.
    }

//    private fun empate(esEmpate: Boolean = false) {
//        if (!esEmpate) {
//            val nombreDelJugador = if (juego.getTurnoDelJugador() == 1) "Jugador 1" else "Jugador 2"
//            val simboloDelJugador = if (juego.getTurnoDelJugador() == 1) "X" else "O"
//            jugadorUno.text = "$nombreDelJugador ($simboloDelJugador)"
//            jugadorDos.text = "$nombreDelJugador ($simboloDelJugador)"
//        }
//    }


    private fun animarBoton(boton: Button, simbolo: String) {
        val colorInicial = Color.WHITE
        val colorFinal = Color.BLACK
        val duracion = 500L // Duración de la animación en milisegundos

        val colorAnim = ValueAnimator.ofObject(ArgbEvaluator(), colorInicial, colorFinal)
        colorAnim.duration = duracion
        colorAnim.addUpdateListener { animator ->
            boton.setBackgroundColor(animator.animatedValue as Int)
        }

        // Animación de rotación del texto
        val rotationAnim = ObjectAnimator.ofFloat(boton, "rotation", 0f, 360f)
        rotationAnim.duration = duracion

        // Iniciar ambas animaciones
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(colorAnim, rotationAnim)
        animatorSet.start()

        if (simbolo == "X") {
            boton.setTextColor(Color.RED)
        }

        if (simbolo == "O"){
            boton.setTextColor(Color.CYAN)
        }
        // Cambiar el color del borde del texto
        //boton.setTextColor(Color.RED)
        boton.paint.strokeWidth = 10f
        boton.paint.style = Paint.Style.STROKE
        boton.paint.color = Color.WHITE
    }


    private fun reinicarJuego() {
        juego.reiniciarJuego()
        for (boton in botones) {
            boton.setBackgroundColor(Color.WHITE)
            boton.text = ""
            boton.isEnabled = true
        }
        nombreDelGanador = null
        simboloDelGanador = null
        jugadorGanador.text = "Jugador Ganador: por definir"

        actualizarTurnoDelJugador()
    }

    private fun actualizarTurnoDelJugador(esEmpate: Boolean = false) {
        if (!esEmpate){
            val nombreDelJugador = nombreDelGanador ?: if (juego.getTurnoDelJugador() == 1) "Jugador 1" else "Jugador 2"
            val simboloDelJugador = simboloDelGanador ?: if (juego.getTurnoDelJugador() == 1) "O" else "X"
            jugadorUno.text = "$nombreDelJugador ($simboloDelJugador)"
            jugadorDos.text = "$nombreDelJugador ($simboloDelJugador)"
        }
    }

    private fun mostrarMensajeAlGanador() {
        jugadorGanador.text = "$nombreDelGanador ($simboloDelGanador) ganó la partida"
    }

    private fun desactivarTablero() {
        for (boton in botones) {
            boton.isEnabled = false
        }
    }

    private fun agregarPuntos(){

    }

    private fun casillaNueve() {
        Toast.makeText(this, "Has presionado la casilla Nueve", Toast.LENGTH_SHORT).show()
    }

    private fun casillaOcho() {
        Toast.makeText(this, "Has presionado la casilla Ocho", Toast.LENGTH_SHORT).show()
    }

    private fun casillaSiete() {
        Toast.makeText(this, "Has presionado la casilla Siete", Toast.LENGTH_SHORT).show()
    }

    private fun casillaSeis() {
        Toast.makeText(this, "Has presionado la casilla Seis", Toast.LENGTH_SHORT).show()
    }

    private fun casillaCinco() {
        Toast.makeText(this, "Has presionado la casilla Cinco", Toast.LENGTH_SHORT).show()
    }

    private fun casillaCuatro() {
        Toast.makeText(this, "Has presionado la casilla Cuatro", Toast.LENGTH_SHORT).show()
    }

    private fun casillaTres() {
        Toast.makeText(this, "Has presionado la casilla Tres", Toast.LENGTH_SHORT).show()
    }

    private fun casillaDos() {
        Toast.makeText(this, "Has presionado la casilla Dos", Toast.LENGTH_SHORT).show()
    }

    private fun casillaUno() {
        Toast.makeText(this, "Has presionado la casilla uno", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show()
        // La actividad esta a punto de hacerse visible.
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show()
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show()
        // Enfocarse en otra actividad  (esta actividad esta a punto de ser "detenida").
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show()
        // La actividad ya no es visible (ahora esta "detenida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show()
        // La actividad esta a punto de ser destruida.
    }
}