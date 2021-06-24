package http;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import contratos.Inscrito;
import enumeradores.Rota;

public class RequestPost<T> extends RequestGet {

    private T entity;

    public RequestPost(Inscrito inscrito, Rota rota, T entity, Context context) {
        super(inscrito, rota,  context);
        this.entity = entity;
    }

    public String requestBody() {
        return new Gson().toJson(this.entity);
    }
}
