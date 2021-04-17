package cz.muni.fi.pv256.hw7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import cz.muni.fi.pv256.hw7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var selectedTheme = 1

    companion object {
        const val THEME_2_ACTIVE = "theme_2_active"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(THEME_2_ACTIVE, false)) {
            selectedTheme = 2
            setTheme(R.style.AppTheme2)
        }
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStyle1.setOnClickListener { checkAndSetTheme(1) }
        binding.btnStyle2.setOnClickListener { checkAndSetTheme(2) }
    }

    private fun checkAndSetTheme(number: Int) {
        when (number) {
            selectedTheme -> return
            1, 2 -> {
                PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(THEME_2_ACTIVE, number == 2).apply()
                selectedTheme = number
                finish()
                val intent = intent
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            else -> throw IllegalStateException("Theme number out of range")
        }
    }
}
