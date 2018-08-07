package br.edu.ifba.samuv.models;

import android.graphics.Color;
import android.hardware.Camera.Area;

public class CaracteristicaFerida {

    private int idCaracteristica;
    private Ferida ferida;
    private Area area;
    private Color cor;

    public CaracteristicaFerida(int idCaracteristica, Ferida ferida, Area area, Color cor) {
        this.idCaracteristica = idCaracteristica;
        this.ferida = ferida;
        this.area = area;
        this.cor = cor;
    }

    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public Ferida getFerida() {
        return ferida;
    }

    public void setFerida(Ferida ferida) {
        this.ferida = ferida;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}
