package windows;

import grafos.Nodo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class VentanaResultsKramer extends JFrame {

    public int supplies;
    public int demands;

    public Vector<Nodo> vecNodo1;
    public Vector<Nodo>vecNodo2;
    public int[] valOfertas;
    public int[] valDemandas;
    public int [][] results ;
    public int [][] matirxCoast;
    public int totalCost;



    public VentanaResultsKramer(int supplies, int demands, Vector<Nodo> vecNodo1, Vector<Nodo>vecNodo2,
                                int[] valOfertas, int[] valDemandas, int [][] matirxCoast,  int [][] results, int totalCost){
        this.supplies = supplies;
        this.demands = demands;
        this.vecNodo1 = vecNodo1;
        this.vecNodo2 = vecNodo2;
        this.valOfertas = valOfertas;
        this.valDemandas = valDemandas;
        this.results = results;
        this.matirxCoast = matirxCoast;
        this.totalCost = totalCost;
        initialize();

    }

    private void initialize() {
        setTitle("MATRIZ KRAMER");
        setBounds(100, 100, 890, 647);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JTextField [][] matriz = new JTextField[supplies+2][demands+2];
//        int [] demanda = new int[matrixCol];
//        int [] oferta = new int[matrixRow];
        ArrayList<Integer> demanda =new ArrayList<Integer>();
        ArrayList<Integer> oferta =new ArrayList<Integer>();
        int posh = 20;
        int posv = 60;
        int fil;
        int col;
        for(col=0;col<supplies+2;col++){
            for(fil=0;fil<demands+2;fil++){
                matriz[col][fil]= new JTextField();
                matriz[col][fil].setBounds(posv,posh,90,40);
                add(matriz[col][fil]);
                posv+=90;
            }
            posh+=40;
            posv =60;
        }

        for(int i=0;i<vecNodo1.size();i++){
            matriz[i+1][0].setText(vecNodo1.get(i).getNombre());
            //matriz[i+1][0].setEnabled(false);
        }
        for(int i=0;i<vecNodo2.size();i++){
            matriz[0][i+1].setText(vecNodo2.get(i).getNombre());
            //matriz[0][i+1].setEnabled(false);
        }

        for(int i=0;i<vecNodo1.size();i++){
            matriz[i+1][vecNodo1.size()+2].setText(valOfertas[i]+"");
            //matriz[i+1][vecNodo1.size()+2].setEnabled(false);
        }

        for(int i=0;i<supplies;i++){
            for(int j=0;j<demands;j++){
                matriz[i+1][j+1].setText(matirxCoast[i][j]+" -> "+results[i][j]);
            }
        }
        System.out.println(vecNodo2.size());

        for(int i=0;i<vecNodo2.size();i++){
            matriz[vecNodo2.size()][i+1].setText(valDemandas[i]+"");
            //matriz[vecNodo2.size()][i+1].setEnabled(false);
        }

        matriz[0][0].setVisible(false);
        //matriz[supplies+1][demands+1].setVisible(false);
        matriz[0][demands+1].setText("Oferta");
        //matriz[0][demands+1].setEnabled(false);
        matriz[supplies+1][0].setText("Demanda");
        //matriz[supplies+1][0].setEnabled(false);

        JLabel jLabel = new JLabel();
        jLabel.setText("EL TOTAL DE COSTOS ES: "+totalCost);
        jLabel.setBounds(100,60*demands,190,40);
        add(jLabel);

    }
}
