package model;

public class Nivel{
    public Nivel() {
    }

    public Nivel(Long id) {
        this.id = id;
    }

    public Nivel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Nivel(Long id, String nome, String descricao, Long hierarquia, Nivel nivel_inferior) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.hierarquia = hierarquia;
        this.nivel_inferior = nivel_inferior;
    }

    private  Long id;

    private String nome;

    private String descricao;

    private Long hierarquia;

    private Nivel nivel_inferior;

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

    public Long getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(Long hierarquia) {
        this.hierarquia = hierarquia;
    }

    public Nivel getNivel_inferior() {
        return nivel_inferior;
    }

    public void setNivel_inferior(Nivel nivel_inferior) {
        this.nivel_inferior = nivel_inferior;
    }
}
