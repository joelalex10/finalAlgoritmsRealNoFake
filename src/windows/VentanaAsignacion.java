package windows;
import Database.EnlaceBDD;
import Database.GrafoBDD;
import Database.NodoBDD;
import grafos.Enlace;
import grafos.Nodo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.util.Arrays.stream;

public class VentanaAsignacion {

	private int origen;
	private int destino;

	public JFrame frame;
	LienzoAsignacion lienzoAsignacion;
	Object [][]mat;
	String []listaOrigen;

	String []listaDestino;
	int index = 0;
	boolean isAsignacion=false;

	int actOrigen = -1;
	int actDestino = -1;

	public String titleWindow= "ALGORITMO DE ASIGNACION";
	private int[][] matrix;
	private int[][] MatrizSolucion;

	ExecutorService es = Executors.newFixedThreadPool(2);
	boolean[] rowDone = new boolean[origen];
	boolean[] colDone = new boolean[destino];
	int[][] result = new int[origen][destino];

	public VentanaAsignacion(int origen, int destino) {
		this.origen = origen;
		this.destino = destino;
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame(titleWindow);
		frame.setBounds(100, 100, 890, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(100,100, 850, 500);
		lienzoAsignacion = new LienzoAsignacion(this);
		lienzoAsignacion.setBounds(0, 0, 864, 500);
		frame.getContentPane().add(lienzoAsignacion);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 510, 864, 87);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton_1_3 = new JButton("LIMPIAR");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "SE ELIMINARAN LOS DATOS \nESTA SEGURO DE LIMPIAR EL LIENZO");
				//System.out.println(lienzo.vertices);
				if(option ==0) {
					lienzoAsignacion.g.eliminarGrafo(lienzoAsignacion.vertices);
					lienzoAsignacion.vectorEnlace.clear();
					lienzoAsignacion.vectorNodosDestino.clear();
					lienzoAsignacion.vectorNodosOrigen.clear();
					lienzoAsignacion.contadorInicio = 0;
					lienzoAsignacion.contadorDestino = 0;
					lienzoAsignacion.repaint();
					JOptionPane.showMessageDialog(null, "EL GRAFO HA SIDO ELIMINADO");
					//System.out.println(lienzo.vertices);
				}
				
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
				String newNombreNodo = JOptionPane.showInputDialog("Ingrese un nombre a guardar");
				GrafoBDD grafo = new GrafoBDD();
				grafo.setNombre(newNombreNodo);
				int iddGrafo = grafo.insertGrafo();
				System.out.println("El ultimo Id es: "+iddGrafo);
				for(Nodo nodo: lienzoAsignacion.vectorNodosOrigen) {
					NodoBDD nodoBDD = new NodoBDD();
					nodoBDD.setX(nodo.getX());
					nodoBDD.setY(nodo.getY());
					nodoBDD.setNombre(nodo.getNombre());
					nodoBDD.setIdGrafo(iddGrafo);
					nodoBDD.setColorRed(nodo.getColor().getRed());
					nodoBDD.setColorGreen(nodo.getColor().getGreen());
					nodoBDD.setColorBlue(nodo.getColor().getBlue());
					nodoBDD.setNroActividad(nodo.getNroActividad());
					int idNodo = nodoBDD.insertNodo();
				}
				for(Nodo nodo: lienzoAsignacion.vectorNodosDestino) {
					NodoBDD nodoBDD = new NodoBDD();
					nodoBDD.setX(nodo.getX());
					nodoBDD.setY(nodo.getY());
					nodoBDD.setNombre(nodo.getNombre());
					nodoBDD.setIdGrafo(iddGrafo);
					nodoBDD.setColorRed(nodo.getColor().getRed());
					nodoBDD.setColorGreen(nodo.getColor().getGreen());
					nodoBDD.setColorBlue(nodo.getColor().getBlue());
					nodoBDD.setNroActividad(nodo.getNroActividad());
					int idNodo = nodoBDD.insertNodo();
				}
				NodoBDD consultaNodo = new NodoBDD();
				consultaNodo.setIdGrafo(iddGrafo);
				ArrayList<NodoBDD>lista= consultaNodo.getNodoByGrafoId();

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
				VentanaPrincipal window=new VentanaPrincipal();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_3_2_1.setForeground(Color.WHITE);
		btnNewButton_1_3_2_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_3_2_1.setBackground(new Color(21, 88, 16));
		btnNewButton_1_3_2_1.setBounds(663, 11, 160, 30);
		panel_1.add(btnNewButton_1_3_2_1);

		JButton btnAsignacion = new JButton("EJECUTAR");
		btnAsignacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String []menu= {"Maximo","Minimo"};
				int optimo[][] = null;
				String valor = (String)JOptionPane.showInputDialog(null, "seleccionar opcion", "opciones",JOptionPane.DEFAULT_OPTION, null, menu,menu[0]);
				if (valor.equalsIgnoreCase ("Maximo")) {
					if(lienzoAsignacion.vectorNodosDestino.size() !=lienzoAsignacion.vectorNodosOrigen.size()){
						int max = lienzoAsignacion.vectorNodosDestino.size();
						int matAux[][] = new int[max][max];
						for(int i=0;i<max;i++){
							for(int j=0;j<max;j++){
								try{
									matAux[i][j]=lienzoAsignacion.matrizCostos[i][j];
								}catch (ArrayIndexOutOfBoundsException e){
									matAux[i][j] = 0;
								}

							}
						}
						AlgoritmoAsignacion cc = new AlgoritmoAsignacion (matAux, true);
						cc.efectuarAlgoritmo();
						optimo = cc.getMatrizOptimo();
					}else{
						AlgoritmoAsignacion cc = new AlgoritmoAsignacion (lienzoAsignacion.matrizCostos, true);
						cc.efectuarAlgoritmo();
						optimo = cc.getMatrizOptimo();
					}
				}
				if (valor.equalsIgnoreCase ("Minimo")) {
					if(lienzoAsignacion.vectorNodosOrigen.size() != lienzoAsignacion.vectorNodosDestino.size()){
						int max = lienzoAsignacion.vectorNodosDestino.size();
						int matAux[][] = new int[max][max];
						for(int i=0;i<max;i++){
							for(int j=0;j<max;j++){
								try{
									matAux[i][j]=lienzoAsignacion.matrizCostos[i][j];
								}catch (ArrayIndexOutOfBoundsException e){
									matAux[i][j] = 0;
								}
							}
						}
						AlgoritmoAsignacion cc = new AlgoritmoAsignacion (matAux, true);
						cc.efectuarAlgoritmo();
						optimo = cc.getMatrizOptimo();
					}
				}else{
					AlgoritmoAsignacion cc = new AlgoritmoAsignacion (lienzoAsignacion.matrizCostos, false);
					cc.efectuarAlgoritmo();
					optimo = cc.getMatrizOptimo();
				}
				int mat[][] = lienzoAsignacion.matrizCostos;
				VentanaResultsAsignacion window=new VentanaResultsAsignacion(origen, destino, lienzoAsignacion.vectorNodosOrigen, lienzoAsignacion.vectorNodosDestino, mat);
				window.setLocationRelativeTo(null);
				window.setVisible(true);

				JFrame ventanaMatriz = new JFrame("Matriz de Adyacentes");
				ventanaMatriz.setSize(500, 300);
				JTextArea matriz  = new JTextArea(lienzoAsignacion.imprimirMatrizAdyString(optimo));
				ventanaMatriz.add(new JScrollPane(matriz), BorderLayout.CENTER);
				ventanaMatriz.setVisible(true);
				ventanaMatriz.setLocationRelativeTo(null);

			}
		});
		btnAsignacion.setForeground(Color.WHITE);
		btnAsignacion.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnAsignacion.setBackground(new Color(21, 88, 16));
		btnAsignacion.setBounds(25, 11, 160, 30);
		panel_1.add(btnAsignacion);

		JButton btnKramer = new JButton("KRAMER");
		btnKramer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> ofertas=generarArreglo(lienzoAsignacion.vectorNodosOrigen);
				ArrayList<Integer> demandas=generarArreglo(lienzoAsignacion.vectorNodosDestino);
				int mat[][] = lienzoAsignacion.matrizCostos;

				String []menu= {"Maximo","Minimo"};
				int optimo[][] = null;
				String valor = (String)JOptionPane.showInputDialog(null, "seleccionar opcion", "opciones",JOptionPane.DEFAULT_OPTION, null, menu,menu[0]);

				if (valor.equalsIgnoreCase ("Maximo")) {

					MatrizSolucion=Noro9(mat,demandas,ofertas,origen,destino);
					JTable tabla= printMatrix(MatrizSolucion,origen,destino);
					tabla.setBounds(60,400,400,200);
					tabla.setAutoResizeMode(1);
					frame.add(tabla);
					mostrar(MatrizSolucion);
					CostoTotal(mat,MatrizSolucion);
				}
				if (valor.equalsIgnoreCase ("Minimo")) {
				}
			}
		});

		btnKramer.setForeground(Color.WHITE);
		btnKramer.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnKramer.setBackground(new Color(21, 88, 16));
		btnKramer.setBounds(25, 52, 160, 30);
		panel_1.add(btnKramer);
		
	}
	public static void CostoTotal(int[][]matriz,int[][]matrizSolu){

		int suma=0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				suma= suma+(matriz[i][j]*matrizSolu[i][j]);
			}

		}

		JOptionPane.showMessageDialog (null, suma);
	}
	public void mostrar(int matrizAdy[][]) {
		int c =matrizAdy.length;
		String[] m= new String[c];
		for(int i = 0;i < c; i++) {
			String v ="";
			for(int j = 0; j < matrizAdy[0].length; j++) {
				v+= matrizAdy[i][j]+"   ";
			}
			m[i]=v;
		}
		JOptionPane.showMessageDialog (null, m);

	}

	public int[][] Noro9(int[][]matrix,ArrayList<Integer> demanda, ArrayList<Integer> oferta, int matrixRow, int matrixCol){
		int[][] matrizSolucion= new int[matrixRow][matrixCol];
		for(int col=0;col<matrixRow;col++){
			for(int fil=0;fil<matrixCol;fil++){
				if (demanda.get(fil)<=oferta.get(col)){
					matrizSolucion[col][fil]=demanda.get(fil);
					oferta.set(col,oferta.get(col)-demanda.get(fil));
					demanda.set(fil,0);
				}else{
					matrizSolucion[col][fil]=oferta.get(col);
					demanda.set(fil,demanda.get(fil)-oferta.get(col));
					oferta.set(col,0);
				}
			}

		}

		return matrizSolucion;
	}

	private ArrayList<Integer> generarArreglo(Vector<Nodo> vectorNodos) {
		Object[] fieldsOrigen = new Object[vectorNodos.size()*2];
		ArrayList<Integer> matIntOrigen = new ArrayList<Integer>();

		for(int i=0;i<vectorNodos.size();i++){
			fieldsOrigen[i*2] = vectorNodos.get(i).getNombre();
		}
		for(int i=0;i<vectorNodos.size();i++){
			fieldsOrigen[i*2+1] = new JTextField();;
		}
		JOptionPane.showConfirmDialog(null,fieldsOrigen,
				"LLENE LOS RECUADROS", JOptionPane.OK_CANCEL_OPTION);

		for(int i=0;i<vectorNodos.size();i++){
			JTextField auxiiar = (JTextField)fieldsOrigen[i*2+1];
			try{
				matIntOrigen.add(Integer.parseInt(auxiiar.getText()));
			}catch (NumberFormatException e){
				matIntOrigen.add(0);
			}
		}
		for(int i=0;i<vectorNodos.size();i++){
			System.out.println(matIntOrigen.get(i));
		}
		return matIntOrigen;
	}


	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public static JTable printMatrix(int[][] matrix, int matrixRow, int matrixCol) {
		System.out.println("Your Matrix is : ");
		JTable tblMatriz = new JTable();
		DefaultTableModel matri = (DefaultTableModel) tblMatriz.getModel();
		matri.setRowCount(matrixRow);
		matri.setColumnCount(matrixCol);
		for (int i = 0; i < matrixRow; i++) {
			for (int j = 0; j < matrixCol; j++) {
				tblMatriz.setValueAt(matrix[i][j],i,j);
			}

		}

		return tblMatriz;
	}
}
