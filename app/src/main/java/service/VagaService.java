package service;

import android.content.Context;

import contratos.Inscrito;
import enumeradores.Rota;
import http.HttpRequestQueue;
import http.RequestGet;
import http.RequestPost;
import model.Vaga;

public class VagaService {

    private final Context context;

    private final Rota rota = Rota.VAGAS;

    public VagaService(Context context) {
        this.context = context;
    }

    public void find(Inscrito inscrito){
        RequestGet request = new RequestGet(inscrito, this.rota, context);
        HttpRequestQueue.send(request);
    }

//    public void findById(Long id){
//    }

    public void save(Inscrito inscrito, Vaga vaga){
        RequestPost<Vaga> request = new RequestPost<>(inscrito, this.rota, vaga, context);
        HttpRequestQueue.send(request);
    }

}
