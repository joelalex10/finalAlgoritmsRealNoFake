package Algoritmos;

public class MainCompet {
    public static void main(String args[]){
        int mat[][] = {
                {1,4},
                {2,5},
                {3,7},
                {5,2},
                {1,4}
        };
        int tam = 5;
        Compet compet = new Compet(mat,tam);
        compet.ejecutar();
    }
}
