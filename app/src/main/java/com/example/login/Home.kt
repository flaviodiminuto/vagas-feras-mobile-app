package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class Home : AppCompatActivity() {
    lateinit var card_cadastrar: CardView ;
    lateinit var card_painel_vagas: CardView ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        card_cadastrar = findViewById(R.id.home_card_cadastrar_vaga)
        card_painel_vagas = findViewById(R.id.home_card_painel_vaga)

        val intent = Intent(this, PainelVagas::class.java)
        card_cadastrar.setOnClickListener{
            startActivity(intent)
        }

        card_painel_vagas.setOnClickListener{
            startActivity(intent)
        }


    }
}