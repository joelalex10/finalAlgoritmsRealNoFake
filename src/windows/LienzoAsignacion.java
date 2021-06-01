package windows;

import grafos.Enlace;
import grafos.Grafos;
import grafos.Nodo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class LienzoAsignacion extends JPanel implements MouseListener, MouseMotionListener {

    private VentanaAsignacion wAsignacion;
    public int  matrizCostos [][];

    /***public Vector<Nodo> vectorNodos*/
    public Vector<Nodo> vectorNodosOrigen;
    public Vector<Nodo> vectorNodosDestino;
    public Vector<Enlace>vectorEnlace;
    public Point p1, p2;
    public int actividadSelect;

    Grafos g;
    ArrayList<String> vertices;
    HashMap<String,Integer> aux;

    private Nodo auxNodoInicio = null;

    public int contadorInicio = 0;
    public int contadorDestino = 0;
    private int inicio;
    private int fin;

    public LienzoAsignacion(VentanaAsignacion wAsignacion) {
        this.setBackground(new Color(66,68,65));
        this.setBounds(0, 0, 400, 300);

        this.wAsignacion = wAsignacion;
        /**this.vectorNodos=new Vector<>();*/
        this.vectorNodosOrigen= new Vector<>();
        this.vectorNodosDestino= new Vector<>();
        this.vectorEnlace= new Vector<>();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        g = new Grafos();
        vertices=new ArrayList<String>();
        matrizCostos = new int[wAsignacion.getOrigen()][wAsignacion.getDestino()];


    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getButton() == MouseEvent.BUTTON1) {
            String []menu= {"Origen","Destino"};
            int optimo[][] = null;
            String valor = (String)JOptionPane.showInputDialog(null, "seleccionar opcion", "opciones",JOptionPane.DEFAULT_OPTION, null, menu,menu[0]);

            if(valor == menu[0]){
                addNodo(vectorNodosOrigen,e, wAsignacion.getOrigen(), contadorInicio, new Color(9,11,48));
                contadorInicio++;

            }
            if(valor == menu[1]){
                addNodo(vectorNodosDestino,e, wAsignacion.getDestino(), contadorDestino, new Color(156,12,12));
                contadorDestino++;
            }
        }

        if(e.getButton() == MouseEvent.BUTTON3){


            for(Nodo nodo: vectorNodosOrigen){
                if(new Rectangle(nodo.getX() - Nodo.d/2, nodo.getY() -
                        Nodo.d/2, Nodo.d*3, Nodo.d).contains(e.getPoint())) {
                    if(p1==null){
                        System.out.println("NODO SELECCIONADO");
                        p1 = new Point(nodo.getX(), nodo.getY());
                        inicio = nodo.getNroActividad();
                        nodo.setColor(Color.green);
                        auxNodoInicio = nodo;
                        repaint();
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null, "Un origen no puede ser destino",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        for(Nodo item: vectorNodosOrigen){
                            if(item.getNroActividad() == auxNodoInicio.getNroActividad()){
                                item.setColor(new Color(9,11,48));

                            }
                        }
                        repaint();
                        p1 = null;

                    }
                }
            }

            for(Nodo nodo: vectorNodosDestino){
                if(new Rectangle(nodo.getX() - Nodo.d/2, nodo.getY() -
                        Nodo.d/2, Nodo.d*3, Nodo.d).contains(e.getPoint())) {
                    if(p1==null){
                        JOptionPane.showMessageDialog(null, "Un destino no puede ser origen",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        p2 = new Point(nodo.getX(), nodo.getY());
                        fin=nodo.getNroActividad();
                        nodo.setColor(Color.green);
                        repaint();
                        int peso = Integer.parseInt(JOptionPane.showInputDialog("Introduce valor para el Enlace"));

                        Enlace enlace = new Enlace();
                        enlace.setX1(p1.x);
                        enlace.setY1(p1.y);
                        enlace.setX2(p2.x);
                        enlace.setY2(p2.y);
                        enlace.setAtributo(peso);
                        enlace.setNroActividadNodoInicio(inicio);
                        enlace.setNroActividadNodoFin(fin);
                        vectorEnlace.add(enlace);

                        /******
                        System.out.println("Segundo indice "+i.getNombre());
                        insertaArista(inicio, i.getNroActividad()-wAsignacion.getOrigen(), enlace.getAtributo());*/

                        repaint();
                        insertaArista(inicio, fin, enlace.getAtributo());
                        p1 = null;
                        p2 = null;
                        System.out.println("EL auxNodo es: "+inicio);
                        for(Nodo item: vectorNodosOrigen){
                            if(item.getNroActividad() == inicio){
                                item.setColor(new Color(9,11,48));
                            }
                        }
                        nodo.setColor(new Color(156,12,12));
                        repaint();

                    }
                }
            }
        }
    }
    public void addNodo(Vector<Nodo> vectorNodos, MouseEvent e, int maxNodo, int c, Color color){
        System.out.println("Se selecciono maximo");
        if(vectorNodos.size() >= maxNodo){
            JOptionPane.showMessageDialog(null, "Usted a superado el numero maximo de nodos de origen",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String nombre = JOptionPane.showInputDialog("Introduce nombre para el Nodo");
            if(!existeOtroIgual(nombre,vectorNodos)) {
                Nodo nodo = new Nodo();
                nodo.setX(e.getX());
                nodo.setY(e.getY());
                nodo.setNombre(nombre);
                nodo.setNroActividad(c);
                nodo.setColor(color);
                vectorNodos.add(nodo);
                repaint();
                c++;
            } else {
                JOptionPane.showMessageDialog(null, "Cambie de Nombre",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        System.out.println("");
        for(Nodo nodo: vectorNodos){
            System.out.println(nodo);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        /**for(Nodo nodos : vectorNodos) {
            nodos.pintar(g);
        }*/

        for(Nodo nodos : vectorNodosOrigen) {
            nodos.pintar(g);
        }
        for(Nodo nodos : vectorNodosDestino) {
            nodos.pintar(g);
        }
        for(Enlace enlace:vectorEnlace) {
            enlace.pintar(g);
        }


        //g.setColor(Color.blue);
        //g.drawLine(0, 0, 400, 300);
        //g.fillOval(10, 10, 25, 25);
    }

    public ArrayList<String> getVertices() {
        return vertices;

    }

    public void setVertices(ArrayList<String> vertices) {
        this.vertices = vertices;
    }

    public Object[][] retornoMatriz(){

        int tam=vertices.size();
        Object mat[][]=new Object[tam][tam];

        for(int i=0;i<vertices.size();i++) {

            for(int j=0;j<vertices.size();j++) {

                if(g.getVertice(vertices.get(i)).get(vertices.get(j))==null){
                    //System.out.print(0+"\t");
                    mat[i][j] = 0;
                }else {
                    //System.out.print(g.getVertice(vertices.get(i)).get(vertices.get(j))+"\t");
                    mat[i][j] = g.getVertice(vertices.get(i)).get(vertices.get(j));
                }
            }
        }

        return mat;
    }

    public int[][] generarMatriz(){

        int tam=vertices.size();
        int mat[][]=new int[tam][tam];

        for(int i=0;i<wAsignacion.getOrigen();i++) {

            for(int j=0;j<wAsignacion.getDestino();j++) {

                if(g.getVertice(vertices.get(i)).get(vertices.get(j))==null){
                    //System.out.print(0+"\t");
                    mat[i][j] = 0;
                }else {
                    //System.out.print(g.getVertice(vertices.get(i)).get(vertices.get(j))+"\t");
                    mat[i][j] = g.getVertice(vertices.get(i)).get(vertices.get(j));
                }
            }
        }

        return mat;
    }

    public String imprimirMatrizAdyString(int[][] y) {
        String m = "";
        for(int i = 0;i < y.length; i++) {
            m = m.concat("\n");
            for(int j = 0; j < y[0].length; j++) {
                m = m.concat(String.valueOf(y[i][j])).concat("    ");
            }
        }
        return m;
    }


    private boolean existeOtroIgual(String nombre, Vector<Nodo> vectorNodos) {
        boolean flag = false;
        for(Nodo i : vectorNodos) {
            if((nombre.equals(i.getNombre()))){
                flag = true;
            }
        }
        return flag;
    }

    public void insertaArista(int i, int j, int peso) {
        matrizCostos [i] [j] = peso;
    }

    public void cambiarNombre(Vector<Nodo> vectorNodos) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de nodo a modificar");
        boolean control = false;

        for(Nodo nodo: vectorNodos){
            if(nodo.getNombre().equals(nombre)){
                String newNombre = JOptionPane.showInputDialog("Ingrese el un nuevo nombre");
                nodo.setNombre(newNombre);
                control = true;
                break;
            }
        }
        if(control){
           JOptionPane.showMessageDialog(null,"SE HA CAMBIADO EL NOMBRE");
        }else{
            JOptionPane.showMessageDialog(null,"NO SE HA ENCONTRADO NINGUN NODO CON ESE NOMBRE");
        }
        repaint();
    }
}
