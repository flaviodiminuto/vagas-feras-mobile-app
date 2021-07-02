package service;

import android.content.Context;

import java.util.List;
import java.util.Optional;

import contratos.Inscrito;
import enumeradores.Rota;
import http.HttpRequestQueue;
import http.RequestGet;
import model.Area;

public class AreaService {

    private final Context context;
    private List<Area> areaList;

    public AreaService(Context context){
        this.context = context;
    }
    public void findAreas(Inscrito inscrito){
        RequestGet request = new RequestGet(inscrito, Rota.AREAS, this.context);
        HttpRequestQueue.send(request);
    }

    public Area findByName(List<Area> areaList, String nome){
        Optional<Area> area = areaList
                .stream()
                .filter(areaItem -> areaItem.getNome().equalsIgnoreCase(nome))
                .findFirst();
        return area.orElseGet(() -> new Area(1L, "", ""));
    }
}
