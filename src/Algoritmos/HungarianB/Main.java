package Algoritmos.HungarianB;

public class Main {
    public static void main(String args[]){

        double[][] dataMatrix = {
                //col0  col1  col2  col3
                {6,  8,   7, 3},  //row0
                {7,  7,   7, 4},  //row1
                {8,  5,   7, 4},//row3
                {5,  7,   7, 5}
        };

        HungarianAlgorithm ha = new HungarianAlgorithm(dataMatrix);
        int[]variable = ha.execute();
        for(int i=0;i<variable.length;i++){
            System.out.println(variable[i]+"\t");
        }

    }
}
