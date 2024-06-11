package com.example.exampleproject

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.exampleproject.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = binding.name
        val phone = binding.phone
        val button = binding.button
        val content = binding.content
        button.setOnClickListener {
            val nameText = name.text.toString()
            val phoneText = Integer.valueOf(phone.text.toString())
            content.text = "Name: $nameText\nPhone: $phoneText"
        }
    }
}