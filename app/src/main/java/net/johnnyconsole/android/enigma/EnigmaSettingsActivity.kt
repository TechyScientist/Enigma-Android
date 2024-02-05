package net.johnnyconsole.android.enigma

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import net.johnnyconsole.android.enigma.databinding.ActivityEnigmaSettingsBinding

class EnigmaSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnigmaSettingsBinding

    private class RotorAdapter(context: Context, val array: Array<String>) :
        ArrayAdapter<String>(context, R.layout.layout_spinner) {
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

        val rotorAdapter = RotorAdapter(this, arrayOf("I", "II", "III", "IV", "V"))
        val ringAdapter = RotorAdapter(this, resources.getStringArray(R.array.alphabet))
        val reflectorAdapter = RotorAdapter(this, arrayOf("A", "B", "C"))

        binding.RotorL.adapter = rotorAdapter
        binding.RotorM.adapter = rotorAdapter
        binding.RotorR.adapter = rotorAdapter
        binding.RingL.adapter = ringAdapter
        binding.RingM.adapter = ringAdapter
        binding.RingR.adapter = ringAdapter
        binding.Reflector.adapter = reflectorAdapter

        binding.RotorM.setSelection(1)
        binding.RotorR.setSelection(2)

        binding.btBack.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.exit_confirm_title)
                .setMessage(R.string.exit_confirm)
                .setPositiveButton(getString(R.string.yes)) { _, _ -> finish() }
                .setNegativeButton(R.string.no, null)
                .create().show()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(this@EnigmaSettingsActivity)
                    .setTitle(R.string.exit_confirm_title)
                    .setMessage(R.string.exit_confirm)
                    .setPositiveButton(getString(R.string.yes)) { _, _ -> finish() }
                    .setNegativeButton(R.string.no, null)
                    .create().show()
            }
        })
    }
}