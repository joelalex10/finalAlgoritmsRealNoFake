package windows;
import Algoritmos.HungarianC.HungarianAlgorithm;
import Algoritmos.Kramer;
import Database.Asignacion.Dao.AsignacionEnlaceDao;
import Database.Asignacion.Dao.AsignacionGrafDao;
import Database.Asignacion.Dao.AsignacionNodoDao;
import Database.Asignacion.Dao.OfertDemantDao;
import Database.Asignacion.Models.Demanda;
import Database.Asignacion.Models.Oferta;
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
import java.util.List;
import java.util.Vector;

import static java.util.Arrays.stream;

public class VentanaAsignacion {

	JPanel panel_1;

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

	private int[] ofertas;
	private int[] demandas;

	List<Oferta> listaofertas;
	List<Demanda> listaDemandas;

	int matrizCostosKramer[][];
	private int[][] MatrizSolucion;
	private String titleWindow= "";
	private boolean isLoading = false;
	public VentanaAsignacion(int origen, int destino, int option) {
		this.origen = origen;
		this.destino = destino;
		this.option = option;
		listaofertas = new ArrayList<Oferta>();
		listaDemandas = new ArrayList<Demanda>();
		initialize();

	}

	private void initialize() {




		frame = new JFrame(titleWindow);
		panel_1 = new JPanel();
		switch (option){
			case 1:
				titleWindow= "ALGORITMO DE ASIGNACION";
				frame.setTitle(titleWindow);

				break;
			case 2:
				titleWindow= "ALGORITMO DE KRAMER";
				frame.setTitle(titleWindow);
				agregarOfertaDemanda();
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
					listaofertas.clear();
					listaDemandas.clear();
					JOptionPane.showMessageDialog(null, "SE HAN REASIGNADO VALORES");
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

				switch (option){
					case 1:
						saveGrafo();
						break;
					case 2:
						if(ofertas.length == 0 && demandas.length==0){
							JOptionPane.showMessageDialog(null,"NO TIENE REGISTRADAS DISPONIBILIDADES Y DEMANDAS");
						}else{
							saveGrafo();

						}
						break;
				}


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
				listaofertas.clear();
				listaDemandas.clear();
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

				listaofertas = OfertDemantDao.getListOfertas(index);
				listaDemandas = OfertDemantDao.getListDemands(index);

				ofertas = new int[listaofertas.size()];
				demandas = new int[listaDemandas.size()];
				System.out.println("EL INDEX ES: "+index);
				for(int i=0;i<listaofertas.size();i++){
					ofertas[i]=listaofertas.get(i).getDato();
				}

				for(int i=0;i<listaDemandas.size();i++){
					demandas[i]=listaDemandas.get(i).getDato();
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


		JButton btnCargarGrafo = new JButton("REASIGNAR VALORES");


		btnCargarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "SE ELIMINARAN LOS DATOS \nESTA SEGURO DE REASIGNAR VALORES");
				if(option ==0) {
					lienzoAsignacion.g.eliminarGrafo(lienzoAsignacion.vertices);
					lienzoAsignacion.vectorEnlace.clear();
					lienzoAsignacion.vectorNodosDestino.clear();
					lienzoAsignacion.vectorNodosOrigen.clear();
					lienzoAsignacion.contadorInicio = 0;
					lienzoAsignacion.contadorDestino = 0;
					lienzoAsignacion.repaint();
					listaofertas.clear();
					listaDemandas.clear();

					String strOrigen = JOptionPane.showInputDialog("Introducir maximo nodos origen");
					origen = Integer.parseInt(strOrigen);
					String strDestino = JOptionPane.showInputDialog("Introducir maximo nodos destino");
					destino = Integer.parseInt(strDestino);

					JOptionPane.showMessageDialog(null, "EL GRAFO HA SIDO ELIMINADO");
					//System.out.println(lienzo.vertices);
				}
			}
		});
		btnCargarGrafo.setForeground(Color.WHITE);
		btnCargarGrafo.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnCargarGrafo.setBackground(new Color(21, 88, 16));
		btnCargarGrafo.setBounds(502, 52, 160, 30);
		panel_1.add(btnCargarGrafo);



	}

	private void agregarOfertaDemanda() {

		JButton btnNewButton_1_3_1 = new JButton("OFERTA/DEMANDA");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {



				if(ofertas==null){
					ofertas= new int[lienzoAsignacion.vectorNodosOrigen.size()];
				}


				if(demandas==null){
					demandas = new int[lienzoAsignacion.vectorNodosDestino.size()];
				}
				System.out.println("MATRICES");
				System.out.println(ofertas.length);
				System.out.println(demandas.length);

				generarArreglo(lienzoAsignacion.vectorNodosOrigen, "ENTER SUPPLIES",ofertas,listaofertas.size());
				generarArreglo(lienzoAsignacion.vectorNodosDestino, "ENTER DEMANDS",demandas,listaDemandas.size());

				matrizCostosKramer= lienzoAsignacion.matrizCostos;


				for(int i=0;i<ofertas.length;i++){
					System.out.print(ofertas[i]+"\t");
				}

				for(int i=0;i<demandas.length;i++){
					System.out.print(demandas[i]+"\t");
				}

			}
		});
		btnNewButton_1_3_1.setForeground(Color.WHITE);
		btnNewButton_1_3_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_3_1.setBackground(new Color(21, 88, 16));
		btnNewButton_1_3_1.setBounds(343, 52, 160, 30);
		panel_1.add(btnNewButton_1_3_1);
	}

	public void saveGrafo(){
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
			enlace.setTextcolor(Color.white);
		}

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



		System.out.println("control tamanio");
		System.out.println(ofertas.length);
		System.out.println(demandas.length);

		for(int i=0;i<ofertas.length;i++){
			Oferta oferta = new Oferta(ofertas[i],idGrafo);
			OfertDemantDao.insertOferta(oferta);
		}

		for(int i=0;i<demandas.length;i++){
			Demanda demanda = new Demanda(demandas[i],idGrafo);
			OfertDemantDao.insertDemanda(demanda);
		}



		JOptionPane.showMessageDialog(null,"SE HAN REGISTRADO LOS DATOS");

	}

	private void ejecutarKramer() {
		String []menu= {"Maximo","Minimo"};
		int optimo[][] = null;
		String valor = (String)JOptionPane.showInputDialog(null, "seleccionar opcion", "opciones",JOptionPane.DEFAULT_OPTION, null, menu,menu[0]);

		if (valor.equalsIgnoreCase ("Minimo")) {

			Kramer kramer = new Kramer(demandas,ofertas,matrizCostosKramer);
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
			VentanaResultsKramer window=new VentanaResultsKramer(origen, destino, lienzoAsignacion.vectorNodosOrigen, lienzoAsignacion.vectorNodosDestino, ofertas, demandas, matrizCostosKramer,matrix, kramer.getTotalCosts());
			window.setLocationRelativeTo(null);
			window.setVisible(true);
		}
		if (valor.equalsIgnoreCase ("Maximo")) {

			Kramer kramer = new Kramer(demandas,ofertas,matrizCostosKramer);
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
			VentanaResultsKramer window=new VentanaResultsKramer(origen, destino, lienzoAsignacion.vectorNodosOrigen, lienzoAsignacion.vectorNodosDestino, ofertas, demandas, matrizCostosKramer,matrix, kramer.getTotalCosts());
			window.setLocationRelativeTo(null);
			window.setVisible(true);


		}
	}

	private void ejecutarMaximo(double matDouble[][], boolean isChange){

		System.out.println("LA VARIABLE ES: "+isChange);
		for(int i=0;i<matDouble.length;i++){
			for(int j=0;j<matDouble.length;j++){
				System.out.print(matDouble[i][j]+"\t");
			}
			System.out.println("");
		}

		HungarianAlgorithm hungarianAlgorithm1 = new HungarianAlgorithm(matDouble);
		System.out.println("EL MAXIMO ES: "+hungarianAlgorithm1.hgAlgorithm("max"));
		int[][] MaxMatrixPositions1=hungarianAlgorithm1.hgAlgorithmAssignments("max");

		//hungarianAlgorithm1.restas("max");
		VentanaResultsAsignacion window=new VentanaResultsAsignacion(origen, destino, lienzoAsignacion.vectorNodosOrigen,
				lienzoAsignacion.vectorNodosDestino, matDouble, hungarianAlgorithm1.restas("max", isChange),hungarianAlgorithm1.matString("max"),hungarianAlgorithm1.hgAlgorithm("max"), lienzoAsignacion.vectorEnlace);
		lienzoAsignacion.repaint();
		window.setLocationRelativeTo(null);
		window.setVisible(true);



	}

	private void ejecutarMinimo(double[][] matDouble, boolean isChange) {

		System.out.println("LA VARIABLE ES: "+isChange);
		for(int i=0;i<matDouble.length;i++){
			for(int j=0;j<matDouble.length;j++){
				System.out.print(matDouble[i][j]+"\t");
			}
			System.out.println("");
		}

		HungarianAlgorithm hungarianAlgorithm1 = new HungarianAlgorithm(matDouble);
		System.out.println("EL MINIMO ES: "+hungarianAlgorithm1.hgAlgorithm("min"));
		int[][] MaxMatrixPositions1=hungarianAlgorithm1.hgAlgorithmAssignments("min");
		//hungarianAlgorithm1.restas("min");

		VentanaResultsAsignacion window=new VentanaResultsAsignacion(origen, destino, lienzoAsignacion.vectorNodosOrigen,
				lienzoAsignacion.vectorNodosDestino, matDouble, hungarianAlgorithm1.restas("min",isChange),hungarianAlgorithm1.matString("min"),hungarianAlgorithm1.hgAlgorithm("min"), lienzoAsignacion.vectorEnlace);
		lienzoAsignacion.repaint();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

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
		boolean isChange = false;

		if(sizeOrigen != sizeDestino){
			isChange = true;
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

			for(Enlace enlace: lienzoAsignacion.vectorEnlace){
				enlace.setTextcolor(Color.white);

			}
			ejecutarMaximo(matDouble, isChange);
		}
		if (valor.equalsIgnoreCase ("Minimo")) {
			for(Enlace enlace: lienzoAsignacion.vectorEnlace){
				enlace.setTextcolor(Color.white);

			}
			ejecutarMinimo(matDouble, isChange);
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



	private void generarArreglo(Vector<Nodo> vectorNodos, String titleWindow, int[]matInt, int option) {
		Object[] fieldsOrigen = new Object[vectorNodos.size()*2];



		if(option == 0){
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
					matInt[i] = Integer.parseInt(auxiiar.getText());
				}catch (NumberFormatException e){
					matInt[i] = 0;
				}
			}
			for(int i=0;i<vectorNodos.size();i++){
				System.out.println(matInt[i]);
			}
		}else{

			for(int i=0;i<vectorNodos.size();i++){
				fieldsOrigen[i*2] = vectorNodos.get(i).getNombre();
			}
			for(int i=0;i<vectorNodos.size();i++){
				System.out.println("es: "+i+": "+matInt[i]);
				fieldsOrigen[i*2+1] = new JTextField(matInt[i]+"");

			}
			JOptionPane.showConfirmDialog(null,fieldsOrigen,
					titleWindow, JOptionPane.OK_CANCEL_OPTION);

			for(int i=0;i<vectorNodos.size();i++){
				JTextField auxiiar = (JTextField)fieldsOrigen[i*2+1];
				try{
					matInt[i] = Integer.parseInt(auxiiar.getText());
				}catch (NumberFormatException e){
					matInt[i] = 0;
				}
			}
			for(int i=0;i<vectorNodos.size();i++){
				System.out.println(matInt[i]);
			}
		}


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
