package com.example.android_kt_retrofit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BienvenidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val btnPregunta1 = findViewById<Button>(R.id.btnPregunta1);
        val btnPregunta2 = findViewById<Button>(R.id.btnPregunta2);

        btnPregunta1.setOnClickListener{
            val productScreen = Intent(this, PantallaPrincipalActivity::class.java)
            startActivity(productScreen)
        };
        btnPregunta2.setOnClickListener{
            val productScreen = Intent(this, MainActivity::class.java)
            startActivity(productScreen)
        };

    };
}