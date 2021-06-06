package windows;

import grafos.Enlace;
import grafos.Nodo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaSorts extends JFrame {

    private static long TInicio;
    private static long  TFin, tiempo;
    static boolean basta=true;

    JTextArea jTextArea1;
    JTextArea jTextArea2;

    public VentanaSorts(){
        if(basta){
            initialize();
        }

    }

    public static void seleccion(int A[]) {

        int i, j, menor, pos, tmp;
        for (i = 0; i < A.length - 1; i++) { // tomamos como menor el primero
            menor = A[i]; // de los elementos que quedan por ordenar
            pos = i; // y guardamos su posición
            for (j = i + 1; j < A.length; j++){ // buscamos en el resto
                if (A[j] < menor) { // del array algún elemento
                    menor = A[j]; // menor que el actual
                    pos = j;
                }
            }
            if (pos != i){ // si hay alguno menor se intercambia
                tmp = A[i];
                A[i] = A[pos];
                A[pos] = tmp;
            }
            System.out.println(A[pos]);
        }


    }

    public void initialize(){
        setTitle("MATRIZ ASGINACION");
        setBounds(100, 100, 890, 647);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel jLabel = new JLabel();
        jLabel.setText("INGRESE LOS DATOS");
        jLabel.setBounds(30,15,140,40);
        add(jLabel);

        int x1 = 180;
        int y1 = 20;
        int widht = 402;
        int height = 100;
        jTextArea1 = new JTextArea();
        jTextArea1.setBounds(x1, y1, widht, height);
        getContentPane().add(jTextArea1);
        jTextArea1.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(jTextArea1);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollBar);
        scrollBar.setBounds(x1-3, y1, widht+11, height+15);
        JLabel jLabelResultados = new JLabel();
        jLabelResultados.setText("RESULTADOS");
        jLabelResultados.setBounds(30,140,140,40);
        add(jLabelResultados);

        int x2 = 180;
        int y2 = 145;
        int widht2 = 402;
        int height2 = 100;
        jTextArea2 = new JTextArea();
        jTextArea2.setBounds(x2, y2, widht2, height2);
        getContentPane().add(jTextArea2);
        jTextArea2.setLineWrap(true);
        JScrollPane scrollBar2 = new JScrollPane(jTextArea2);
        scrollBar2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollBar2);
        scrollBar2.setBounds(x2-3, y2, widht2+11, height2+15);

        JLabel labelControl = new JLabel();
        labelControl.setBounds(180,275,200,30);
        labelControl.setText("Tiempo en milisegundos");
        getContentPane().add(labelControl);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 510, 864, 127);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JButton btnNewButton = new JButton("SELECTION SORT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String orden="";
                ArrayList<Integer>listaSeleccion = getTextofSort();
                TInicio = System.currentTimeMillis();
                int []A= new int[listaSeleccion.size()];
                for (int i=0;i<listaSeleccion.size();i++){
                    A[i]=listaSeleccion.get(i);
                }
                seleccion(A);
                for (int i=0;i<listaSeleccion.size();i++){
                    if(i==(listaSeleccion.size()-1)){
                        orden+=A[i];
                    }else{
                        orden+=A[i]+",";
                    }


                    jTextArea2.setText(orden);
                }
                TFin = System.currentTimeMillis();
                tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
                System.out.println("Tiempo en milisegundos: " + tiempo);
                labelControl.setText("Tiempo en milisegundos: " + tiempo);
                //temp.setText(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T

                basta=false;

            }
        });
        btnNewButton.setBounds(184, 11, 160, 30);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        //btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
        btnNewButton.setBackground(new Color(21, 88, 16));
        panel_1.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("INSERTION SORT");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String inser="";
                ArrayList<Integer>listaInsertion = getTextofSort();
                TInicio = System.currentTimeMillis();
                int []A= new int[listaInsertion.size()];
                for (int i=0;i<listaInsertion.size();i++){
                    A[i]=listaInsertion.get(i);
                }
                insercion(A);
                for (int i=0;i<listaInsertion.size();i++){

                    if(i==(listaInsertion.size()-1)){
                        inser+=A[i];
                    }else{
                        inser+=A[i]+",";
                    }
                    jTextArea2.setText(inser);
                }
                TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
                tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
                labelControl.setText("Tiempo en milisegundos: " + tiempo);

                basta=false;
            }
        });
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnNewButton_1.setBackground(new Color(21, 88, 16));
        btnNewButton_1.setBounds(25, 11, 160, 30);
        panel_1.add(btnNewButton_1);

        JButton btnNewButton_1_2 = new JButton("SHELL SORT");
        btnNewButton_1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String she="";
                ArrayList<Integer>listaShell = getTextofSort();
                TInicio = System.currentTimeMillis();
                int []A= new int[listaShell.size()];
                for (int i=0;i<listaShell.size();i++){
                    A[i]=listaShell.get(i);
                }
                shell(A);
                for (int i=0;i<listaShell.size();i++){

                    if(i==(listaShell.size()-1)){
                        she+=A[i];
                    }else{
                        she+=A[i]+",";
                    }

                    jTextArea2.setText(she);
                }
                TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
                tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
                labelControl.setText("Tiempo en milisegundos: " + tiempo);

                basta=false;

            }
        });
        btnNewButton_1_2.setForeground(Color.WHITE);
        btnNewButton_1_2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnNewButton_1_2.setBackground(new Color(21, 88, 16));
        btnNewButton_1_2.setBounds(343, 11, 160, 30);
        panel_1.add(btnNewButton_1_2);

        JButton btnNewButton_1_1 = new JButton("MERGE SORT");
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String merg="";
                ArrayList<Integer>listaMerge = getTextofSort();
                TInicio = System.currentTimeMillis();
                int []A= new int[listaMerge.size()];
                for (int i=0;i<listaMerge.size();i++){
                    A[i]=listaMerge.get(i);
                }
                mergeSort(A,A.length);
                for (int i=0;i<listaMerge.size();i++){
                    if(i==(listaMerge.size()-1)){
                        merg+=A[i];
                    }else{
                        merg+=A[i]+",";
                    }
                    jTextArea2.setText(merg);
                }
                TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
                tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
                labelControl.setText("Tiempo en milisegundos: " + tiempo);

                basta=false;

            }
        });
        btnNewButton_1_1.setForeground(Color.WHITE);
        btnNewButton_1_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnNewButton_1_1.setBackground(new Color(21, 88, 16));
        btnNewButton_1_1.setBounds(502, 11, 160, 30);
        panel_1.add(btnNewButton_1_1);

        JButton btnNewButton_1_1_1 = new JButton("ATRAS");
        btnNewButton_1_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal window = new VentanaPrincipal();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1_1_1.setForeground(Color.WHITE);
        btnNewButton_1_1_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnNewButton_1_1_1.setBackground(new Color(21, 88, 16));
        btnNewButton_1_1_1.setBounds(663, 11, 160, 30);
        panel_1.add(btnNewButton_1_1_1);

    }

    public static void shell(int A[]){

        int salto, aux, i;
        boolean cambios;
        for(salto=A.length/2; salto!=0; salto/=2){
            cambios=true;
            while(cambios){ // Mientras se intercambie algún elemento
                cambios=false;
                for(i=salto; i< A.length; i++) // se da una pasada
                    if(A[i-salto]>A[i]){ // y si están desordenados
                        aux=A[i]; // se reordenan
                        A[i]=A[i-salto];
                        A[i-salto]=aux;
                        cambios=true; // y se marca como cambio.
                    }
            }
        }
    }
    public ArrayList<Integer> getTextofSort(){

        String cadena = jTextArea1.getText();
        String [] arrCadena = cadena.split(",");
        ArrayList<Integer>lista = new ArrayList<Integer>();
        try{
            for(int i=0;i<arrCadena.length;i++){
                int valor = Integer.parseInt(arrCadena[i]);
                lista.add(valor);
                System.out.println(valor);
            }
        }catch (NumberFormatException e){
            lista.clear();
            JOptionPane.showMessageDialog(null,"SE HA DETECTADO UN VALOR NO NUMERICO");
        }
        return lista;
    }

    public static void insercion(int[] a) {

        int n = a.length;
        for (int i = 1; i <= n - 1; i++) {
            int x = a[i];
            int j = i - 1;
            while (j >= 0 && x < a[j]) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = x;
        }
    }

    public static void mergeSort(int[] a, int n) {

        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
