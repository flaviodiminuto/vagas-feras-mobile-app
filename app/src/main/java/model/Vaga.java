package model;

import java.util.List;

public class Vaga {

    private  Long id;

    private String titulo;

    private String descricao;

    private Area area;

    private Segmento segmento;

    private Nivel nivel;

    private Double remuneracao_minima;

    private Double remumeracao_maxima;

    private List<Skill> requisitos;

    private List<Skill> desejaveis;

    private Vaga(Builder builder) {
        setId(builder.id);
        setTitulo(builder.titulo);
        setDescricao(builder.descricao);
        setArea(builder.area);
        setSegmento(builder.segmento);
        setNivel(builder.nivel);
        setRemuneracao_minima(builder.remuneracao_minima);
        setRemumeracao_maxima(builder.remumeracao_maxima);
        setRequisitos(builder.requisitos);
        setDesejaveis(builder.desejaveis);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Double getRemuneracao_minima() {
        return remuneracao_minima;
    }

    public void setRemuneracao_minima(Double remuneracao_minima) {
        this.remuneracao_minima = remuneracao_minima;
    }

    public Double getRemumeracao_maxima() {
        return remumeracao_maxima;
    }

    public void setRemumeracao_maxima(Double remumeracao_maxima) {
        this.remumeracao_maxima = remumeracao_maxima;
    }

    public List<Skill> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<Skill> requisitos) {
        this.requisitos = requisitos;
    }

    public List<Skill> getDesejaveis() {
        return desejaveis;
    }

    public void setDesejaveis(List<Skill> desejaveis) {
        this.desejaveis = desejaveis;
    }


    public static final class Builder {
        private Long id;
        private String titulo;
        private String descricao;
        private Area area;
        private Segmento segmento;
        private Nivel nivel;
        private Double remuneracao_minima;
        private Double remumeracao_maxima;
        private List<Skill> requisitos;
        private List<Skill> desejaveis;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder titulo(String val) {
            titulo = val;
            return this;
        }

        public Builder descricao(String val) {
            descricao = val;
            return this;
        }

        public Builder area(Area val) {
            area = val;
            return this;
        }

        public Builder segmento(Segmento val) {
            segmento = val;
            return this;
        }

        public Builder nivel(Nivel val) {
            nivel = val;
            return this;
        }

        public Builder remuneracao_minima(Double val) {
            remuneracao_minima = val;
            return this;
        }

        public Builder remumeracao_maxima(Double val) {
            remumeracao_maxima = val;
            return this;
        }

        public Builder requisitos(List<Skill> val) {
            requisitos = val;
            return this;
        }

        public Builder desejaveis(List<Skill> val) {
            desejaveis = val;
            return this;
        }

        public Vaga build() {
            return new Vaga(this);
        }
    }
}
