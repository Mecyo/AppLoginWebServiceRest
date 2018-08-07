package br.edu.ifba.samuv.models;

import java.util.Date;

public class Atendimento {

    private int idAtendimento;
    private Tecnica tecnica;
    private Profissional profissional;
    private Doenca doenca;
    private CaracteristicaFerida caracteristicaFerida;
    private Date dataHora;

    public Atendimento(int idAtendimento, Tecnica tecnica, Profissional profissional, Doenca doenca, CaracteristicaFerida caracteristicaFerida, Date dataHora) {
        this.idAtendimento = idAtendimento;
        this.tecnica = tecnica;
        this.profissional = profissional;
        this.doenca = doenca;
        this.caracteristicaFerida = caracteristicaFerida;
        this.dataHora = dataHora;
    }

    public int getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(int idAtendimento) {
        this.idAtendimento = idAtendimento;
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

    public Doenca getDoenca() {
        return doenca;
    }

    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
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
}
