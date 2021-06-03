package Algoritmos;

import grafos.Enlace;
import grafos.Nodo;
import windows.Sudoku;

import javax.swing.*;
import java.util.Vector;

public class Asignacion {

    int[][]matrizAdyacente;
    int[][]matrizRestada;
    boolean opcion;
    int[]arrMayores;
    int[][]matMayores;

    public Asignacion(int[][] matrizAdyacente, boolean opcion) {
        super();
        this.matrizAdyacente = matrizAdyacente;
        this.opcion = opcion;
        System.out.println("DATOS");
        for(int i=0;i<matrizAdyacente.length;i++){
            System.out.println("");
            for(int j=0;j<matrizAdyacente.length;j++){
                System.out.print(matrizAdyacente[i][j]+"\t");
            }
        }

    }
    public int []obtenerMayores(){
        int arrMax [] = new int[matrizAdyacente.length];
        int valorMaxF = 0;
        for(int i=0;i<matrizAdyacente.length;i++){
            valorMaxF = matrizAdyacente[0][i];
            for(int j=0;j<matrizAdyacente.length;j++){
                if(valorMaxF<matrizAdyacente[j][i]) {
                    valorMaxF=matrizAdyacente[j][i];
                }
            }
            arrMax[i] = valorMaxF;
            //System.out.println("El valor max de la fila es " + valorMaxF);
        }
        return arrMax;
    }

    public void efectuarAlgoritmo() {
        mostrar(matrizAdyacente);
        System.out.println("");
        arrMayores = obtenerMayores();
        matrizRestada = getMatrizRestada(arrMayores);
        System.out.println("");
        matMayores = getMatMayores(matrizRestada);

        /********
        int [][]resta2 = matrizRestada();
        alfa = getMinOMax2(resta2);
        resta1 = restarMatriz(resta2, alfa);
        mostrar(resta1);
        int [][] resultado = new int[resta1.length][resta1.length];
        for (int x=0; x < resultado.length; x++) {
            for (int y=0; y < resta1[x].length; y++) {
                resultado[x][y] = 100*resta1 [x][y];
            }
        }
        Sudoku a=new Sudoku(resultado);
        int[][] matrizasd = a.solve();
        int[][] hola=multiplicarMatrices(matrizAdyacente,cambiar(matrizasd));
        if (a.isLol()){
            mostrarString(orden(matrizAdyacente,cambiar(matrizasd)));
            JOptionPane.showMessageDialog (null, letra(matrizAdyacente,cambiar(matrizasd)));
            SumaAsignado(matrizAdyacente,cambiar(matrizasd));
        }*/
    }

    public void mostrar(int matrizAdy[][]) {
        int c =matrizAdy.length;
        String[] m= new String[c];
        for(int i = 0;i < c; i++) {
            String v ="";
            for(int j = 0; j < matrizAdy[0].length; j++) {
                v+= matrizAdy[i][j]+"   ";
            }
            m[i]=v;
        }
        JOptionPane.showMessageDialog (null, m);
    }

    public int[][] getMatrizRestada(int arrMax[]){
        int [][] matSust = matrizAdyacente;

        for(int i=0;i<matrizAdyacente.length;i++){
            for(int j=0;j<matrizAdyacente.length;j++){
                matSust[i][j] = matSust[i][j] -arrMax[j];
            }
        }
        System.out.println("");
        for(int i=0;i<matrizAdyacente.length;i++){
            System.out.println("");
            for(int j=0;j<matrizAdyacente.length;j++){
                System.out.print(matSust[i][j]+"\t");
            }
        }
        return matSust;
    }

    private int[][] getMatMayores(int[][] m){
        int [][] matrizC = new int[m.length][m[0].length];
        for(int i = 0; i < matrizC.length; i++) {
            if(opcion) {
                int maximo = getMaximo(m, i, true);
                for(int j = 0 ;j < matrizC[0].length; j++) {
                    matrizC[i][j] = maximo;
                }
            } else {
                int minimo = getMinimo(m, i, true);
                for(int j = 0 ;j < matrizC[0].length; j++) {
                    matrizC[i][j] = minimo;
                }
            }
        }

        System.out.println("alfa");
        for(int i=0;i<matrizC.length;i++){
            System.out.println("");
            for(int j=0;j<matrizC.length;j++){
                System.out.print(matrizC[i][j]+"\t");
            }
        }

        return matrizC;
    }

    private int getMaximo(int[][] m, int indice, boolean estado) {
        int maximo;
        if(estado) {
            maximo = m[indice][0];
            for(int i = 0; i < m[0].length; i++)
                maximo = Math.max(m[indice][i], maximo);
        } else {
            maximo = m[0][indice];
            for(int i = 0; i < m.length; i++)
                maximo = Math.max(m[i][indice], maximo);
        }

        return maximo;
    }

    private int getMinimo(int[][] m, int indice, boolean estado) {
        int minimo;
        if(estado) {
            minimo = m[indice][0];
            for(int i = 0; i < m[0].length; i++)
                minimo = Math.min(m[indice][i], minimo);
        } else {
            minimo = m[0][indice];
            for(int i = 0; i < m.length; i++)
                minimo = Math.min(m[i][indice], minimo);
        }
        return minimo;
    }

    /***
    **/


}
