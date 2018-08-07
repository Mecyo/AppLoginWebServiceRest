package br.edu.ifba.samuv.models;

import android.media.Image;

public class Imagem {

    private int idImagem;
    private Atendimento atendimento;
    private String imageName;
    private Image foto;

    public Imagem(int idImagem, Atendimento atendimento, String imageName, Image foto) {
        this.idImagem = idImagem;
        this.atendimento = atendimento;
        this.imageName = imageName;
        this.foto = foto;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }
}
