package windows;

import Database.Compet.Dao.CompetEnlaceDao;
import Database.Compet.Dao.CompetGrafoDao;
import Database.Compet.Dao.CompetNodoDao;
import Database.Compet.Model.CompetEnlaceModel;
import Database.Compet.Model.CompetGrafoModel;
import Database.Compet.Model.CompetNodoModel;
import Database.EnlaceBDD;
import Database.GrafoBDD;
import Database.NodoBDD;
import GrafoCompet.EnlaceCompet;
import GrafoCompet.NodoCompet;
import GrafoCompet.PosicionCompet;
import grafos.Enlace;
import grafos.Nodo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class VentanaCompet extends JFrame {

    boolean flagPro=false;

    NodoCompet ni=null,nf=null;
    private double mul=1;
    private Vector<NodoCompet> vectorNodos=new Vector<>();
    private ArrayList<EnlaceCompet> listae=null;
    private ArrayList<String> help=null;
    private ArrayList<EnlaceCompet> enlacesEliminar = null;
    public ArrayList<PosicionCompet> listp=new ArrayList<>();
    private ArrayList<EnlaceCompet> listaEnlace=new ArrayList<>();
    private ArrayList<NodoCompet> puntoMedio=new ArrayList<>();
    HashMap<String,Integer> aux;
    ArrayList<String>vertices;
    String nuevo = "", nuevofin="";
    boolean sw=false;
    private JTextField textFieldX;
    private JTextField textFieldY;
    public static JScrollPane scrollPane;
    private JTable tablaDatos;
    public static JFrame ventanaAsignacion = new JFrame();
    private DibujoCompet paneld=new DibujoCompet();
    private DefaultTableModel modelo=new DefaultTableModel();
    private int index = 0;


    public VentanaCompet(){
        initialize();
    }
    private void initialize() {
        setTitle("ALGORITMO DE COMPET");
        setBounds(100, 100, 890, 647);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setListae (new ArrayList<>());

        //JPanel panel = new JPanel();
        //panel.setBounds(100,100, 850, 500);
        //lienzoAsignacion = new LienzoAsignacion(this);
        //lienzoAsignacion.setBounds(0, 0, 864, 500);
        //frame.getContentPane().add(lienzoAsignacion);
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10,0, 850, 500);
        paneld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==e.BUTTON1) {
                    if(vectorNodos.size()<paneld.getLista().size())
                    {
                        vectorNodos.clear();
                        for(NodoCompet nodo: paneld.getLista()) {
                            vectorNodos.add(nodo);
                            System.out.println("ADD ACTION 3");
                            //paneld.getListae().add(new Enlace (ni,nf,name,Color.gray,doble,mismo));
                        }
                        agregarColumna();
                        System.out.println("ADD ACTION 2");
                    }

                    if(paneld.getListae().size()>1)
                    {
                        listae.clear();
                        for(EnlaceCompet enlace: paneld.getListae()) {
                            listae.add(enlace);
                            System.out.println("ADD ACTION 1");
                        }
                        System.out.println("Lista enlace Listener");
                        imprimirListae();
                    }
                }
                else {
                    if(vectorNodos.size()>paneld.getLista().size()) {

                        //elimnarnodo();
                    }
                }
            }

        });
        scrollPane_1.setViewportView(paneld);
        add(scrollPane_1);





        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 510, 864, 87);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JButton btnNewButton_1_3 = new JButton("CALCULAR PUNTO");
        btnNewButton_1_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(verificarPoligono())
                {
                    try{
                        crearListaE();
                        System.out.println("EL PUNTO MEDIO ES: "+puntoMedio.get(0));

                        paneld.setPuntoMedio(puntoMedio.get(0));
                        paneld.setSw(true);
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"NO SE HAN AGREGADO NODOS");
                    }


                }
                else
                    JOptionPane.showMessageDialog(null, "Grafos ingresados incorrectos");
                paneld.repaint();


            }
        });
        btnNewButton_1_3.setForeground(Color.WHITE);
        btnNewButton_1_3.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnNewButton_1_3.setBackground(new Color(21, 88, 16));
        btnNewButton_1_3.setBounds(184, 11, 160, 30);
        panel_1.add(btnNewButton_1_3);
        JButton btnNewButton_1_3_1 = new JButton("GUARDAR GRAFO");
        btnNewButton_1_3_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String nombre = JOptionPane.showInputDialog("Ingrese un nombre al archivo");
                CompetGrafoModel grafoModel = new CompetGrafoModel(nombre);
                int idGrafo = CompetGrafoDao.insertEnlace(grafoModel);

                for(NodoCompet nodoCompet: paneld.getLista()){

                    CompetNodoModel nodoModel = new CompetNodoModel(idGrafo,nodoCompet.getX(), nodoCompet.getY(),
                            nodoCompet.getA(), nodoCompet.getB(), nodoCompet.getXx(), nodoCompet.getYy(),
                            nodoCompet.getName(), nodoCompet.getC().getRed(), nodoCompet.getC().getGreen(),
                            nodoCompet.getC().getBlue());
                    CompetNodoDao.insertNodo(nodoModel);
                }

                List<CompetNodoModel> listaNodosCompet = CompetNodoDao.getListNodoModelByIdGrafo(idGrafo);
                List<NodoCompet> lista = new ArrayList<>();
                for(CompetNodoModel nodoCompetModel : listaNodosCompet){
                    lista.add(new NodoCompet(nodoCompetModel.getX(), nodoCompetModel.getY(),
                            nodoCompetModel.getXx(), nodoCompetModel.getYy(),
                            nodoCompetModel.getB(), nodoCompetModel.getA(),
                            nodoCompetModel.getNombre(), new Color(nodoCompetModel.getRed(),
                            nodoCompetModel.getGreen(), nodoCompetModel.getBlue())));
                }

                for(EnlaceCompet enlaceCompet: paneld.getListae()){
                    System.out.println(enlaceCompet);
                }

                for(EnlaceCompet enlaceCompet: paneld.getListae()){
                    int idNodoInicio = 0;
                    int idNodoFin = 0;
                    for(CompetNodoModel nodoCompetModel : listaNodosCompet){
                        NodoCompet nodoCompet = new NodoCompet(nodoCompetModel.getX(), nodoCompetModel.getY(),
                                nodoCompetModel.getXx(), nodoCompetModel.getYy(),
                                nodoCompetModel.getB(), nodoCompetModel.getA(),
                                nodoCompetModel.getNombre(), new Color(nodoCompetModel.getRed(),
                                nodoCompetModel.getGreen(), nodoCompetModel.getBlue()));
                        if(enlaceCompet.getNi().getName().equals(nodoCompet.getName())){
                            idNodoInicio = nodoCompetModel.getId();
                            System.out.println("EL ID INICIO ES: "+idNodoInicio);
                        }
                    }

                    for(CompetNodoModel nodoCompetModel : listaNodosCompet){
                        NodoCompet nodoCompet = new NodoCompet(nodoCompetModel.getX(), nodoCompetModel.getY(),
                                nodoCompetModel.getXx(), nodoCompetModel.getYy(),
                                nodoCompetModel.getB(), nodoCompetModel.getA(),
                                nodoCompetModel.getNombre(), new Color(nodoCompetModel.getRed(),
                                nodoCompetModel.getGreen(), nodoCompetModel.getBlue()));
                        if(enlaceCompet.getNf().getName().equals(nodoCompet.getName())){
                            idNodoFin = nodoCompetModel.getId();
                        }
                    }
                    CompetEnlaceModel enlaceModel = new CompetEnlaceModel(enlaceCompet.getC().getRed(),
                            enlaceCompet.getC().getGreen(), enlaceCompet.getC().getBlue(),idNodoInicio,
                            idNodoFin, idGrafo);
                    CompetEnlaceDao.insertEnlace(enlaceModel);
                }
                /****

                imprimirListae();

                String name = JOptionPane.showInputDialog("Ingrese un nombre al archivo");
                String cad = "";
                for(int i=0;i<vectorNodos.size();i++){

                    if(i==vectorNodos.size()-1){
                        cad+=vectorNodos.get(i).getName()+","+(vectorNodos.get(i).getX()/70)+","+(vectorNodos.get(i).getY()/70);
                    }else{

                        cad+=vectorNodos.get(i).getName()+","+(vectorNodos.get(i).getX()/70)+","+(vectorNodos.get(i).getY()/70)+";";
                    }
                }

                CompetModel compet = new CompetModel();
                compet.setNombre(name);
                compet.setCadena(cad);

                CompetDao.insertCompet(compet);

                 ***/
                JOptionPane.showMessageDialog(null,"DATO AGREGADOS");
            }
        });
        btnNewButton_1_3_1.setForeground(Color.WHITE);
        btnNewButton_1_3_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnNewButton_1_3_1.setBackground(new Color(21, 88, 16));
        btnNewButton_1_3_1.setBounds(343, 11, 160, 30);
        panel_1.add(btnNewButton_1_3_1);

        JButton btnNewButton_1_3_2 = new JButton("CARGAR GRAFO");


        btnNewButton_1_3_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {



                List<CompetGrafoModel>lista = CompetGrafoDao.listGrafoCompet();
                String[]listaCombo= new String[lista.size()];
                for(int i=0;i<lista.size();i++) {
                    listaCombo[i] = lista.get(i).getNombre();
                }

                JComboBox comboBox = new JComboBox(listaCombo);
                comboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int ob=comboBox.getSelectedIndex();

                        for(int i=0;i<lista.size();i++) {
                            if(ob==i) {
                                index = lista.get(i).getId();
                            }
                        }
                    }
                });
                comboBox.setBounds(88, 62, 177, 20);
                JOptionPane.showMessageDialog(null, comboBox, "SELECCIONE UN ARCHIVO", 1);



                paneld.getLista().clear();
                vectorNodos.clear();
                paneld.getListae().clear();
                listae.clear();
                listaEnlace.clear();
                paneld.sw=false;
                repaint();
                List<CompetNodoModel>listaNodosModel = CompetNodoDao.getListNodoModelByIdGrafo(index);
                for(CompetNodoModel nodoModel: listaNodosModel){
                    paneld.getLista().add(new NodoCompet(nodoModel.getX(), nodoModel.getY(), nodoModel.getXx(),
                            nodoModel.getYy(), nodoModel.getB(), nodoModel.getA(),  nodoModel.getNombre(),
                            new Color(nodoModel.getRed(), nodoModel.getGreen(), nodoModel.getBlue())));

                    int a=60;
                    int aux=(int)Math.round(nodoModel.getXx());
                    int auy=(int)Math.round(nodoModel.getYy());
                    if(aux<10 && vectorNodos.size()<1)
                    {
                        mul=70;

                    }else if(aux<100 && vectorNodos.size()<1 )
                    {
                        mul=6;
                    }
                    if(aux>1000 && vectorNodos.size()<1)
                    {
                        mul=0.1;

                    }
                    aux=(int)Math.round(aux*mul);
                    auy=(int)Math.round(auy*mul);
                    paneld.setMul(mul);
                    vectorNodos.add(new NodoCompet(aux, auy, nodoModel.getXx(),
                            nodoModel.getYy(), nodoModel.getB(), nodoModel.getA(),  nodoModel.getNombre(),
                            new Color(nodoModel.getRed(), nodoModel.getGreen(), nodoModel.getBlue())));
                }
                List<CompetEnlaceModel>listaEnlaceModel = CompetEnlaceDao.getListEnlaceModelByIdGrafo(index);
                List<CompetNodoModel>listaNodoModel = CompetNodoDao.getListNodoModelByIdGrafo(index);

                for(CompetEnlaceModel enlaceModel: listaEnlaceModel){

                    int idNodoInicio = enlaceModel.getIdNodo1();
                    int idNodoFin = enlaceModel.getIdNodo2();
                    NodoCompet nodo1 = null;
                    NodoCompet nodo2 = null;


                    for(CompetNodoModel nodoModel: listaNodoModel){
                        if(idNodoInicio == nodoModel.getId()){
                            nodo1 = new NodoCompet(nodoModel.getX(), nodoModel.getY(), nodoModel.getXx(),
                                    nodoModel.getYy(), nodoModel.getB(), nodoModel.getA(), nodoModel.getNombre(),
                                    new Color(nodoModel.getRed(), nodoModel.getGreen(), nodoModel.getBlue()));
                        }
                    }

                    for(CompetNodoModel nodoModel: listaNodoModel){
                        if(idNodoFin == nodoModel.getId()){
                            nodo2 = new NodoCompet(nodoModel.getX(), nodoModel.getY(), nodoModel.getXx(),
                                    nodoModel.getYy(), nodoModel.getB(), nodoModel.getA(), nodoModel.getNombre(),
                                    new Color(nodoModel.getRed(), nodoModel.getGreen(), nodoModel.getBlue()));
                        }
                    }
                    EnlaceCompet enlaceCompet = new EnlaceCompet(nodo1,nodo2,"0",new Color(enlaceModel.getRed(),
                            enlaceModel.getGreen(), enlaceModel.getBlue()));
                    paneld.getListae().add(enlaceCompet);
                    listae.add(enlaceCompet);
                    listaEnlace.add(enlaceCompet);


                }
                repaint();




                /******************

                List<CompetModel> lista = CompetDao.getListCompet();
                String[]listaCombo= new String[lista.size()];
                for(int i=0;i<lista.size();i++) {
                    listaCombo[i] = lista.get(i).getNombre();
                }
                System.out.println(lista);
                JComboBox comboBox = new JComboBox(listaCombo);
                comboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int ob=comboBox.getSelectedIndex();
                        for(int i=0;i<lista.size();i++) {
                            if(ob==i) {
                                index = ob;

                            }
                        }
                        vectorNodos.clear();
                        repaint();

                        System.out.println("SELECCIONADO: "+index);
                        String cad[]=lista.get(index).getCadena().split(";");
                        System.out.println(lista.get(index).getCadena());
                        for(int i=0;i<cad.length;i++){
                            String[] data = cad[i].split(",");
                            String name = data[0];
                            int x = Integer.parseInt(data[1]);
                            int y = Integer.parseInt(data[2]);
                            int a=60;
                            int aux=(int)Math.round(x);
                            int auy=(int)Math.round(y);
                            if(aux<10 && vectorNodos.size()<1)
                            {
                                mul=70;

                            }else if(aux<100 && vectorNodos.size()<1 )
                            {
                                mul=6;
                            }
                            if(aux>1000 && vectorNodos.size()<1)
                            {
                                mul=0.1;

                            }
                            aux=(int)Math.round(aux*mul);
                            auy=(int)Math.round(auy*mul);
                            paneld.setMul(mul);
                            paneld.getLista().add(new NodoCompet(aux,auy,x,y,a,a,name,new Color(0, 102, 102)));
                            vectorNodos.add(new NodoCompet(aux,auy,x,y,a,a,name,new Color(0, 102, 102)));


                            agregarColumna();
                            repaint();
                        }

                        if(paneld.getListae().size()>1)
                        {
                            listae.clear();
                            for(EnlaceCompet enlace: paneld.getListae()) {
                                listae.add(enlace);
                                System.out.println("ADD ACTION 1");
                            }
                            System.out.println("Lista enlace Listener");
                            imprimirListae();
                        }

                        repaint();


                    }
                });
                comboBox.setBounds(88, 62, 177, 20);
                JOptionPane.showMessageDialog(null, comboBox, "SELECCIONE UN ARCHIVO", 1);******/
            }
        });
        btnNewButton_1_3_2.setForeground(Color.WHITE);
        btnNewButton_1_3_2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnNewButton_1_3_2.setBackground(new Color(21, 88, 16));
        btnNewButton_1_3_2.setBounds(502, 11, 160, 30);
        panel_1.add(btnNewButton_1_3_2);

        JButton btnNewButton_1_3_2_1 = new JButton("ATRAS");
        btnNewButton_1_3_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                VentanaPrincipal window = new VentanaPrincipal();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
                dispose();

            }
        });
        btnNewButton_1_3_2_1.setForeground(Color.WHITE);
        btnNewButton_1_3_2_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnNewButton_1_3_2_1.setBackground(new Color(21, 88, 16));
        btnNewButton_1_3_2_1.setBounds(663, 11, 160, 30);
        panel_1.add(btnNewButton_1_3_2_1);

        JButton btnAsignacion = new JButton("AGREGAR DATO");
        btnAsignacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try{
                    double x=Double.parseDouble(JOptionPane.showInputDialog("Ingrese x"));
                    double y=Double.parseDouble(JOptionPane.showInputDialog("Ingrese y"));


                    String nombre =JOptionPane.showInputDialog( "Ingrese nombre");
                    int a=60;
                    int aux=(int)Math.round(x);
                    int auy=(int)Math.round(y);
                    if(aux<10 && vectorNodos.size()<1)
                    {
                        mul=70;

                    }else if(aux<100 && vectorNodos.size()<1 )
                    {
                        mul=6;
                    }
                    if(aux>1000 && vectorNodos.size()<1)
                    {
                        mul=0.1;

                    }
                    aux=(int)Math.round(aux*mul);
                    auy=(int)Math.round(auy*mul);
                    paneld.setMul(mul);
                    paneld.getLista().add(new NodoCompet(aux,auy,x,y,a,a,nombre,new Color(9,11,48)));
                    vectorNodos.add(new NodoCompet(aux,auy,x,y,a,a,nombre,new Color(0, 102, 102)));
                    agregarColumna();
                    repaint();

                }catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null,"EL CUADRO DE TEXTO NO PUEDE ESTAR VACIO");
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"DEBE INGRESAR UN VALOR NUMERICO");
                }

            }
        });
        btnAsignacion.setForeground(Color.WHITE);
        btnAsignacion.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnAsignacion.setBackground(new Color(21, 88, 16));
        btnAsignacion.setBounds(25, 11, 160, 30);
        panel_1.add(btnAsignacion);


        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int option = JOptionPane.showConfirmDialog(null, "SE ELIMINARAN LOS DATOS \nESTA SEGURO DE LIMPIAR EL LIENZO");
                //System.out.println(lienzo.vertices);
                if(option ==0) {
                    paneld.getLista().clear();
                    vectorNodos.clear();
                    paneld.getListae().clear();
                    listae.clear();
                    listaEnlace.clear();
                    paneld.sw=false;
                    repaint();
                    JOptionPane.showMessageDialog(null, "EL GRAFO HA SIDO ELIMINADO");
                    //System.out.println(lienzo.vertices);
                }

            }
        });

        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnLimpiar.setBackground(new Color(21, 88, 16));
        btnLimpiar.setBounds(25, 52, 160, 30);
        panel_1.add(btnLimpiar);

        JButton btnTable = new JButton("VER DATOS");
        btnTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                activarVentanaDatos();

            }
        });
        btnTable.setForeground(Color.WHITE);
        btnTable.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnTable.setBackground(new Color(21, 88, 16));
        btnTable.setBounds(184, 52, 160, 30);
        panel_1.add(btnTable);

        /*****
        JButton btnRandomizar = new JButton("RANDOMIZAR");
        btnRandomizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    String strNumero = JOptionPane.showInputDialog("INGRESE EL NUMERO DE PUNTOS");


                    JTextField tfLim1 = new JTextField();
                    JTextField tfLim2 = new JTextField();

                    Object[] fieldsOrigen ={
                            new JLabel("Limite inferior"),
                            tfLim1,
                            new JLabel("Limite superior"),
                            tfLim2
                    };
                    JOptionPane.showConfirmDialog(null,fieldsOrigen,
                            "INGRESE LIMITES", JOptionPane.OK_CANCEL_OPTION);

                    int size = Integer.parseInt(strNumero);
                    int lim1 = Integer.parseInt(tfLim1.getText());
                    int lim2 = Integer.parseInt(tfLim2.getText());
                    System.out.println(size);
                    System.out.println(lim1);
                    System.out.println(lim2);

                    for(int i=0;i<size;i++){

                    }


                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"DEBE INGRESAR UN VALOR NUMERICO");
                }


            }
        });
        btnRandomizar.setForeground(Color.WHITE);
        btnRandomizar.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
        btnRandomizar.setBackground(new Color(21, 88, 16));
        btnRandomizar.setBounds(343, 52, 160, 30);
        panel_1.add(btnRandomizar);**/



    }
    public void activarVentanaDatos(){
        VentanaDataCompet window=new VentanaDataCompet(this);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    void imprimirListae()
    {
        for(EnlaceCompet enlace:listae)
        {
            System.out.print("Nodo inicial"+enlace.getNi().getName());
            System.out.print("Nodo final"+enlace.getNf().getName());
            System.out.println();
        }
    }

    public void crearListaE()
    {
        listaEnlace.clear();
        EnlaceCompet auxiliar=new EnlaceCompet(listae.get(0).getNi(),listae.get(0).getNf(),listae.get(0).getName(),listae.get(0).getC());

        for(int i=0;i<listae.size();i++)
        {
            for(int j=0;j<listae.size();j++)
            {
                if(auxiliar.getNf().getName().compareTo(listae.get(j).getNf().getName())!=0 || auxiliar.getNi().getName().compareTo(listae.get(j).getNi().getName())!=0)
                {
                    if (auxiliar.getNf().getName().compareTo(listae.get(j).getNi().getName())==0)
                    {
                        auxiliar.setNf(listae.get(j).getNf());
                        auxiliar.setNi(listae.get(j).getNi());
                        listaEnlace.add(new EnlaceCompet(listae.get(j).getNi(),listae.get(j).getNf(),listae.get(j).getName(),listae.get(j).getC()));
                        j=listae.size();

                    }else if(auxiliar.getNf().getName().compareTo(listae.get(j).getNf().getName())==0)
                    {
                        listae.get(j).setNf(listae.get(j).getNi());
                        listae.get(j).setNi(auxiliar.getNf());
                        auxiliar.setNf(listae.get(j).getNf());
                        auxiliar.setNi(listae.get(j).getNi());
                        listaEnlace.add(new EnlaceCompet(listae.get(j).getNi(),listae.get(j).getNf(),listae.get(j).getName(),listae.get(j).getC()));
                        j=listae.size();
                    }
                }




            }
        }
        int tp=0;
        do {
            obtenerPM();
            generarNL();
            tp++;
        }while(!verificarPunto() && tp<100);
        //textFieldX.setText(""+listaEnlace.get(0).getNi().getXx());
        //textFieldY.setText(""+listaEnlace.get(0).getNi().getYy());
        JOptionPane.showMessageDialog(null,"X= "+listaEnlace.get(0).getNi().getXx()+"\n"+"Y= "+listaEnlace.get(0).getNi().getYy());
        System.out.println(""+listaEnlace.get(0).getNi().getXx());
        System.out.println(""+listaEnlace.get(0).getNi().getYy());
        System.out.println("LISTA PUNTO MEDIO");
        for(NodoCompet nodoCompet: puntoMedio){
            System.out.println(nodoCompet);
        }


    }


    private void generarNL() {
        listaEnlace.clear();
        for(int i=0;i<puntoMedio.size();i++)
        {

            if(i==puntoMedio.size()-1)
            {
                listaEnlace.add(new EnlaceCompet(puntoMedio.get(i),puntoMedio.get(0), "0",Color.BLACK));
                paneld.getListapintar().add(new EnlaceCompet(puntoMedio.get(i),puntoMedio.get(0), "0",Color.BLACK));
            }else
            {
                listaEnlace.add(new EnlaceCompet(puntoMedio.get(i),puntoMedio.get(i+1), "0",Color.BLACK));
                paneld.getListapintar().add(new EnlaceCompet(puntoMedio.get(i),puntoMedio.get(i+1), "0",Color.BLACK));
            }
        }
    }

    private boolean verificarPunto()
    {
        boolean flag=true;
        double x=puntoMedio.get(0).getXx();
        double y=puntoMedio.get(0).getYy();
        for(NodoCompet nodo: puntoMedio )
        {

            if(nodo.getXx()!=x || y!=nodo.getYy())
            {
                flag=false;
            }


        }
        return flag;

    }
    private void obtenerPM(){
        puntoMedio.clear();
        int a=20;
        double x,y;
        for(EnlaceCompet enlace: listaEnlace)
        {
            x=(enlace.getNi().getXx()+enlace.getNf().getXx())/2;
            y=(enlace.getNi().getYy()+enlace.getNf().getYy())/2;
            x=((double)Math.round(x * 1000d) / 1000d);
            y=((double)Math.round(y * 1000d) / 1000d);
            puntoMedio.add(new NodoCompet(x,y,enlace.getNi().getName()));
        }

        //}

        for(NodoCompet nodo: puntoMedio)
        {
            x=nodo.getXx();
            y=nodo.getYy();
        }
    }

    public Vector<NodoCompet> getLista() {
        return vectorNodos;
    }

    public boolean verificarPoligono()
    {

        System.out.println("LISTA DE VECTORES");
        for(EnlaceCompet enlaceCompet: listae){
            System.out.println(enlaceCompet);
        }

        for(NodoCompet nodoCompet: vectorNodos){
            System.out.println(nodoCompet);
        }


        boolean flag=true;

        int con=0;
        for(int i=0;i<vectorNodos.size();i++)
        {
            if(listae.get(i).getNi().equals(listae.get(i).getNf()))
            {
                flag=false;
                break;
            }
            con=0;
            for(int j=0;j<listae.size();j++){
                if(listae.get(j).getNf().getName().compareTo(vectorNodos.get(i).getName())==0 || listae.get(j).getNi().getName().compareTo(vectorNodos.get(i).getName())==0)
                    con++;
            }
            if(con!=2)
            {
                flag=false;
                break;
            }
        }
        if(vectorNodos.size()!=listae.size())
        {
            flag=false;
        }
        return flag;
    }

    public void agregarColumna() {
        /**
        if(vectorNodos.size()==1)
        {
            modelo.setValueAt(vectorNodos.get(0).getName(), 0, 1);
            modelo.setValueAt(vectorNodos.get(0).getXx(), 1, 1);
            modelo.setValueAt(vectorNodos.get(0).getYy(), 2, 1);

        }else if(vectorNodos.size()>1) {
            int tamanio=vectorNodos.size();
            modelo.addColumn("");
            modelo.setValueAt(vectorNodos.get(tamanio-1).getName(), 0, tamanio);
            modelo.setValueAt(vectorNodos.get(tamanio-1).getXx(), 1, tamanio);
            modelo.setValueAt(vectorNodos.get(tamanio-1).getYy(), 2, tamanio);
        }**/
    }

    public ArrayList<EnlaceCompet> getListae() {
        return listae;
    }

    public void setListae(ArrayList<EnlaceCompet> listae) {
        this.listae = listae;
    }
}
