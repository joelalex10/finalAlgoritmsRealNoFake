package Algoritmos;

import grafos.Enlace;
import grafos.Nodo;

import java.awt.*;
import java.util.Vector;

public class MainJohnson {
    public static void main(String args[]){
        Vector<Nodo> listNodo= new Vector<>();
        Vector<Enlace>listEnlace= new Vector<>();

        llenar(listNodo,listEnlace);
    }

    public static void llenar(Vector<Nodo> listNodo, Vector<Enlace> listEnlace){

        Nodo nodo1 = new Nodo();
        nodo1.setX(0);
        nodo1.setY(0);
        nodo1.setNombre("A");
        nodo1.setColor(Color.blue);
        nodo1.setNroActividad(listNodo.size());
        listNodo.add(nodo1);

        Nodo nodo2 = new Nodo();
        nodo2.setX(0);
        nodo2.setY(0);
        nodo2.setNombre("B");
        nodo2.setColor(Color.blue);
        nodo2.setNroActividad(listNodo.size());
        listNodo.add(nodo2);

        Nodo nodo3 = new Nodo();
        nodo3.setX(0);
        nodo3.setY(0);
        nodo3.setNombre("C");
        nodo3.setColor(Color.blue);
        nodo3.setNroActividad(listNodo.size());
        listNodo.add(nodo3);

        Nodo nodo4 = new Nodo();
        nodo4.setX(0);
        nodo4.setY(0);
        nodo4.setNombre("D");
        nodo4.setColor(Color.blue);
        nodo4.setNroActividad(listNodo.size());
        listNodo.add(nodo4);

        Nodo nodo5 = new Nodo();
        nodo5.setX(0);
        nodo5.setY(0);
        nodo5.setNombre("E");
        nodo5.setColor(Color.blue);
        nodo5.setNroActividad(listNodo.size());
        listNodo.add(nodo5);

        Nodo nodo6 = new Nodo();
        nodo6.setX(0);
        nodo6.setY(0);
        nodo6.setNombre("F");
        nodo6.setColor(Color.blue);
        nodo6.setNroActividad(listNodo.size());
        listNodo.add(nodo6);

        Nodo nodo7 = new Nodo();
        nodo7.setX(0);
        nodo7.setY(0);
        nodo7.setNombre("G");
        nodo7.setColor(Color.blue);
        nodo7.setNroActividad(listNodo.size());
        listNodo.add(nodo7);

        Nodo nodo8 = new Nodo();
        nodo8.setX(0);
        nodo8.setY(0);
        nodo8.setNombre("H");
        nodo8.setColor(Color.blue);
        nodo8.setNroActividad(listNodo.size());
        listNodo.add(nodo8);

        Enlace enlace1 = new Enlace();
        enlace1.setX1(0);
        enlace1.setY1(0);
        enlace1.setX2(0);
        enlace1.setY2(0);
        enlace1.setAtributo(6);
        enlace1.setTextcolor(Color.green);
        enlace1.setNroActividadNodoInicio(listNodo.get(0).getNroActividad());
        enlace1.setNroActividadNodoFin(listNodo.get(1).getNroActividad());
        enlace1.setThickness(1);
        enlace1.setTextDirection(20);
        enlace1.setNodoInicio(listNodo.get(0));
        enlace1.setNodoFin(listNodo.get(1));
        enlace1.setCiclo(true);
        listEnlace.add(enlace1);

        Enlace enlace2 = new Enlace();
        enlace2.setX1(0);
        enlace2.setY1(0);
        enlace2.setX2(0);
        enlace2.setY2(0);
        enlace2.setAtributo(7);
        enlace2.setTextcolor(Color.green);
        enlace2.setNroActividadNodoInicio(listNodo.get(0).getNroActividad());
        enlace2.setNroActividadNodoFin(listNodo.get(2).getNroActividad());
        enlace2.setThickness(1);
        enlace2.setTextDirection(20);
        enlace2.setNodoInicio(listNodo.get(0));
        enlace2.setNodoFin(listNodo.get(2));
        enlace2.setCiclo(true);
        listEnlace.add(enlace2);

        Enlace enlace3 = new Enlace();
        enlace3.setX1(0);
        enlace3.setY1(0);
        enlace3.setX2(0);
        enlace3.setY2(0);
        enlace3.setAtributo(1);
        enlace3.setTextcolor(Color.green);
        enlace3.setNroActividadNodoInicio(listNodo.get(1).getNroActividad());
        enlace3.setNroActividadNodoFin(listNodo.get(3).getNroActividad());
        enlace3.setThickness(1);
        enlace3.setTextDirection(20);
        enlace3.setNodoInicio(listNodo.get(1));
        enlace3.setNodoFin(listNodo.get(3));
        enlace3.setCiclo(true);
        listEnlace.add(enlace3);

        Enlace enlace4 = new Enlace();
        enlace4.setX1(0);
        enlace4.setY1(0);
        enlace4.setX2(0);
        enlace4.setY2(0);
        enlace4.setAtributo(8);
        enlace4.setTextcolor(Color.green);
        enlace4.setNroActividadNodoInicio(listNodo.get(2).getNroActividad());
        enlace4.setNroActividadNodoFin(listNodo.get(3).getNroActividad());
        enlace4.setThickness(1);
        enlace4.setTextDirection(20);
        enlace4.setNodoInicio(listNodo.get(2));
        enlace4.setNodoFin(listNodo.get(3));
        enlace4.setCiclo(true);
        listEnlace.add(enlace4);

        Enlace enlace5 = new Enlace();
        enlace5.setX1(0);
        enlace5.setY1(0);
        enlace5.setX2(0);
        enlace5.setY2(0);
        enlace5.setAtributo(6);
        enlace5.setTextcolor(Color.green);
        enlace5.setNroActividadNodoInicio(listNodo.get(3).getNroActividad());
        enlace5.setNroActividadNodoFin(listNodo.get(4).getNroActividad());
        enlace5.setThickness(1);
        enlace5.setTextDirection(20);
        enlace5.setNodoInicio(listNodo.get(3));
        enlace5.setNodoFin(listNodo.get(4));
        enlace5.setCiclo(true);
        listEnlace.add(enlace5);

        Enlace enlace6 = new Enlace();
        enlace6.setX1(0);
        enlace6.setY1(0);
        enlace6.setX2(0);
        enlace6.setY2(0);
        enlace6.setAtributo(9);
        enlace6.setTextcolor(Color.green);
        enlace6.setNroActividadNodoInicio(listNodo.get(3).getNroActividad());
        enlace6.setNroActividadNodoFin(listNodo.get(5).getNroActividad());
        enlace6.setThickness(1);
        enlace6.setTextDirection(20);
        enlace6.setNodoInicio(listNodo.get(3));
        enlace6.setNodoFin(listNodo.get(5));
        enlace6.setCiclo(true);
        listEnlace.add(enlace6);

        Enlace enlace7 = new Enlace();
        enlace7.setX1(0);
        enlace7.setY1(0);
        enlace7.setX2(0);
        enlace7.setY2(0);
        enlace7.setAtributo(3);
        enlace7.setTextcolor(Color.green);
        enlace7.setNroActividadNodoInicio(listNodo.get(4).getNroActividad());
        enlace7.setNroActividadNodoFin(listNodo.get(6).getNroActividad());
        enlace7.setThickness(1);
        enlace7.setTextDirection(20);
        enlace7.setNodoInicio(listNodo.get(4));
        enlace7.setNodoFin(listNodo.get(6));
        enlace7.setCiclo(true);
        listEnlace.add(enlace7);

        Enlace enlace8 = new Enlace();
        enlace8.setX1(0);
        enlace8.setY1(0);
        enlace8.setX2(0);
        enlace8.setY2(0);
        enlace8.setAtributo(2);
        enlace8.setTextcolor(Color.green);
        enlace8.setNroActividadNodoInicio(listNodo.get(5).getNroActividad());
        enlace8.setNroActividadNodoFin(listNodo.get(6).getNroActividad());
        enlace8.setThickness(1);
        enlace8.setTextDirection(20);
        enlace8.setNodoInicio(listNodo.get(5));
        enlace8.setNodoFin(listNodo.get(6));
        enlace8.setCiclo(true);
        listEnlace.add(enlace8);

        Enlace enlace9 = new Enlace();
        enlace9.setX1(0);
        enlace9.setY1(0);
        enlace9.setX2(0);
        enlace9.setY2(0);
        enlace9.setAtributo(5);
        enlace9.setTextcolor(Color.green);
        enlace9.setNroActividadNodoInicio(listNodo.get(6).getNroActividad());
        enlace9.setNroActividadNodoFin(listNodo.get(7).getNroActividad());
        enlace9.setThickness(1);
        enlace9.setTextDirection(20);
        enlace9.setNodoInicio(listNodo.get(6));
        enlace9.setNodoFin(listNodo.get(7));
        enlace9.setCiclo(true);
        listEnlace.add(enlace9);


        System.out.println("");
        System.out.println(listNodo);
        System.out.println("");
        System.out.println(listEnlace);

        Johnson johnson = new Johnson();
        johnson.setEnlaces(listEnlace);
        johnson.setNodos(listNodo);
        johnson.eject();


        System.out.println("");
        System.out.println(listNodo);
        System.out.println("");
        System.out.println(listEnlace);

    }
}
