package windows;

import javax.swing.*;

public class Sudoku {
    private  int sudoku[][];
    public Sudoku(int sudoku[][]) {
        this.sudoku = sudoku;
    }
    private boolean lol=true;

    public boolean isLol() {
        return lol;
    }

    public int[][] solve() {

        if (!backtrackSolve()) {
            System.out.println("error");
        }


        int[][] matrizcau= new int [sudoku.length][sudoku.length] ;
        if(sudoku.length==3) {
            int aux1=0;
            int aux2=0;
            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku.length; j++) {
                    if(sudoku[i][j]==1) {
                        aux1++;
                    }else if(sudoku[i][j]==2) {
                        aux2++;
                    }
                }
            }
            System.out.println("vas bien"+ aux1+aux2);
            if(aux1>aux2) {
                for (int i = 0; i < sudoku.length; i++) {
                    for (int j = 0; j < sudoku.length; j++) {
                        if(sudoku[i][j]==1) {
                            matrizcau[i][j]=0;
                        }else {
                            matrizcau[i][j]=1;
                        }
                    }
                }
            }
            if(aux2>aux1) {
                for (int i = 0; i < sudoku.length; i++) {
                    for (int j = 0; j < sudoku.length; j++) {
                        if(sudoku[i][j]==2) {
                            matrizcau[i][j]=0;
                        }else {
                            matrizcau[i][j]=1;
                        }
                    }
                }
            }
            if (aux2==aux1){
                lol=false;
                JOptionPane.showMessageDialog(null, "No hay solucion");
            }



        }else {
            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku.length; j++) {
                    if(sudoku[i][j]==1) {
                        matrizcau[i][j]=0;
                    }else {
                        matrizcau[i][j]=1;
                    }
                }
            }
        }

        return matrizcau;
        // return sudoku;

    }

    public boolean isSuitableToPutXThere(int i, int j, int x) {

        // Is 'x' used in row.
        for (int jj = 0; jj < sudoku.length; jj++) {
            if (sudoku[i][jj] == x) {
                return false;
            }
        }

        // Is 'x' used in column.
        for (int ii = 0; ii < sudoku.length; ii++) {
            if (sudoku[ii][j] == x) {
                return false;
            }
        }
        return true;
    }




    public boolean backtrackSolve() {
        int i = 0, j = 0;
        boolean isThereEmptyCell = false;

        for (int ii = 0; ii <sudoku.length && !isThereEmptyCell; ii++) {
            for (int jj = 0; jj < sudoku.length && !isThereEmptyCell; jj++) {
                if (sudoku[ii][jj] == 0) {
                    isThereEmptyCell = true;
                    i = ii;
                    j = jj;
                }
            }
        }


        if (!isThereEmptyCell) {
            return true;
        }

        if(sudoku.length==3) {


            for (int x = 1; x < 3; x++) {

                if (isSuitableToPutXThere(i, j, x)) {
                    sudoku[i][j] = x;

                    if (backtrackSolve()) {
                        return true;
                    }

                    sudoku[i][j] = 0; // We've failed.
                }

            }
        }else {
            for (int x = 1; x < 10; x++) {

                if (isSuitableToPutXThere(i, j, x)) {
                    sudoku[i][j] = x;

                    if (backtrackSolve()) {
                        return true;
                    }

                    sudoku[i][j] = 0; // We've failed.
                }

            }
        }

        return false; // Backtracking
    }
}