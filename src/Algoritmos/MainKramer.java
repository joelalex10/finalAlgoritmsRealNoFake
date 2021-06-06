package Algoritmos;

public class MainKramer {
    public static void main(String[] args) throws Exception {

        int[] demanda = {4, 6, 7, 8};
        int[] disponibilidad = {7, 8, 10};
        int[][] costs = {
                {3, 4, 6, 6},
                {6, 3, 4, 7},
                {1, 5, 3, 2}
        };
        Kramer kramer = new Kramer(demanda, disponibilidad,costs);
        kramer.init();
        kramer.northWestCornerRule();
        kramer.minimizar();
        int [][] mat = kramer.printResult();
        for(int i=0;i<disponibilidad.length;i++){
            for(int j=0;j<demanda.length;j++){
                System.out.print(mat[i][j]+"\t");
            }
            System.out.println("");
        }

        kramer.maximizar();
        int [][] mat1 =kramer.printResult();
        for(int i=0;i<disponibilidad.length;i++){
            for(int j=0;j<demanda.length;j++){
                System.out.print(mat1[i][j]+"\t");
            }
            System.out.println("");
        }
    }
}
