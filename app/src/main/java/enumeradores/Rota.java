package enumeradores;

import androidx.annotation.NonNull;

public enum Rota {
    VAGAS("/vagas"),
    USUARIOS("/usuarios"),
    USUARIO_LOGIN("/usuarios/login");

    private final String rota;

    Rota(String rota) {
        this.rota = rota;
    }

    @NonNull
    @Override
    public String toString() {
        return rota;
    }
}
