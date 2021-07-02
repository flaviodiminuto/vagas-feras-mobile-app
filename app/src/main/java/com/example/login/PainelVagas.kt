package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import contratos.Inscrito
import model.Vaga
import service.VagaService
import service.adapter.PainelVagaAdapter
import java.lang.Exception

class PainelVagas : AppCompatActivity(), Inscrito{
    var vagas: List<Vaga> = mutableListOf()
    lateinit var adapter: PainelVagaAdapter
    lateinit var vagaService: VagaService

    lateinit var divulgar_vaga: TextView
    lateinit var recicler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_painel_vagas)
        divulgar_vaga = findViewById(R.id.txt_divulgar_vaga)
        this.adapter = PainelVagaAdapter(this.vagas)
        this.vagaService = VagaService(this)

        this.recicler = findViewById(R.id.painel_vagas_recycler)
        this.recicler.adapter = this.adapter
        this.recicler.layoutManager = LinearLayoutManager(this)
        vagaService.find(this)

        divulgar_vaga.setOnClickListener{
            val intent = Intent(this, FormularioVaga::class.java)
            startActivity(intent)
        }
    }

    override fun evento(response: String?) {
        var list = mutableListOf<Vaga>()
        try{
            list = Gson().fromJson(response,Array<Vaga>::class.java).toMutableList()
        }catch (ex: Exception){
            ex.printStackTrace()
        }
        this.vagas = list
        adapter.update(this.vagas)
    }
}