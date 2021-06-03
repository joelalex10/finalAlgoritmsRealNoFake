package Algoritmos;

import java.util.ArrayList;

import static java.util.Arrays.stream;

public class VogelsApproximationMethod {

    public static void main(String[] args) throws Exception {
        int[] demanda = {4, 6, 7, 8};
        int[] disponibilidad = {7, 8, 10};

        int[] auxDemanda = demanda;
        int[] auxDisponibilidad = disponibilidad;

        int[][] costs = {
                {3, 4, 6, 6},
                {6, 3, 4, 7},
                {1, 5, 3, 2}
        };
        Voggel voggel = new Voggel(disponibilidad, demanda, costs);
        voggel.ejecutar();

        /*

        ArrayList<Integer>arrDisponibilidad = new ArrayList<Integer>();
        ArrayList<Integer>arrDemanda = new ArrayList<Integer>();


        for(int i=0;i<auxDemanda.length;i++){
            arrDemanda.add(auxDemanda[i]);
        }

        for(int i=0;i<auxDisponibilidad.length;i++){
            arrDisponibilidad.add(auxDisponibilidad[i]);
        }

        Kramer k = new Kramer();
        int matrix[][] = k.Noro9(costs,arrDemanda, arrDisponibilidad, disponibilidad.length, demanda.length);
        k.mostrar(matrix);
        k.CostoTotal(costs,matrix);***/

    }

}