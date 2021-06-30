package http;

import android.content.Context;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import contratos.Inscrito;
import enumeradores.Rota;

public class RequestGet {
    private final String baseUrl = "https://vagas-feras.herokuapp.com";
    private String uri;
    private Context context;
    private Inscrito inscrito;

    public RequestGet(Inscrito inscrito, Rota rota, Context context) {
        String url = baseUrl.concat(rota.toString());
        buildRequest(inscrito, url, context);
    }


    public RequestGet(Inscrito inscrito, String uri, Context context) {
        String url = baseUrl.concat(uri);
        buildRequest(inscrito, url, context);
    }

    public void buildRequest(Inscrito inscrito, String URL, Context context){
        this.uri = URL;
        this.inscrito = inscrito;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public String getUri() {
        return this.uri;
    }

    public void finish( String response) {
        inscrito.evento(new String(response.getBytes(), StandardCharsets.UTF_8));
    }
}
