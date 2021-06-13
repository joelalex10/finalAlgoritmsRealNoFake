package Algoritmos.Hungarian;

/**
 * Test Class for HungarianAlgorithm.java
 *
 * @author https://github.com/aalmi | march 2014
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) {

      // the problem is written in the form of a matrix
      int[][] dataMatrix = {
              {70,65,30,25},
              {40,60,45,30},
              {20,45,50,55},
              {55,90,75,40},

      };

      //find optimal assignment
      HungarianAlgorithm ha = new HungarianAlgorithm(dataMatrix);
      int[][] assignment = ha.findOptimalAssignment();

      if (assignment.length > 0) {
        // print assignment
        for (int i = 0; i < assignment.length; i++) {
          System.out.print("Col" + assignment[i][0] + " => Row" + assignment[i][1] + " (" + dataMatrix[assignment[i][0]][assignment[i][1]] + ")");
          System.out.println();
        }
      } else {
        System.out.println("no assignment found!");
      }
    }
}
