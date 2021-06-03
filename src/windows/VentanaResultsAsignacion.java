package windows;

import grafos.Nodo;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class VentanaResultsAsignacion extends JFrame {

    int matrixRow;
    int matrixCol;
    Vector<Nodo> vecNodo1;
    Vector<Nodo> vecNodo2;
    int matrizCoeficientes[][];

    public VentanaResultsAsignacion(int matrixRow, int matrixCol, Vector<Nodo> vecNodo1, Vector<Nodo> vecNodo2, int matrizCoeficientes[][]) {
        this.matrixRow = matrixRow;
        this.matrixCol = matrixCol;
        this.vecNodo1 = vecNodo1;
        this.vecNodo2 = vecNodo2;
        this.matrizCoeficientes = matrizCoeficientes;
        initialize();
    }

    public void initialize(){
        setTitle("MATRIZ ASGINACION");
        setBounds(100, 100, 890, 647);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel jLabel = new JLabel();
        jLabel.setText("MATRIZ ORIGINAL");
        jLabel.setBounds(10,100,90,40);
        add(jLabel);
        JTextField [][] matriz = new JTextField[matrixRow+1][matrixCol+1];
        int posh = 30;
        int posv = 60;
        int fil;
        int col;
        for(col=0;col<(matrixRow+1);col++){
            for(fil=0;fil<(matrixCol+1);fil++){
                matriz[col][fil]= new JTextField();
                matriz[col][fil].setBounds(posv,posh,90,40);
                posv+=90;
                add(matriz[col][fil]);
            }
            posh+=40;
            posv =60;
        }
        matriz[0][0].setText("O/D");
        matriz[0][0].setEnabled(false);
        for(int i=0;i<vecNodo1.size();i++){
            matriz[i+1][0].setText(vecNodo1.get(i).getNombre());
            matriz[i+1][0].setEnabled(false);
        }
        for(int i=0;i<vecNodo2.size();i++){
            matriz[0][i+1].setText(vecNodo2.get(i).getNombre());
            matriz[0][i+1].setEnabled(false);
        }
        for(int i=0;i<matrixRow;i++){
            for(int j=0;j<matrixCol;j++){
                matriz[i+1][j+1].setText(matrizCoeficientes[i][j]+"");
            }
        }
    }

    public void generarMatriz2(){

    }
}
