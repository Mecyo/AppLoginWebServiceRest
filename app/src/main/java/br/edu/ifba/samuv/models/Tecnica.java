package br.edu.ifba.samuv.models;

public class Tecnica {

    private int id;
    private String nomeTecnica;

    public Tecnica(int id, String nomeTecnica) {
        this.id = id;
        this.nomeTecnica = nomeTecnica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTecnica() {
        return nomeTecnica;
    }

    public void setNomeTecnica(String nomeTecnica) {
        this.nomeTecnica = nomeTecnica;
    }
}
