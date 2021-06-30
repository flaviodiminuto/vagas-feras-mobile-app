package service;

import android.content.Context;

import java.util.List;
import java.util.Optional;

import contratos.Inscrito;
import enumeradores.Rota;
import http.HttpRequestQueue;
import http.RequestGet;
import model.Skill;

public class SkillService {

    private final Context context;

    public SkillService(Context context){
        this.context = context;
    }
    public void find(Inscrito inscrito){
        RequestGet request = new RequestGet(inscrito, Rota.SKILLS, this.context);
        HttpRequestQueue.send(request);
    }

    public Skill byName(List<Skill> skillList, String name){
        Optional<Skill> skillOptionnal = skillList
                .stream()
                .filter(skill -> skill.getNome().equalsIgnoreCase(name))
                .findFirst();
        return skillOptionnal.orElse(null);
    }
}
