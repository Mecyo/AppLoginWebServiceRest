package br.edu.ifba.samuv.models;

public class Ferida {

    private int idFerida;
    private Doenca idDoenca;
    private CaracteristicaFerida caracteristica;

    public Ferida() { }

    public int getIdFerida() {
        return idFerida;
    }

    public void setIdFerida(int idFerida) {
        this.idFerida = idFerida;
    }

    public Doenca getIdDoenca() {
        return idDoenca;
    }

    public void setIdDoenca(Doenca doenca) {
        this.idDoenca = doenca;
    }

    public CaracteristicaFerida getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(CaracteristicaFerida caracteristica) {
        this.caracteristica = caracteristica;
    }
}
