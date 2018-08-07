package br.edu.ifba.samuv.models;

public class Tecnica {

    private int idTecnica;
    private String nomeTecnica;

    public Tecnica(int idTecnica, String nomeTecnica) {
        this.idTecnica = idTecnica;
        this.nomeTecnica = nomeTecnica;
    }

    public int getIdTecnica() {
        return idTecnica;
    }

    public void setIdTecnica(int idTecnica) {
        this.idTecnica = idTecnica;
    }

    public String getNomeTecnica() {
        return nomeTecnica;
    }

    public void setNomeTecnica(String nomeTecnica) {
        this.nomeTecnica = nomeTecnica;
    }
}
