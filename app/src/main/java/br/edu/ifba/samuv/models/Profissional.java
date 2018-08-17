package br.edu.ifba.samuv.models;

public class Profissional extends Usuario {

    private int id;
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Profissional{" +
                "id=" + id +
                ", usuario=" + usuario.toString() +
                '}';
    }
}
