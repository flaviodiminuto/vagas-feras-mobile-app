package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class DetalheVaga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_vaga)
        val vagaString = intent.getStringExtra("vaga")
        Toast.makeText(this, vagaString, Toast.LENGTH_LONG).show()
    }
}