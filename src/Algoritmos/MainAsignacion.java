package Algoritmos;

import windows.AlgoritmoAsignacion;

public class MainAsignacion {
    public static void main(String args[]){

        int mat1[][]={
                {55,85,68},
                {71,69,72},
                {82,52,72},
        };
        int n=3;
        int m=3;
        AlgoritmoAsignacion a = new AlgoritmoAsignacion(mat1,true);
        a.efectuarAlgoritmo();
        int [][]optimo = a.getMatrizOptimo();
        //a.ejecutar();



    }


}
