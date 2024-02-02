package net.johnnyconsole.android.enigma.algorithm

import java.io.Serializable

class Reflector(wiring: String): Serializable {

    companion object {
        val A = Reflector("EJMZALYXVBWFCRQUONTSPIKHGD")
        val B = Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT")
        val C = Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL")
    }

    private val left = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    private val right: CharArray

    init {
        right = wiring.toCharArray()
    }

    private fun find(array: CharArray, key: Char): Int {
        for(i in array.indices) {
            if(array[i] == key) return i
        }
        return -1
    }

    fun reflect(signal: Int): Int {
        return find(left, right[signal])
    }




}