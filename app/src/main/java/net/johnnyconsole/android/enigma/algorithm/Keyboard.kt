package net.johnnyconsole.android.enigma.algorithm

import java.io.Serializable

class Keyboard: Serializable {

    fun forward(letter: Char): Int {
        return letter - 'A'
    }

    fun reverse(signal: Int): Char {
        return (signal + 'A'.code).toChar()
    }

}