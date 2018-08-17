package br.edu.ifba.samuv.models;

public class Ferida {

    private int pk;
    private Paciente paciente;
    private String apelido;

    public Ferida() { }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @Override
    public String toString() {
        return "Ferida{" +
                "pk=" + pk +
                ", paciente=" + paciente.toString() +
                ", apelido='" + apelido + '\'' +
                '}';
    }
}
