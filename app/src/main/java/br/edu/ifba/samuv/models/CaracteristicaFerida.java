package br.edu.ifba.samuv.models;

import android.graphics.Color;
import android.hardware.Camera.Area;

public class CaracteristicaFerida {

    private int id;
    private double area;
    private String cor;

    public CaracteristicaFerida() {
    }

    public CaracteristicaFerida(int id, double area, String cor) {
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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "CaracteristicaFerida{" +
                "id=" + id +
                ", area=" + area +
                ", cor='" + cor + '\'' +
                '}';
    }
}
