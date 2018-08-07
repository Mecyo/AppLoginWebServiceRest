package br.edu.ifba.samuv.models;

public class Ferida extends Doenca {

    private int idFerida;
    private Doenca doenca;
    private CaracteristicaFerida caracteristica;


    public Ferida(int idDoenca, Paciente paciente, String tipo, String apelido, int idFerida, Doenca doenca, CaracteristicaFerida caracteristica) {
        super(idDoenca, paciente, tipo, apelido);
        this.idFerida = idFerida;
        this.doenca = doenca;
        this.caracteristica = caracteristica;
    }

    public int getIdFerida() {
        return idFerida;
    }

    public void setIdFerida(int idFerida) {
        this.idFerida = idFerida;
    }

    public Doenca getDoenca() {
        return doenca;
    }

    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    public CaracteristicaFerida getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(CaracteristicaFerida caracteristica) {
        this.caracteristica = caracteristica;
    }
}
