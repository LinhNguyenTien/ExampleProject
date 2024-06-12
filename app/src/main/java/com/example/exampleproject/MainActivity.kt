package com.example.exampleproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.exampleproject.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: SharedPreferences
    private var KEY_ENVIRONMENT = "Environment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(KEY_ENVIRONMENT, MODE_PRIVATE)
        fetchDataSharedPreferences()
        binding.Submit.setOnClickListener {
            var selectedEnvironment = ""
            if(binding.DEV.isChecked) {
                selectedEnvironment = "DEV"
            }
            if(binding.SIT.isChecked) {
                selectedEnvironment = "SIT"
            }
            if(binding.UAT.isChecked) {
                selectedEnvironment = "UAT"
            }
            if(binding.PROD.isChecked) {
                selectedEnvironment = "PROD"
            }
            lifecycleScope.launch {
                changeEnvironment(selectedEnvironment)
                delay(1000L)
                restartApp()
            }
        }

    }

    private fun fetchDataSharedPreferences() {
        val environment = sharedPreferences.getString("Environment", null)
        Log.i("Environment", "$environment")
        var environmentStage: Environment? = null
        // Get environment from SharedPreferences
        if(environment != null) {
            when(environment) {
                "DEV" -> environmentStage = Environment.DEV
                "SIT" -> environmentStage = Environment.SIT
                "UAT" -> environmentStage = Environment.UAT
                "PROD" -> environmentStage = Environment.PROD
                else -> environmentStage = null
            }
            // Display environment properties
            if(environmentStage != null) {
                binding.apiurl.text = "API_URL: ${environmentStage.apiUrl}"
                binding.dburl.text = "DB_URL: ${environmentStage.databaseUrl}"
                binding.logginglevel.text = "LOGGING_LEVEL: ${environmentStage.loggingLevel}"
                binding.sharedPreferences.text = "SharedPreferences: ${environment}"
            }
            // Display Unknown if environmentStage is null
            else {
                binding.apiurl.text = "API_URL: Unknown"
                binding.dburl.text = "DB_URL: Unknown"
                binding.logginglevel.text = "Logging level: Unknown"
            }
        }
        else {
            // This is the first lauch of the app
            sharedPreferences.edit().putString("Environment", BuildConfig.Environment).apply()
            // Recall this function to load environment properties
            fetchDataSharedPreferences()
        }
    }

    fun changeEnvironment(environment: String) {
        val editor = sharedPreferences.edit()
        editor.putString("Environment", environment)
        editor.apply()
    }

    fun restartApp() {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
        Runtime.getRuntime().exit(0)
    }
}