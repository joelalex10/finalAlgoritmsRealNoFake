package Algoritmos;

import javax.swing.*;
import java.util.ArrayList;

public class Kramer {


    public Kramer(){

    }
    public int[][] Noro9(int[][]matrix,ArrayList<Integer> demanda, ArrayList<Integer> oferta, int matrixRow, int matrixCol){

        System.out.println("");
        for(int i=0;i<matrixRow;i++){
            System.out.println("");
            for(int j=0;j<matrixCol;j++){
                System.out.print(matrix[i][j]+"\t");
            }
        }

        System.out.println("");

        for(int i=0;i<demanda.size();i++){
            System.out.println(demanda.get(i));
        }
        System.out.println("");
        for(int i=0;i<oferta.size();i++){
            System.out.println(oferta.get(i));
        }

        int[][] matrizSolucion= new int[matrixRow][matrixCol];
        for(int col=0;col<matrixRow;col++){
            for(int fil=0;fil<matrixCol;fil++){
                if (demanda.get(fil)<=oferta.get(col)){
                    matrizSolucion[col][fil]=demanda.get(fil);
                    oferta.set(col,oferta.get(col)-demanda.get(fil));
                    demanda.set(fil,0);
                }else{
                    matrizSolucion[col][fil]=oferta.get(col);
                    demanda.set(fil,demanda.get(fil)-oferta.get(col));
                    oferta.set(col,0);
                }
            }

        }

        return matrizSolucion;
    }

    public static void mostrar(int matrizAdy[][]) {
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
    public static void CostoTotal(int[][]matriz,int[][]matrizSolu){
        int suma=0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                suma= suma+(matriz[i][j]*matrizSolu[i][j]);
            }

        }

        JOptionPane.showMessageDialog (null, suma);
    }
}
