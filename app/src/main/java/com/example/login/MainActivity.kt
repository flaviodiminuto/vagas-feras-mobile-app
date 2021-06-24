package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import com.google.gson.Gson
import contratos.Inscrito
import enumeradores.IntentExtras
import model.Usuario
import service.UsuarioService
import java.lang.Exception

class MainActivity : AppCompatActivity(), Inscrito {
    lateinit var btn_cadastrar: TextView
    lateinit var btn_login: TextView
    lateinit var edt_login: EditText
    lateinit var edt_senha: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login = findViewById(R.id.btn_login)
        btn_cadastrar = findViewById(R.id.telaLoginCadastrarDestaque)
        edt_login = findViewById(R.id.cadastro_edt_login)
        edt_senha = findViewById(R.id.login_edt_senha)
        val service = UsuarioService(this)

        btn_login.setOnClickListener{
            val usuario = getUsuario()
            if(usuario != null)
                service.login(this, usuario)
        }

        btn_cadastrar.setOnClickListener{
            val intent = Intent(this, CadastroUsuario::class.java)
            startActivity(intent)
        }
    }

    private fun campos_validos(): Boolean {
        return edt_login.text.isNotBlank()
            && edt_senha.text.isNotBlank()
    }

    private fun getUsuario(): Usuario? {
        return if(campos_validos()) {
            Usuario(null, edt_login.text.toString(), edt_senha.text.toString(), null)
        }else
            null
    }

    override fun evento(response: String) {
        try {
            val usuarioRetorno : Usuario = Gson().fromJson(response, Usuario::class.java)
            val intent = Intent(this, Home::class.java)
            intent.putExtra(IntentExtras.USER_ID.get(), usuarioRetorno.id)
            startActivity(intent)
        }catch (exception: Exception){
            Toast.makeText(this, "Serviço indisponivel", Toast.LENGTH_LONG).show()
        }
    }
}