package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PainelVagas : AppCompatActivity() {
    lateinit var divulgar_vaga: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_painel_vagas)
        divulgar_vaga = findViewById(R.id.txt_divulgar_vaga)

        divulgar_vaga.setOnClickListener{
            val intent = Intent(this, FormularioVaga::class.java)
            startActivity(intent)
        }
    }
}