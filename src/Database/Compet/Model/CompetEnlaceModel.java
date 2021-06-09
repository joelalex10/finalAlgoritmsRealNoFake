package Database.Compet.Model;

public class CompetEnlaceModel {
    int idEnlace;
    int red;
    int green;
    int blue;
    int idNodo1;
    int idNodo2;
    int idGrafo;
    public CompetEnlaceModel(){

    }

    public CompetEnlaceModel(int red, int green, int blue, int idNodo1, int idNodo2, int idGrafo) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.idNodo1 = idNodo1;
        this.idNodo2 = idNodo2;
        this.idGrafo = idGrafo;
    }

    public CompetEnlaceModel(int idEnlace, int red, int green, int blue, int idNodo1, int idNodo2, int idGrafo) {
        this.idEnlace = idEnlace;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.idNodo1 = idNodo1;
        this.idNodo2 = idNodo2;
        this.idGrafo = idGrafo;
    }

    public int getIdEnlace() {
        return idEnlace;
    }

    public void setIdEnlace(int idEnlace) {
        this.idEnlace = idEnlace;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getIdNodo1() {
        return idNodo1;
    }

    public void setIdNodo1(int idNodo1) {
        this.idNodo1 = idNodo1;
    }

    public int getIdNodo2() {
        return idNodo2;
    }

    public void setIdNodo2(int idNodo2) {
        this.idNodo2 = idNodo2;
    }

    public int getIdGrafo() {
        return idGrafo;
    }

    public void setIdGrafo(int idGrafo) {
        this.idGrafo = idGrafo;
    }

    @Override
    public String toString() {
        return "CompetEnlaceModel{" +
                "idEnlace=" + idEnlace +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", idNodo1=" + idNodo1 +
                ", idNodo2=" + idNodo2 +
                ", idGrafo=" + idGrafo +
                '}';
    }
}
