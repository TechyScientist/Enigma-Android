package net.johnnyconsole.android.enigma.algorithm

import java.io.Serializable

class Enigma(val plugboard: Plugboard,
             val left: Rotor,
             val middle: Rotor,
             val right: Rotor,
             val reflector: Reflector): Serializable {

    val keyboard = Keyboard()

    fun encipher(letter: Char): Char {
        if (!Character.isAlphabetic(letter.code)) return letter
        if (middle.isOnNotch() && right.isOnNotch()) {
            left.rotate()
            middle.rotate()
            right.rotate()
        } else if (middle.isOnNotch()) {    //Double Step Anomaly
            left.rotate()
            middle.rotate()
            right.rotate()
        } else if (right.isOnNotch()) {
            middle.rotate()
            right.rotate()
        } else {
            right.rotate()
        }
        var signal = keyboard.forward(Character.toUpperCase(letter))
        signal = plugboard.forward(signal)
        signal = right.forward(signal)
        signal = middle.forward(signal)
        signal = left.forward(signal)
        signal = reflector.reflect(signal)
        signal = left.reverse(signal)
        signal = middle.reverse(signal)
        signal = right.reverse(signal)
        signal = plugboard.reverse(signal)
        return keyboard.reverse(signal)
    }

    fun encipher(string: String): String {
        val ciphertext = StringBuilder()
        for(char in string) {
            ciphertext.append(encipher(char))
        }
        return ciphertext.toString()
    }

    fun setRotorPositions(positions: String) {
        left.rotateTo(positions[0])
        middle.rotateTo(positions[1])
        right.rotateTo(positions[2])
    }

    fun setRingPositions(positions: String) {
        left.setRingPosition(positions[0])
        middle.setRingPosition(positions[1])
        right.setRingPosition(positions[2])
    }


}