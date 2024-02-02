package net.johnnyconsole.android.enigma.algorithm

import java.io.Serializable


class Rotor(wiring: String, var notch: Char): Serializable {

    companion object {
        val I = Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 'Q')
        val II = Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 'E')
        val III = Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')
        val IV = Rotor("ESOVPZJAYQUIRHXLNFTGKDCMWB", 'J')
        val V = Rotor("VZBRGITYUPSDNHLXAWMJQOFECK", 'Z')
    }

    private val left = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    private val right: CharArray

    init {
        right = wiring.toCharArray()
    }

    fun rotate() {
        val l = left[0]
        val r = right[0]
        for(i in left.indices) {
            left[i] = left[i + 1]
            right[i] = right[i + 1]
        }
        left[left.size - 1] = l
        right[right.size - 1] = r
    }

    private fun reverseRotate(n: Int) {
        var times = n
        while(times-- > 0) {
            val l = left[25]
            val r = right[25]

            for (i in left.size - 1 downTo 1) {
                left[i] = left[i - 1]
                right[i] = right[i - 1]
            }
            left[0] = l
            right[0] = r
        }
    }

    fun isOnNotch(): Boolean {
        return left[0] == notch
    }

    fun rotateTo(letter: Char) {
        for (i in 0 until letter - 'A') {
            rotate()
        }
    }

    fun setRingPosition(position: Char) {
        val n = position - 'A'
        val nNotch = notch.code - 'A'.code
        reverseRotate(n)
        notch = (Math.abs((nNotch - n) % 26) + 'A'.code).toChar()
    }

    private fun find(array: CharArray, key: Char): Int {
        for(i in array.indices) {
            if(array[i] == key) return i
        }
        return -1
    }

    fun forward(signal: Int): Int {
        return find(left, right[signal])
    }

    fun reverse(signal: Int): Int {
        return find(right, left[signal])
    }

}