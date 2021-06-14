package windows;

import grafos.Enlace;
import grafos.Nodo;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class VentanaResultsAsignacion extends JFrame {

    int matrixRow;
    int matrixCol;
    Vector<Nodo> vecNodo1;
    Vector<Nodo> vecNodo2;
    Vector<Enlace> vecEnlace;
    double matrizCoeficientes[][];
    double matrizRestas[][];
    String matrizResultados[][];
    double sumaTotal;


    public VentanaResultsAsignacion(int matrixRow, int matrixCol, Vector<Nodo> vecNodo1, Vector<Nodo> vecNodo2, double matrizCoeficientes[][],double  matrizRestas[][],String matrizResultados[][], double sumaTotal,
                                    Vector<Enlace> vecEnlace) {
        this.matrixRow = matrixRow;
        this.matrixCol = matrixCol;
        this.vecNodo1 = vecNodo1;
        this.vecNodo2 = vecNodo2;
        this.matrizRestas = matrizRestas;
        this.matrizCoeficientes = matrizCoeficientes;
        this.matrizResultados= matrizResultados;
        this.sumaTotal = sumaTotal;
        this.vecEnlace = vecEnlace;
        initialize();
    }

    public void initialize(){



        setTitle("MATRIZ ASGINACION");
        setBounds(100, 100, 590, 820);
        getContentPane().setLayout(null);



        JTextField [][] matriz = new JTextField[matrixRow+1][matrixCol+1];
        int posh = 30;
        int posv = 60;
        int fil;
        int col;
        for(col=0;col<(matrixRow+1);col++){
            for(fil=0;fil<(matrixCol+1);fil++){
                matriz[col][fil]= new JTextField();
                matriz[col][fil].setBounds(posv,posh,90,40);
                matriz[col][fil].setEditable(false);
                posv+=90;
                add(matriz[col][fil]);
            }
            posh+=40;
            posv =60;
        }
        matriz[0][0].setText("O/D");
        matriz[0][0].setEditable(false);
        matriz[0][0].setForeground(Color.white);
        matriz[0][0].setBackground(new Color(9,11,48));



        for(int i=0;i<vecNodo1.size();i++){
            matriz[i+1][0].setText(vecNodo1.get(i).getNombre());
            matriz[i+1][0].setEditable(false);
            matriz[i+1][0].setForeground(Color.white);
            matriz[i+1][0].setBackground(new Color(9,11,48));
        }
        for(int i=0;i<vecNodo2.size();i++){
            matriz[0][i+1].setText(vecNodo2.get(i).getNombre());
            matriz[0][i+1].setEditable(false);
            matriz[0][i+1].setForeground(Color.white);
            matriz[0][i+1].setBackground(new Color(9,11,48));
        }
        for(int i=0;i<matrixRow;i++){
            for(int j=0;j<matrixCol;j++){
                matriz[i+1][j+1].setText(matrizCoeficientes[i][j]+"");

            }
        }

        int posh1 = posh;
        int posv1 = posv*matrizCoeficientes.length;
        JTextField [][] matriz1 = new JTextField[matrixRow+1][matrixCol+1];
        int fil1;
        int col1;
        for(col1=0;col1<(matrixRow+1);col1++){
            for(fil1=0;fil1<(matrixCol+1);fil1++){
                matriz1[col1][fil1]= new JTextField();
                matriz1[col1][fil1].setBounds(posv,posh,90,40);
                matriz1[col1][fil1].setEditable(false);
                posv+=90;
                add(matriz1[col1][fil1]);
            }
            posh+=40;
            posv =60;
        }
        matriz1[0][0].setText("O/D");
        matriz1[0][0].setEditable(false);
        matriz1[0][0].setForeground(Color.white);
        matriz1[0][0].setBackground(new Color(9,11,48));

        for(int i=0;i<vecNodo1.size();i++){
            matriz1[i+1][0].setText(vecNodo1.get(i).getNombre());
            matriz1[i+1][0].setEditable(false);
            matriz1[i+1][0].setForeground(Color.white);
            matriz1[i+1][0].setBackground(new Color(9,11,48));
        }
        for(int i=0;i<vecNodo2.size();i++){
            matriz1[0][i+1].setText(vecNodo2.get(i).getNombre());
            matriz1[0][i+1].setEditable(false);
            matriz1[0][i+1].setForeground(Color.white);
            matriz1[0][i+1].setBackground(new Color(9,11,48));
        }

        int cont =0;

        for(int i=0;i<matrixRow;i++){
            for(int j=0;j<matrixCol;j++){
                matriz1[i+1][j+1].setText(matrizRestas[i][j]+"");
                cont++;
            }
        }

        int posh2 = posh;
        int posv2 = posv*matrizCoeficientes.length*2;
        JTextField [][] matriz2 = new JTextField[matrixRow+1][matrixCol+1];
        int fil2;
        int col2;
        for(col2=0;col2<(matrixRow+1);col2++){
            for(fil2=0;fil2<(matrixCol+1);fil2++){
                matriz2[col2][fil2]= new JTextField();
                matriz2[col2][fil2].setBounds(posv,posh,90,40);
                matriz2[col2][fil2].setEditable(false);
                posv+=90;
                add(matriz2[col2][fil2]);
            }
            posh+=40;
            posv =60;
        }
        matriz2[0][0].setText("O/D");
        matriz2[0][0].setEditable(false);
        matriz2[0][0].setForeground(Color.white);
        matriz2[0][0].setBackground(new Color(9,11,48));
        for(int i=0;i<vecNodo1.size();i++){
            matriz2[i+1][0].setText(vecNodo1.get(i).getNombre());
            matriz2[i+1][0].setEditable(false);
            matriz2[i+1][0].setForeground(Color.white);
            matriz2[i+1][0].setBackground(new Color(9,11,48));
        }
        for(int i=0;i<vecNodo2.size();i++){
            matriz2[0][i+1].setText(vecNodo2.get(i).getNombre());
            matriz2[0][i+1].setEditable(false);
            matriz2[0][i+1].setForeground(Color.white);
            matriz2[0][i+1].setBackground(new Color(9,11,48));

        }
        for(int i=0;i<matrixRow;i++){
            for(int j=0;j<matrixCol;j++){

                if(!matrizResultados[i][j].contains("(")){
                    matriz2[i+1][j+1].setText(matrizResultados[i][j]+"");
                }else{
                    matriz2[i+1][j+1].setText(matrizResultados[i][j]+"");
                    matriz2[i+1][j+1].setBackground(Color.green);
                    matriz1[i+1][j+1].setBackground(Color.green);
                    matriz[i+1][j+1].setBackground(Color.green);
                }
            }
        }

        for(Enlace enlace: vecEnlace){
            for(int i=0;i<matrixRow;i++){
                for(int j=0;j<matrixCol;j++){

                    if(matrizResultados[i][j].contains("(")){
                        if(enlace.getNroActividadNodoInicio()==i && enlace.getNroActividadNodoFin()==j){
                            enlace.setTextcolor(Color.GREEN);
                        }
                    }

                }
            }
        }

        JLabel jLabel = new JLabel();
        jLabel.setText("Total: "+sumaTotal);
        System.out.println("size "+posh*matrizResultados.length);
        jLabel.setBounds(posv*matrizResultados.length,posv*3*matrizCoeficientes.length,190,40);
        jLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));



        add(jLabel);





    }

    public void generarMatriz2(){

    }
}
