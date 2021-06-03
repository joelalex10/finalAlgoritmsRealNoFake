package Algoritmos;

public class MainAsignacion {
    public static void main(String args[]){

        int mat1[][]={
                {55,85,68},
                {71,69,72},
                {82,52,72},
        };
        int n=3;
        int m=3;
        Asignacion a = new Asignacion(mat1,true);
        a.efectuarAlgoritmo();
        //a.ejecutar();



    }


}
