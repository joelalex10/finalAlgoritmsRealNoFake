package Algoritmos;

import grafos.Enlace;
import grafos.Nodo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

@SuppressWarnings("serial")
public class GrafoNoroeste extends JPanel {

    private JButton btnMatriz, camino, john;
    private JTextField[][]matrix;
    private boolean dirigido; // Indica si es dirigido o no.
    private int maxNodos; // Tama�o m�ximo de la tabla.
    private int numNodos; // N�mero de v�rtices del grafo.
    private int  matrizAdy [][]; // Matriz de adyacencias del grafo.
    private int opcion;

    private int  matrizCostos [][];
    private int  matrizSolu [][];
    private int fi; // Tama�o m�ximo de la tabla.
    private int co; // Tama�o m�ximo de la tabla.

    private Vector<Nodo> nodos;
    private Vector<Enlace> enlaces;
    private Point p1, p2;

    private int inicio;

    private int fina;
    private int c;
    private int f=0;
    public GrafoNoroeste(boolean d) {
        maxNodos = numNodos = c = 0;
        dirigido = d;
        ejecutar();
    }

    private void ejecutar() {
        /**
        setLayout(new BorderLayout());
        nodos = new Vector<Nodo>();
        aristas = new Vector<Arista>();
        addMouseListener(this);*/


    }


}