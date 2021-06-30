package service;

import android.content.Context;

import contratos.Inscrito;
import enumeradores.Rota;
import http.HttpRequestQueue;
import http.RequestGet;

public class SegmentoService {
    private final Context context;

    public SegmentoService(Context context) {
        this.context = context;
    }
    public void find(Inscrito inscrito, Long idSegmento){
        RequestGet request = new RequestGet(inscrito,
                String.format(Rota.SEGMENTOS_POR_AREA.toString(), idSegmento),
                this.context);
        HttpRequestQueue.send(request);
    }
}
