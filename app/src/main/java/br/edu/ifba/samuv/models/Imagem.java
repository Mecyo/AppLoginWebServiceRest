package br.edu.ifba.samuv.models;

import android.media.Image;

public class Imagem {

    private int id;
    private Atendimento atendimento;
    private String imageName;
    private Image foto;
    private Image segmented_image;


    public Imagem(int id, Atendimento atendimento, String imageName, Image foto, Image segmented_image) {
        this.id = id;
        this.atendimento = atendimento;
        this.imageName = imageName;
        this.foto = foto;
        this.segmented_image = segmented_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Image getSegmented_image() {
        return segmented_image;
    }

    public void setSegmented_image(Image segmented_image) {
        this.segmented_image = segmented_image;
    }
}
