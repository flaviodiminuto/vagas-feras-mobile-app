package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import contratos.Inscrito
import http.Response
import model.Usuario
import service.UsuarioService

class CadastroUsuario : AppCompatActivity(), Inscrito {

    lateinit var txt_pin : EditText
    lateinit var txt_login : EditText
    lateinit var txt_senha : EditText
    lateinit var txt_confirmar_senha : EditText
    lateinit var txt_email : EditText
    lateinit var acao : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)
        txt_pin = findViewById(R.id.cadastro_pin)
        txt_login = findViewById(R.id.cadastro_edt_login)
        txt_senha = findViewById(R.id.login_edt_senha)
        txt_confirmar_senha = findViewById(R.id.cadastro_confirma_senha)
        txt_email = findViewById(R.id.cadastro_email)
        acao = findViewById(R.id.cadastro_btn_cadastrar)

        val service = UsuarioService(this)

        acao.setOnClickListener {
            val login =  txt_login.text.toString()
            val senha = txt_senha.text.toString()
            val confirmarSenha = txt_confirmar_senha.text.toString()
            val email = txt_email.text.toString()

            if (senha != confirmarSenha) {
                Toast.makeText(this,
                        "Senha e confirmação são diferentes"
                        , Toast.LENGTH_LONG).show()
            }

            val usuario = Usuario(null,login, senha,  email)
            service.save(this, usuario)

        }

    }

    override fun evento(response: String) {
        if(!response.isBlank()) {
            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Não foi possível realizar o cadastro no momento", Toast.LENGTH_LONG).show()
        }
    }
}
