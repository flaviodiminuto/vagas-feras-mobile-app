package model;

public class Segmento{
    public Segmento(Long id, Area area, String nome, String descricao) {
        this.id = id;
        this.area = area;
        this.nome = nome;
        this.descricao = descricao;
    }

    private  Long id;

    private Area area;

    private String nome;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
