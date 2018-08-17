package br.edu.ifba.samuv.models;

import java.util.Date;

public class Atendimento {

    private int id;
    private Profissional profissional;
    private Ferida ferida;
    private CaracteristicaFerida caracteristica;
    private Date dataHora;
    private String nota;


    public Atendimento() {
    }

    public Atendimento(int id, Ferida ferida, Profissional profissional, CaracteristicaFerida caracteristicaFerida, Date dataHora, String nota) {
        this.id = id;
        this.profissional = profissional;
        this.nota = nota;
        this.ferida = ferida;
        this.caracteristica = caracteristicaFerida;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Ferida getFerida() {
        return ferida;
    }

    public void setFerida(Ferida ferida) {
        this.ferida = ferida;
    }

    public CaracteristicaFerida getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(CaracteristicaFerida caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "id=" + id +
                ", profissional=" + profissional.toString() +
                ", ferida=" + ferida.toString() +
                ", caracteristica=" + caracteristica.toString() +
                ", dataHora=" + dataHora +
                ", nota='" + nota + '\'' +
                '}';
    }
}
