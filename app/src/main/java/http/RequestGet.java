package http;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import contratos.Inscrito;
import enumeradores.Rota;

public class RequestGet {
    private final String uri;
    private final Context context;
    private final Inscrito inscrito;

    public RequestGet(Inscrito inscrito, Rota rota, Context context) {
        this.inscrito = inscrito;
        this.uri = "https://vagas-feras.herokuapp.com".concat(rota.toString());
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public String getUri() {
        return this.uri;
    }

    public void finish( String response) {
        inscrito.evento(response);
    }
}
