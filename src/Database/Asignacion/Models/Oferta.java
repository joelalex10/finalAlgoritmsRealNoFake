package Database.Asignacion.Models;

public class Oferta {

    int idOferta;
    int dato;
    int grafoId;

    public Oferta(int idOferta, int dato, int grafoId) {
        this.idOferta = idOferta;
        this.dato = dato;
        this.grafoId = grafoId;
    }

    public Oferta(int dato, int grafoId) {
        this.dato = dato;
        this.grafoId = grafoId;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
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
