package Database.Asignacion.Models;

public class Demanda {

    int idDemanda;
    int dato;
    int grafoId;

    public Demanda(int idDemanda, int dato, int grafoId) {
        this.idDemanda = idDemanda;
        this.dato = dato;
        this.grafoId = grafoId;
    }

    public Demanda(int dato, int grafoId) {
        this.dato = dato;
        this.grafoId = grafoId;
    }

    public int getIdDemanda() {
        return idDemanda;
    }

    public void setIdDemanda(int idDemanda) {
        this.idDemanda = idDemanda;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getGrafoId() {
        return grafoId;
    }

    public void setGrafoId(int grafoId) {
        this.grafoId = grafoId;
    }
}
