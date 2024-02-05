package net.johnnyconsole.android.enigma

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import net.johnnyconsole.android.enigma.databinding.ActivityEnigmaSettingsBinding

class EnigmaSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnigmaSettingsBinding

    private class RotorAdapter(context: Context, val array: Array<String>): ArrayAdapter<String>(context, R.layout.layout_spinner) {
        override fun getCount(): Int {
            return array.size
        }

        override fun getItem(position: Int): String {
            return array[position]
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnigmaSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = getString(R.string.settings)

        val rotorAdapter = RotorAdapter(this, resources.getStringArray(R.array.alphabet))
        val reflectorAdapter = RotorAdapter(this, arrayOf("A", "B", "C"))

        binding.RotorL.adapter = rotorAdapter
        binding.RotorM.adapter = rotorAdapter
        binding.RotorR.adapter = rotorAdapter
        binding.RingL.adapter = rotorAdapter
        binding.RingM.adapter = rotorAdapter
        binding.RingR.adapter = rotorAdapter
        binding.Reflector.adapter = reflectorAdapter
    }
}