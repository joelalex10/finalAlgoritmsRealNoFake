package Algoritmos.HungarianC;

public class MainHungarianAlgorithm {
    public static void main(String[] args) {
        System.out.println("Running two tests on three arrays:\n");

        // Square
        double[][] test1 = {
                {6,  8,   7, 3},
                {7,  7,   7, 4},
                {8,  5,   7, 4},
                {5,  7,   7, 5}
        };
        // Tall
        double[][] test2 = {
                {5,  6,   8, 9},
                {3,  4,   3, 5},
                {2,  3,   1, 7},
                {0,  0,   0, 0}


        };
        // Wide
        double[][] test3 = {
                {2,1,5},
                {1,2,4},
                {0,0,0}

        };
        double[][] test4 = {
                {55,85,68},
                {71,69,72},
                {82,52,72}

        };

        HungarianAlgorithm hungarianAlgorithm1 = new HungarianAlgorithm(test1);
        System.out.println("TEST 1");
        System.out.println("EL MINIMO ES: "+hungarianAlgorithm1.hgAlgorithm("min"));
        int[][] MinMatrixPositions1=hungarianAlgorithm1.hgAlgorithmAssignments("min");
        String[][] resultsPositions = hungarianAlgorithm1.matString("min");
        hungarianAlgorithm1.restas("min",false);

        System.out.println("EL MAXIMO ES: "+hungarianAlgorithm1.hgAlgorithm("max"));
        int[][] MaxMatrixPositions1=hungarianAlgorithm1.hgAlgorithmAssignments("max");
        hungarianAlgorithm1.restas("max",false);
        System.out.println("");
        String[][] resultsPositionsq = hungarianAlgorithm1.matString("max");


        /***
        HungarianAlgorithm hungarianAlgorithm2 = new HungarianAlgorithm(test2);
        System.out.println("TEST 2");
        System.out.println("EL MINIMO ES: "+hungarianAlgorithm2.hgAlgorithm("min"));
        int[][] MinMatrixPositions2=hungarianAlgorithm2.hgAlgorithmAssignments("min");
        System.out.println("EL MAXIMO ES: "+hungarianAlgorithm2.hgAlgorithm("max"));
        int[][] MaxMatrixPositions2=hungarianAlgorithm2.hgAlgorithmAssignments("max");
        System.out.println("");

        HungarianAlgorithm hungarianAlgorithm3 = new HungarianAlgorithm(test3);
        System.out.println("TEST 3");
        System.out.println("EL MINIMO ES: "+hungarianAlgorithm3.hgAlgorithm("min"));
        System.out.println("EL MAXIMO ES: "+hungarianAlgorithm3.hgAlgorithm("max"));
        System.out.println("");

        HungarianAlgorithm hungarianAlgorithm4 = new HungarianAlgorithm(test4);
        System.out.println("TEST 4");
        System.out.println("EL MINIMO ES: "+hungarianAlgorithm4.hgAlgorithm("min"));
        System.out.println("EL MAXIMO ES: "+hungarianAlgorithm4.hgAlgorithm("max"));
        System.out.println("");***/
    }
}
