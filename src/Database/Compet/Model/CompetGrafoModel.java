package Database.Compet.Model;

public class CompetGrafoModel {
    int id;
    String nombre;

    public CompetGrafoModel(){

    }
    public CompetGrafoModel(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public CompetGrafoModel(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
