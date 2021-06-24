package service;

import android.content.Context;

import java.lang.reflect.Type;
import java.util.List;

import contratos.Inscrito;
import enumeradores.Rota;
import http.HttpRequestQueue;
import http.RequestGet;
import http.RequestPost;
import model.Usuario;

public class UsuarioService {
    private final Context context;

    private final Rota rota = Rota.USUARIOS;

    public UsuarioService(Context context) {
        this.context = context;
    }

    public void find(Inscrito inscrito){
       RequestGet request = new RequestGet(inscrito, this.rota, context);
       HttpRequestQueue.send(request);
    }

    public void findById(Long id){
    }

    public void save(Inscrito inscrito, Usuario usuario){
        RequestPost<Usuario> request = new RequestPost<>(inscrito, Rota.USUARIO_LOGIN, usuario, context);
        HttpRequestQueue.send(request);
    }

    public void login(Inscrito inscrito, Usuario usuario){
        RequestPost<Usuario> request = new RequestPost<>(inscrito, Rota.USUARIO_LOGIN, usuario, context);
        HttpRequestQueue.send(request);
    }
}
