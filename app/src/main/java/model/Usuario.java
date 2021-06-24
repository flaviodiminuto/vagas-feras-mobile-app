package model;

import android.text.Editable;

import androidx.annotation.Nullable;

public class Usuario{
    private  Long id;
    private String login;
    private String senha;
    private String email;

    public Usuario() {
    }

    public Usuario(@Nullable Long id, String login, String senha, String email) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
