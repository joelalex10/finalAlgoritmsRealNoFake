package Database.Compet.Model;

public class CompetNodoModel {
    int id;
    int idGrafo;
    int x;
    int y;
    int a;
    int b;
    double xx;
    double yy;
    String nombre;
    int red;
    int green;
    int blue;
    public CompetNodoModel(){

    }

    public CompetNodoModel(int id, int idGrafo, int x, int y, int a, int b, double xx, double yy, String nombre, int red, int green, int blue) {
        this.id = id;
        this.idGrafo = idGrafo;
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.xx = xx;
        this.yy = yy;
        this.nombre = nombre;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public CompetNodoModel(int idGrafo, int x, int y, int a, int b, double xx, double yy, String nombre, int red, int green, int blue) {
        this.idGrafo = idGrafo;
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.xx = xx;
        this.yy = yy;
        this.nombre = nombre;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGrafo() {
        return idGrafo;
    }

    public void setIdGrafo(int idGrafo) {
        this.idGrafo = idGrafo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public double getXx() {
        return xx;
    }

    public void setXx(double xx) {
        this.xx = xx;
    }

    public double getYy() {
        return yy;
    }

    public void setYy(double yy) {
        this.yy = yy;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
