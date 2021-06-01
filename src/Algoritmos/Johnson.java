package Algoritmos;

import grafos.Enlace;
import grafos.Nodo;

import java.awt.*;
import java.util.Vector;

public class Johnson{


    private Vector<Nodo> nodos;
    private Vector<Enlace> enlaces;

    public Johnson(){

    }

    public Vector<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(Vector<Nodo> nodos) {
        this.nodos = nodos;
    }

    public Vector<Enlace> getEnlaces() {
        return enlaces;
    }

    public void setEnlaces(Vector<Enlace> enlaces) {
        this.enlaces = enlaces;
    }

    public void eject(){
        System.out.println("EL TAMANIO DE LAS ARISTAS ES: "+enlaces.size());
        System.out.println("EL TAMANIO DE LOS NODOS ES: "+nodos.size());
        nodos.get(0).setColor(Color.green);
        modNod();
    }

    public void modNod() {
        for(int i = 0; i< nodos.size(); i++){
            for(int j = 0; j< enlaces.size(); j++){
                if (nodos.get(i).getNroActividad()==enlaces.get(j).getNroActividadNodoInicio()){
                    nodos.get(enlaces.get(j).getNroActividadNodoFin()).setRec1(enlaces.get(j).getAtributo() + nodos.get(enlaces.get(j).getNroActividadNodoInicio()).getRec1());
                }
            }
        }

        nodos.lastElement().setRec2(nodos.lastElement().getRec1());

        for(int i = 0; i< nodos.size(); i++){
            for(int j = 0; j< enlaces.size(); j++){

                    nodos.get(enlaces.get(j).
                            getNroActividadNodoInicio()).setRec2(
                            nodos.get(enlaces.get(j).
                                    getNroActividadNodoFin()).getRec2() -
                                    enlaces.get(j).getAtributo()
                    );


            }
        }

        for(Nodo nodo: nodos){
            if(nodo.getRec1() == nodo.getRec2()){
                nodo.setColor(Color.green);

            }
        }

        for(Enlace enlace: enlaces){
            if(nodos.firstElement().getNroActividad() ==
            enlace.getNroActividadNodoInicio() && nodos.get(
                    enlace.getNroActividadNodoFin()).getRec2() == nodos.get(
                            enlace.getNroActividadNodoFin()
            ).getRec1()
            ){
                enlace.setTextcolor(Color.orange);
            }
                if(nodos.get(enlace.getNroActividadNodoFin()).getRec2()==
                        nodos.get(enlace.getNroActividadNodoFin()).getRec1() &&
                        nodos.get(enlace.getNroActividadNodoInicio()).getRec2() ==
                                nodos.get(enlace.getNroActividadNodoInicio()).getRec1()
                ){
                    enlace.setTextcolor(Color.orange);
                }
        }



        for(Enlace enlace:enlaces){
            Nodo nodoAuxI=null;
            Nodo nodoAuxF=null;

            for (Nodo nodo: nodos){
                if(enlace.getNroActividadNodoFin()==nodo.getNroActividad()){
                    nodoAuxF=nodo;
                }
            }
            for (Nodo nodo: nodos){
                if(enlace.getNroActividadNodoInicio()==nodo.getNroActividad()){
                    nodoAuxI = nodo;
                }
            }
            System.out.println("("+nodoAuxF.getRec2()+","+nodoAuxI.getRec1()+","+enlace.getAtributo()+")");
            enlace.setHolgura(nodoAuxF.getRec2()-nodoAuxI.getRec1()-enlace.getAtributo());
        }
    }
}  
