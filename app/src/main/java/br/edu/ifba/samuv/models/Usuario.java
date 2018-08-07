package br.edu.ifba.samuv.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Usuario {

    private int idUsuario;
    private String nomeUsuario;
    private String login;
    private String email;
    private String senha;


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /*@Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }*/

    /*public String toJson() {
        return "{" +
                "\"idUsuario\":" + idUsuario +
                ", \"nomeUsuario\":\"" + nomeUsuario + "\"" +
                ", \"login\":\"" + login + "\"" +
                ", \"email\":\"" + email + "\"" +
                ", \"senha\":\"" + senha + "\"" +
                "}";
    }*/
}
