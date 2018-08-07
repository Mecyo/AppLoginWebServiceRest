package br.edu.ifba.samuv.models;

public abstract class Doenca {

    private int idDoenca;
    private Paciente paciente;
    private String tipo;
    private String apelido;

    public Doenca(int idDoenca, Paciente paciente, String tipo, String apelido) {
        this.idDoenca = idDoenca;
        this.paciente = paciente;
        this.tipo = tipo;
        this.apelido = apelido;
    }

    public int getIdDoenca() {
        return idDoenca;
    }

    public void setIdDoenca(int idDoenca) {
        this.idDoenca = idDoenca;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
}
