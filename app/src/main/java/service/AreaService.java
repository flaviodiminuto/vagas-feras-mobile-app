package service;

import android.content.Context;

import contratos.Inscrito;
import enumeradores.Rota;
import http.HttpRequestQueue;
import http.RequestGet;

public class AreaService {

    private final Context context;

    public AreaService(Context context){
        this.context = context;
    }
    public void findAreas(Inscrito inscrito){
        RequestGet request = new RequestGet(inscrito, Rota.AREAS, this.context);
        HttpRequestQueue.send(request);
    }
}
