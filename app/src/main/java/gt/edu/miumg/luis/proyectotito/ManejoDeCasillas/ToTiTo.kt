package gt.edu.miumg.luis.proyectotito.ManejoDeCasillas

class ToTiTo {
    private val tablero = Array(3) { IntArray(3) { 0 } } // Matriz 3x3 para el tablero
    private var turnoDelJugador:Int = 1 // Jugador actual (1 para X, 2 para O)

    fun comprobarMovimiento(fila: Int, columna: Int): Boolean {
        if (fila in 0..2 && columna in 0..2 && tablero[fila][columna] == 0) {
            tablero[fila][columna] = turnoDelJugador
            turnoDelJugador = 3 - turnoDelJugador
            return true
        }
        return false
    }

    fun comprobarGanador(): Int {
        // Verificar filas
        for (row in 0..2) {
            if (tablero[row][0] != 0 && tablero[row][0] == tablero[row][1] && tablero[row][1] == tablero[row][2]) {
                return tablero[row][0]
            }
        }

        // Verificar columnas
        for (col in 0..2) {
            if (tablero[0][col] != 0 && tablero[0][col] == tablero[1][col] && tablero[1][col] == tablero[2][col]) {
                return tablero[0][col]
            }
        }

        // Verificar diagonales
        if (tablero[0][0] != 0 && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return tablero[0][0]
        }
        if (tablero[0][2] != 0 && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return tablero[0][2]
        }
        // No hay ganador
        return 0
    }

    fun comprobarTableroLleno(): Boolean {
        return tablero.all { fila -> fila.all { cell -> cell != 0 } }
    }

    fun reiniciarJuego() {
        // Reinicia la matriz del tablero y el jugador actual
        for (fila in 0..2) {
            for (columna in 0..2) {
                tablero[fila][columna] = 0
            }
        }
    }

    fun getTurnoDelJugador(): Int{
        return turnoDelJugador
    }

}