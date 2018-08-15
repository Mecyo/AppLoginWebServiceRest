package br.edu.ifba.samuv.models;

import java.util.Date;

public class Atendimento {

    private int id;
    private Tecnica tecnica;
    private Profissional profissional;
    private Ferida ferida;
    private CaracteristicaFerida caracteristicaFerida;
    private Date dataHora;
    private String nota;


    public Atendimento(int id, Tecnica tecnica, Profissional profissional, Doenca doenca, CaracteristicaFerida caracteristicaFerida, Date dataHora, String nota) {
        this.id = id;
        this.tecnica = tecnica;
        this.profissional = profissional;
        this.nota = nota;
        this.caracteristicaFerida = caracteristicaFerida;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tecnica getTecnica() {
        return tecnica;
    }

    public void setTecnica(Tecnica tecnica) {
        this.tecnica = tecnica;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public CaracteristicaFerida getCaracteristicaFerida() {
        return caracteristicaFerida;
    }

    public void setCaracteristicaFerida(CaracteristicaFerida caracteristicaFerida) {
        this.caracteristicaFerida = caracteristicaFerida;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Ferida getFerida() {
        return ferida;
    }

    public void setFerida(Ferida ferida) {
        this.ferida = ferida;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
