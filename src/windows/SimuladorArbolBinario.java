
package windows;


import grafoArboles.Arbol;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SimuladorArbolBinario {

    Arbol miArbol = new Arbol();
    ArrayList<String> lista = new ArrayList<String>();

    public SimuladorArbolBinario() {
    }

    public boolean insertar(Integer dato) {
        lista.add(dato+"");
        return (this.miArbol.agregar(dato));
    }

    public String borrar(Integer dato) {
        Integer x = this.miArbol.borrar(dato);
        if (x == null) {
            return ("No existe el dato en el arbol");
        }
        lista.remove(dato+"");
        return ("Borrado el dato: " + x.toString());
    }

    public String preOrden() {
        ArrayList it = this.miArbol.preOrden();
        return (recorrido(it, "Recorrido PreOrden"));
    }

    public String inOrden() {
        ArrayList it = this.miArbol.inOrden();

        System.out.println(it);

        return (recorrido(it, "Recorrido InOrden"));
    }

    public ArrayList<String>listaOriginal(){
        System.out.println(lista);
        return lista;
    }

    public String postOrden() {
        ArrayList it = this.miArbol.postOrden();
        return (recorrido(it, "Recorrido PosOrden"));
    }

    public String imprimirPorNiveles() {
        ArrayList it = this.miArbol.impNiveles();
        return (recorrido(it, "Imprimir Por niveles"));
    }

    public String darHojas() {
        ArrayList it = this.miArbol.getHojas();
        return (recorrido(it, "Hojas del Arbol"));
    }

    public String darPadre(Integer hijo) {
        if (this.miArbol.getRaiz().getDato() == (hijo)) {
            return ("La raiz no tiene padre");
        }
        Integer padre = this.miArbol.padre(hijo);
        if (padre == null) {
            return ("No existe el Dato: " + hijo.toString());
        }
        return ("El padre de: " + hijo + "\n es : " + padre.toString());
    }

    public String esta(Integer dato) {
        boolean siEsta = this.miArbol.buscar(dato);
        String r = "El dato:" + dato.toString() + "\n";
        r += siEsta ? "Si se encuentra en el arbol" : "No se encuentra en el arbol";
        return (r);
    }

    private String recorrido(ArrayList it, String msg) {
        int i = 0;
        String r = msg + "\n";
        while (i < it.size()) {
            r += "\t" + it.get(i).toString() + "\n";
            i++;
        }
        return (r);
    }
    
    public String CantidadNodos(){
        return this.miArbol.cantidadNodos();
    }
    
    public String CantidadHojas(){
        return this.miArbol.cantidadNodosHoja();
    }
    public String alturaArbol(){
        return this.miArbol.retornarAltura();
    }
    public String menorValor(){
        return this.miArbol.menorValor();
    }
    public String mayorValor(){
        return this.miArbol.mayorValor();
    }
    public String balance(){
        return this.miArbol.imprimirBalance();
    }
    public String borrarMenor(){
        return this.miArbol.borrarMenor();
    }
    public String borrarMayor(){
        return this.miArbol.borrarMayor();
    }
    public String porNivel(){
        this.miArbol.alturaArbol();
        ArrayList it = this.miArbol.imprimirNivel();
        return (recorrido(it, "Imprimir Por niveles en orden!!!"));
    }
    public String cambiar() {
        this.miArbol.cambiar();
        return "Valores cambiados!!!";
    }
    public String ramaMayor() {
        ArrayList it = this.miArbol.ObtenerRamamayor();
        return (recorrido(it, "Rama(s) con mas valores"));
    }
    

    public void podarArbol() {
        this.miArbol.podar();
    }

    public void eliminar() {
        lista.clear();
        this.miArbol.eliminar();
    }

    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
