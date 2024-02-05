package net.johnnyconsole.android.enigma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.johnnyconsole.android.enigma.databinding.ActivityEnigmaSettingsBinding

class EnigmaSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnigmaSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnigmaSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}