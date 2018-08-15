package br.edu.ifba.samuv.models;

public class Doenca {

    private int id;
    private Paciente paciente;

    public Doenca() {
    }

    public Doenca(int id, Paciente paciente, String tipo, String apelido) {
        this.id = id;
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
