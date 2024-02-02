package net.johnnyconsole.android.enigma

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import net.johnnyconsole.android.enigma.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private class RotorAdapter(context: Context, val array: Array<String>): ArrayAdapter<String>(context, R.layout.layout_spinner) {
        override fun getCount(): Int {
            return array.size
        }

        override fun getItem(position: Int): String? {
            return array[position]
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RotorAdapter(this, resources.getStringArray(R.array.alphabet))

        binding.RotorL.adapter = adapter
        binding.RotorM.adapter = adapter
        binding.RotorR.adapter = adapter

    }

    @SuppressWarnings("discouragedApi")
    private fun clearLamps() {
        for(i in 0..25) {
            val id = resources.getIdentifier("Lamp" + ('A' + i), "id", packageName)
            binding.root.findViewById<TextView>(id).setTextColor(getColor(android.R.color.darker_gray))
        }
    }


}