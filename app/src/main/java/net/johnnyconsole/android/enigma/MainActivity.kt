package net.johnnyconsole.android.enigma

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import net.johnnyconsole.android.enigma.algorithm.Enigma
import net.johnnyconsole.android.enigma.algorithm.Plugboard
import net.johnnyconsole.android.enigma.databinding.ActivityMainBinding
import net.johnnyconsole.android.enigma.algorithm.Reflector.Companion.A
import net.johnnyconsole.android.enigma.algorithm.Reflector.Companion.B
import net.johnnyconsole.android.enigma.algorithm.Reflector.Companion.C
import net.johnnyconsole.android.enigma.algorithm.Rotor.Companion.I
import net.johnnyconsole.android.enigma.algorithm.Rotor.Companion.II
import net.johnnyconsole.android.enigma.algorithm.Rotor.Companion.III
import net.johnnyconsole.android.enigma.algorithm.Rotor.Companion.IV
import net.johnnyconsole.android.enigma.algorithm.Rotor.Companion.V

class MainActivity : AppCompatActivity() {

    private class RotorAdapter(context: Context, val array: Array<String>): ArrayAdapter<String>(context, R.layout.layout_spinner) {
        override fun getCount(): Int {
            return array.size
        }

        override fun getItem(position: Int): String {
            return array[position]
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var enigma: Enigma

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enigma = Enigma(Plugboard(arrayOf("AB", "CD", "EF", "GH", "IJ", "KL", "MN", "OP", "QR", "ST")), I, II, III, A)

        val adapter = RotorAdapter(this, resources.getStringArray(R.array.alphabet))

        binding.RotorL.adapter = adapter
        binding.RotorM.adapter = adapter
        binding.RotorR.adapter = adapter

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("enigma", enigma)
    }

    override fun onRestoreInstanceState(inState: Bundle) {
        super.onRestoreInstanceState(inState)
        enigma = inState.getSerializable("enigma", Enigma::class.java)!!
    }

    fun onLetterKeyPressed(view: View) {
        clearLamps()
        val letter = (view as Button).text[0]
        val lamp = binding.root.findViewById<TextView>(resources.getIdentifier("Lamp$letter", "id", packageName))
        lamp.setTextColor(getColor(R.color.yellow))
    }

    @SuppressWarnings("discouragedApi")
    private fun clearLamps() {
        for(i in 0..25) {
            val id = resources.getIdentifier("Lamp" + ('A' + i), "id", packageName)
            binding.root.findViewById<TextView>(id).setTextColor(getColor(android.R.color.darker_gray))
        }
    }


}