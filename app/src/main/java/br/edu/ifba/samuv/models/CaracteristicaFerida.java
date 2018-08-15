package br.edu.ifba.samuv.models;

import android.graphics.Color;
import android.hardware.Camera.Area;

public class CaracteristicaFerida {

    private int id;
    private Area area;
    private Color cor;

    public CaracteristicaFerida(int id, Ferida ferida, Area area, Color cor) {
        this.id = id;
        this.area = area;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
