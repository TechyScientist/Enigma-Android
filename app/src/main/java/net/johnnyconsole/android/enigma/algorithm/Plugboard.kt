package net.johnnyconsole.android.enigma.algorithm

import java.io.Serializable

class Plugboard(pairs: Array<String>): Serializable {

    private val left = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    private val right = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

    init {
        for(pair in pairs) {
            val a = pair[0] - 'A'
            val b = pair[1] - 'A'
            val t = left[a]
            left[a] = left[b]
            left[b] = t
        }
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