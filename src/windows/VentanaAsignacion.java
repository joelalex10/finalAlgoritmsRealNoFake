package windows;
import Algoritmos.HungarianC.HungarianAlgorithm;
import Algoritmos.Kramer;
import Database.Asignacion.AsignacionEnlaceDao;
import Database.Asignacion.AsignacionGrafDao;
import Database.Asignacion.AsignacionNodoDao;
import Database.EnlaceBDD;
import Database.GrafoBDD;
import Database.NodoBDD;
import grafos.Enlace;
import grafos.Grafo;
import grafos.Nodo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.util.Arrays.stream;

public class VentanaAsignacion {

	private int origen;
	private int destino;
	private int option;
	int optimo[][] = null;
	public JFrame frame;
	LienzoAsignacion lienzoAsignacion;
	Object [][]mat;
	String []listaOrigen;

	String []listaDestino;
	int index = 0;
	boolean isAsignacion=false;

	int actOrigen = -1;
	int actDestino = -1;

	private int[][] MatrizSolucion;
	private String titleWindow= "";
	public VentanaAsignacion(int origen, int destino, int option) {
		this.origen = origen;
		this.destino = destino;
		this.option = option;

		initialize();

	}

	private void initialize() {
		frame = new JFrame(titleWindow);
		switch (option){
			case 1:
				titleWindow= "ALGORITMO DE ASIGNACION";
				frame.setTitle(titleWindow);
				break;
			case 2:
				titleWindow= "ALGORITMO DE KRAMER";
				frame.setTitle(titleWindow);
				break;
		}

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
				Grafo grafo = new Grafo();
				grafo.setNombre(newNombreNodo);
				int idGrafo = AsignacionGrafDao.addGrafo(grafo);

				for(Nodo nodo: lienzoAsignacion.vectorNodosOrigen){
					nodo.setIdGrafo(idGrafo);
					nodo.setAsignacion(1);
					AsignacionNodoDao.insertNodo(nodo);
				}

				for(Nodo nodo: lienzoAsignacion.vectorNodosDestino){
					nodo.setIdGrafo(idGrafo);
					nodo.setAsignacion(2);
					AsignacionNodoDao.insertNodo(nodo);
				}

				List<Nodo> listaNodoOrigenes = AsignacionNodoDao.getNodoByIdGrafoAndAsignation(idGrafo,1);
				List<Nodo>listaNodoDestino = AsignacionNodoDao.getNodoByIdGrafoAndAsignation(idGrafo,2);
				int idNodoInicio = 0;
				int idNodoFin = 0;

				for(Enlace enlace:lienzoAsignacion.vectorEnlace){
					for(Nodo nodo: listaNodoOrigenes){
						if(enlace.getNroActividadNodoInicio()==nodo.getNroActividad()){
							idNodoInicio= nodo.getIdNodo();
							break;
						}
					}
					for(Nodo nodo: listaNodoDestino){
						if(enlace.getNroActividadNodoFin()==  nodo.getNroActividad()){
							idNodoFin = nodo.getIdNodo();
							break;
						}
					}

					enlace.setIdGrafo(idGrafo);
					enlace.setIdNodoInicio(idNodoInicio);
					enlace.setIdNodoFin(idNodoFin);
					System.out.print("EL ID origen ES: "+enlace.getIdNodoInicio());
					System.out.print("EL ID destino ES: "+enlace.getIdNodoFin());
					AsignacionEnlaceDao.insertEnlace(enlace);

				}
				JOptionPane.showMessageDialog(null,"SE HAN REGISTRADO LOS DATOS");
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
				List<Grafo> lista = AsignacionGrafDao.listGrafos();
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
								index = lista.get(i).getIdGrafo();
							}
						}

					}
				});
				comboBox.setBounds(88, 62, 177, 20);
				JOptionPane.showMessageDialog(null, comboBox, "SELECCIONE UN ARCHIVO", 1);

				lienzoAsignacion.g.eliminarGrafo(lienzoAsignacion.vertices);
				lienzoAsignacion.vectorEnlace.clear();
				lienzoAsignacion.vectorNodosDestino.clear();
				lienzoAsignacion.vectorNodosOrigen.clear();
				lienzoAsignacion.contadorInicio = 0;
				lienzoAsignacion.contadorDestino = 0;
				lienzoAsignacion.repaint();

				List<Nodo>listNodosOrigen = AsignacionNodoDao.getNodoByIdGrafoAndAsignation(index,1);
				List<Nodo>listNodosDestino = AsignacionNodoDao.getNodoByIdGrafoAndAsignation(index,2);

				origen = listNodosOrigen.size();
				destino = listNodosDestino.size();
				lienzoAsignacion.matrizCostos = new int[origen][destino];

				for (Nodo nodo:listNodosOrigen){
					lienzoAsignacion.vectorNodosOrigen.add(nodo);
				}

				for (Nodo nodo:listNodosDestino){
					lienzoAsignacion.vectorNodosDestino.add(nodo);
				}
				List<Enlace>listEnlaces = AsignacionEnlaceDao.getListEnlaceByIdGrafo(index);
				System.out.println(listEnlaces.size());
				for(Enlace enlace:listEnlaces){
					lienzoAsignacion.vectorEnlace.add(enlace);
					lienzoAsignacion.insertaArista(enlace.getNroActividadNodoInicio(), enlace.getNroActividadNodoFin(), enlace.getAtributo());
				}




				lienzoAsignacion.repaint();




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
				/**
				System.out.println("");
				for (Nodo nodo:lienzoAsignacion.vectorNodosOrigen){
					System.out.println(nodo);
				}
				System.out.println("");
				for (Nodo nodo:lienzoAsignacion.vectorNodosDestino){
					System.out.println(nodo);
				}
				System.out.println("");
				for(Enlace enlace:lienzoAsignacion.vectorEnlace){
					System.out.println(enlace);
				}**/
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
				switch (option){
					case 1:
						ejecutarAsignacion();
						break;
					case 2:
						ejecutarKramer();
						break;
				}

			}
		});
		btnAsignacion.setForeground(Color.WHITE);
		btnAsignacion.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnAsignacion.setBackground(new Color(21, 88, 16));
		btnAsignacion.setBounds(25, 11, 160, 30);
		panel_1.add(btnAsignacion);


		
	}

	private void ejecutarKramer() {
		int[] ofertas=generarArreglo(lienzoAsignacion.vectorNodosOrigen, "ENTER SUPPLIES");
		int[] demandas=generarArreglo(lienzoAsignacion.vectorNodosDestino, "ENTER DEMANDS");
		int mat[][] = lienzoAsignacion.matrizCostos;

		String []menu= {"Maximo","Minimo"};
		int optimo[][] = null;
		String valor = (String)JOptionPane.showInputDialog(null, "seleccionar opcion", "opciones",JOptionPane.DEFAULT_OPTION, null, menu,menu[0]);

		if (valor.equalsIgnoreCase ("Minimo")) {

			Kramer kramer = new Kramer(demandas,ofertas,mat);
			kramer.init();
			kramer.northWestCornerRule();
			kramer.minimizar();
			int [][] matrix = kramer.printResult();
			for(int i=0;i<ofertas.length;i++){
				for(int j=0;j<demandas.length;j++){
					System.out.print(matrix[i][j]+"\t");
				}
				System.out.println("");
			}
			System.out.println("EL TOTAL DE COSTOS ES: "+kramer.getTotalCosts());
			VentanaResultsKramer window=new VentanaResultsKramer(origen, destino, lienzoAsignacion.vectorNodosOrigen, lienzoAsignacion.vectorNodosDestino, ofertas, demandas, mat,matrix, kramer.getTotalCosts());
			window.setLocationRelativeTo(null);
			window.setVisible(true);
		}
		if (valor.equalsIgnoreCase ("Maximo")) {

			Kramer kramer = new Kramer(demandas,ofertas,mat);
			kramer.init();
			kramer.northWestCornerRule();
			kramer.maximizar();
			int [][] matrix = kramer.printResult();
			for(int i=0;i<ofertas.length;i++){
				for(int j=0;j<demandas.length;j++){
					System.out.print(matrix[i][j]+"\t");
				}
				System.out.println("");
			}
			System.out.println("EL TOTAL DE COSTOS ES: "+kramer.getTotalCosts());
			VentanaResultsKramer window=new VentanaResultsKramer(origen, destino, lienzoAsignacion.vectorNodosOrigen, lienzoAsignacion.vectorNodosDestino, ofertas, demandas, mat,matrix, kramer.getTotalCosts());
			window.setLocationRelativeTo(null);
			window.setVisible(true);


		}
	}

	private void ejecutarMaximo(double matDouble[][]){

		System.out.println("");
		for(int i=0;i<matDouble.length;i++){
			for(int j=0;j<matDouble.length;j++){
				System.out.print(matDouble[i][j]+"\t");
			}
			System.out.println("");
		}

		HungarianAlgorithm hungarianAlgorithm1 = new HungarianAlgorithm(matDouble);
		System.out.println("EL MAXIMO ES: "+hungarianAlgorithm1.hgAlgorithm("max"));
		int[][] MaxMatrixPositions1=hungarianAlgorithm1.hgAlgorithmAssignments("max");

		hungarianAlgorithm1.restas("max");
		VentanaResultsAsignacion window=new VentanaResultsAsignacion(origen, destino, lienzoAsignacion.vectorNodosOrigen,
				lienzoAsignacion.vectorNodosDestino, matDouble, hungarianAlgorithm1.restas("max"),hungarianAlgorithm1.matString("max"),hungarianAlgorithm1.hgAlgorithm("max"), lienzoAsignacion.vectorEnlace);
		lienzoAsignacion.repaint();
		window.setLocationRelativeTo(null);
		window.setVisible(true);



	}

	private void ejecutarMinimo(double[][] matDouble) {
		System.out.println("");
		for(int i=0;i<matDouble.length;i++){
			for(int j=0;j<matDouble.length;j++){
				System.out.print(matDouble[i][j]+"\t");
			}
			System.out.println("");
		}

		HungarianAlgorithm hungarianAlgorithm1 = new HungarianAlgorithm(matDouble);
		System.out.println("EL MINIMO ES: "+hungarianAlgorithm1.hgAlgorithm("min"));
		int[][] MaxMatrixPositions1=hungarianAlgorithm1.hgAlgorithmAssignments("min");
		hungarianAlgorithm1.restas("min");
	}
	private void ejecutarAsignacion() {
		String []menu= {"Maximo","Minimo"};
		System.out.println("");
		for(int i=0;i<lienzoAsignacion.vectorNodosOrigen.size();i++){
			for(int j=0;j<lienzoAsignacion.vectorNodosDestino.size();j++){
				System.out.print(lienzoAsignacion.matrizCostos[i][j]+"\t");
			}
			System.out.println("");
		}
		int sizeOrigen = lienzoAsignacion.vectorNodosOrigen.size();
		int sizeDestino = lienzoAsignacion.vectorNodosDestino.size();
		double matDouble[][] = new double[sizeDestino][sizeDestino];
		if(sizeOrigen != sizeDestino){
			for(int i=0;i<sizeDestino;i++){
				for(int j=0;j<sizeDestino;j++){
					try{
						matDouble[i][j]=lienzoAsignacion.matrizCostos[i][j];
					}catch (ArrayIndexOutOfBoundsException e){
						matDouble[i][j] = 0;
					}
				}
			}
		}else{
			for(int i=0;i<sizeDestino;i++){
				for(int j=0;j<sizeDestino;j++){
					matDouble[i][j]=lienzoAsignacion.matrizCostos[i][j];
				}
			}
		}
		String valor = (String)JOptionPane.showInputDialog(null, "seleccionar opcion", "opciones",JOptionPane.DEFAULT_OPTION, null, menu,menu[0]);

		if (valor.equalsIgnoreCase ("Maximo")) {
			ejecutarMaximo(matDouble);
		}
		if (valor.equalsIgnoreCase ("Minimo")) {
			ejecutarMinimo(matDouble);
		}


		//System.out.print("tamaño: "+optimo.length);
		/**
		System.out.print("matriz");
		for(int i=0;i<optimo.length;i++){
			for(int j=0;j<optimo.length;j++){
				System.out.print(optimo[i][j]+"\t");
			}
			System.out.println("");
		}

		JTextArea matriz  = new JTextArea(lienzoAsignacion.imprimirMatrizAdyString(optimo));

		JFrame ventanaMatriz = new JFrame("Matriz de Adyacentes");
		ventanaMatriz.setSize(500, 300);
		ventanaMatriz.add(new JScrollPane(matriz), BorderLayout.CENTER);
		ventanaMatriz.setVisible(true);
		ventanaMatriz.setLocationRelativeTo(null);**/

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

	private int[] generarArreglo(Vector<Nodo> vectorNodos, String titleWindow) {
		Object[] fieldsOrigen = new Object[vectorNodos.size()*2];
		int[] matIntOrigen = new int[vectorNodos.size()];

		for(int i=0;i<vectorNodos.size();i++){
			fieldsOrigen[i*2] = vectorNodos.get(i).getNombre();
		}
		for(int i=0;i<vectorNodos.size();i++){
			fieldsOrigen[i*2+1] = new JTextField();;
		}
		JOptionPane.showConfirmDialog(null,fieldsOrigen,
				titleWindow, JOptionPane.OK_CANCEL_OPTION);

		for(int i=0;i<vectorNodos.size();i++){
			JTextField auxiiar = (JTextField)fieldsOrigen[i*2+1];
			try{
				matIntOrigen[i] = Integer.parseInt(auxiiar.getText());
			}catch (NumberFormatException e){
				matIntOrigen[i] = 0;
			}
		}
		for(int i=0;i<vectorNodos.size();i++){
			System.out.println(matIntOrigen[i]);
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
