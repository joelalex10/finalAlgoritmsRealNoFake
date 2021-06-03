package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Algoritmos.Johnson;
import Database.EnlaceBDD;
import Database.GrafoBDD;
import Database.NodoBDD;
import grafos.Enlace;
import grafos.Nodo;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	public JFrame frame;
	Lienzo lienzo;
	Object [][]mat;
	String []lista;
	int index = 0;
	boolean isAsignacion=false;
	
	public String titleWindow= "GENERACION DE GRAFOS";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame(titleWindow);
		frame.setBounds(100, 100, 890, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(100,100, 850, 500);
		lienzo = new Lienzo();
		lienzo.setBounds(0, 0, 864, 500);
		frame.getContentPane().add(lienzo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 510, 864, 87);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("VER MATRIZ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("");
				
				mat = lienzo.retornoMatriz();
				ArrayList<String>variables = lienzo.getVertices();
				lista = new String[variables.size()];
				
				for(int i=0;i<variables.size();i++) {
					lista[i] = variables.get(i);
				}
				
				VentanaMatriz window=new VentanaMatriz(mat, lista);
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(184, 11, 160, 30);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		//btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnNewButton.setBackground(new Color(21, 88, 16));
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BORRAR NODO");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(lienzo.p1 !=null) {
					int pos = lienzo.indexNodo;
					Nodo nodoSeleccionado = lienzo.vectorNodos.get(pos);
					//System.out.println(pos);
					int option = JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE BORRAR EL NODO");
					
					if(option==0) {
						//System.out.println("NODO BORRADO");
						lienzo.vectorNodos.remove(pos);
						lienzo.vertices.remove(pos);
						lienzo.p1 = null;
						ArrayList<Integer>indexListEnlace = new ArrayList<Integer>();
						
						int cont = 0;
						for(int i=0;i<lienzo.vectorEnlace.size();i++) {
							
							String nodoNombreFin = lienzo.vectorEnlace.get(i).getNodoFin().getNombre();
							String nodoNombreInicio= lienzo.vectorEnlace.get(i).getNodoInicio().getNombre();
							String nombre = nodoSeleccionado.getNombre();
							
							if(nodoNombreInicio.equals(nombre)) {
								indexListEnlace.add(i);
								cont++;
								
							}
							
							if(nodoNombreFin.equals(nombre)) {
								if(nodoNombreFin.equals(nodoNombreInicio) == false) {
									indexListEnlace.add(i);
									cont++;
								}
							}
							
						}
						//System.out.println("CONTADOR: "+cont);

						//System.out.println("EL TAMAÑO ES: "+indexListEnlace.size());
						for(int i=0;i<indexListEnlace.size();i++) {
							int posEnlace = indexListEnlace.get(i);
							lienzo.vectorEnlace.remove(posEnlace-i);
						}
						
						lienzo.g.eliminarVertice(nodoSeleccionado.getNombre(), lienzo.vertices,lienzo.aux);
						lienzo.repaint();
						
						
					}else {
						//System.out.println("NODO SALVADO");
						nodoSeleccionado.setColor(new Color(9,11,48));
						lienzo.repaint();
						
						lienzo.aux.remove(nodoSeleccionado.getNombre());
						
						lienzo.p1 = null;
						
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN NODO");
				}
				
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1.setBackground(new Color(21, 88, 16));
		btnNewButton_1.setBounds(25, 11, 160, 30);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("EDITAR NODO");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lienzo.p1 != null) {
					int pos = lienzo.indexNodo;
					Nodo nodoSeleccionado = lienzo.vectorNodos.get(pos);
					String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del nodo");
					String auxNombre = nodoSeleccionado.getNombre();
					nodoSeleccionado.setNombre(nombre);
					nodoSeleccionado.setColor(new Color(9,11,48));
					for(Enlace enlace:lienzo.vectorEnlace) {
						if(enlace.getNodoInicio().getNombre().equals(auxNombre)) {
							enlace.getNodoInicio().setNombre(nombre);
						}
						if(enlace.getNodoFin().getNombre().equals(auxNombre)) {
							enlace.getNodoFin().setNombre(nombre);
						}
					}
					
					lienzo.g.editarVertice(auxNombre, nombre, lienzo.aux, lienzo.vertices);
					lienzo.repaint();
					lienzo.p1 =null;
					
				}else {
					JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN NODO");
				}
			}
		});
		btnNewButton_1_2.setForeground(Color.WHITE);
		btnNewButton_1_2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_2.setBackground(new Color(21, 88, 16));
		btnNewButton_1_2.setBounds(343, 11, 160, 30);
		panel_1.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_1 = new JButton("EDITAR ENLACE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valorA = JOptionPane.showInputDialog("Ingrese el nombre del Nodo de Inicio del enlace");
				String valorB = JOptionPane.showInputDialog("Ingrese el nombre del Nodo de Finalizacion del enlace");
				boolean isChange = false;
				
				for(Enlace enlace: lienzo.vectorEnlace) {
					if(valorA.equals(enlace.getNodoInicio().getNombre()) && valorB.equals(enlace.getNodoFin().getNombre())) {
						isChange=true;
					}
				}
				if(isChange) {
					try {
						String cadNewValue = JOptionPane.showInputDialog("Ingrese el nuevo valor del atributo");
						int newValue = Integer.parseInt(cadNewValue);
						
						for(Enlace enlace: lienzo.vectorEnlace) {
							
							if(valorA.equals(enlace.getNodoInicio().getNombre()) && valorB.equals(enlace.getNodoFin().getNombre())) {
								enlace.setAtributo(newValue);
							}
						}
						lienzo.g.editarEnlace(valorA, valorB, newValue, lienzo.aux, lienzo.vertices);
						lienzo.repaint();
						
					}catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "DEBE INGRESAR VALORES NUMERICOS UNICAMENTE");
						lienzo.repaint();
					}
					
					
					
					
				}else {
					JOptionPane.showMessageDialog(null, "NO ES POSIBLE REALIZAR EL CAMBIOS");
				}
				
			}
		});
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_1.setBackground(new Color(21, 88, 16));
		btnNewButton_1_1.setBounds(502, 11, 160, 30);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("BORRAR ENLACE");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String valorA = JOptionPane.showInputDialog("Ingrese el nombre del Nodo de Inicio del enlace");
				String valorB = JOptionPane.showInputDialog("Ingrese el nombre del Nodo de Finalizacion del enlace");
				boolean isChange = false;
				
				for(Enlace enlace: lienzo.vectorEnlace) {
					if(valorA.equals(enlace.getNodoInicio().getNombre()) && valorB.equals(enlace.getNodoFin().getNombre())) {
						isChange=true;
					}
				}
				if(isChange) {
					
					for(int i=0;i<lienzo.vectorEnlace.size();i++) {
						if(valorA.equals(lienzo.vectorEnlace.get(i).getNodoInicio().getNombre()) &&
								valorB.equals(lienzo.vectorEnlace.get(i).getNodoFin().getNombre())) {
							lienzo.vectorEnlace.remove(i);
						}
					}
					lienzo.g.elimiarEnlace(valorA, valorB);
					lienzo.repaint();
					JOptionPane.showMessageDialog(null, "Enlace Borrado");
					
					
				}else {
					JOptionPane.showMessageDialog(null, "NO ES POSIBLE REALIZAR EL CAMBIOS");
				}
				
			}
		});
		btnNewButton_1_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_1_1.setBackground(new Color(21, 88, 16));
		btnNewButton_1_1_1.setBounds(663, 11, 160, 30);
		panel_1.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_3 = new JButton("LIMPIAR");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "SE ELIMINARAN LOS DATOS \nESTA SEGURO DE LIMPIAR EL LIENZO");
				//System.out.println(lienzo.vertices);
				if(option ==0) {
					lienzo.g.eliminarGrafo(lienzo.vertices);
					lienzo.vectorEnlace.clear();
					lienzo.vectorNodos.clear();
					lienzo.repaint();
					JOptionPane.showMessageDialog(null, "EL GRAFO HA SIDO ELIMINADO");
					//System.out.println(lienzo.vertices);
				}
				
			}
		});
		btnNewButton_1_3.setForeground(Color.WHITE);
		btnNewButton_1_3.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_3.setBackground(new Color(21, 88, 16));
		btnNewButton_1_3.setBounds(184, 52, 160, 30);
		panel_1.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_3_1 = new JButton("GUARDAR GRAFO");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newNombreNodo = JOptionPane.showInputDialog("Ingrese un nombre a guardar");
				
				
				GrafoBDD grafo = new GrafoBDD();
				grafo.setNombre(newNombreNodo);
				int iddGrafo = grafo.insertGrafo();
				System.out.println("El ultimo Id es: "+iddGrafo);
				
				for(Nodo nodo: lienzo.vectorNodos) {
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
				System.out.println("");

				for(Enlace enlace: lienzo.vectorEnlace) {
					EnlaceBDD enlaceBDD = new EnlaceBDD();
					enlaceBDD.setX1(enlace.getX1());
					enlaceBDD.setY1(enlace.getY1());
					enlaceBDD.setX2(enlace.getX2());
					enlaceBDD.setY2(enlace.getY2());
					enlaceBDD.setAtributo(enlace.getAtributo());
					enlaceBDD.setThickness(enlace.getThickness());
					enlaceBDD.setTextDirection(enlace.getTextDirection());
					enlaceBDD.setNroActividadNodoInicio(enlace.getNroActividadNodoInicio());
					enlaceBDD.setNroActividadNodoFin(enlace.getNroActividadNodoFin());
					
					for(int i=0;i<lista.size();i++) {
						if(enlace.getNodoInicio().getNombre().equals(lista.get(i).getNombre())) {
							//System.out.println(enlace.getAtributo()+"\t"+lista.get(i).getIdNodo());
							enlaceBDD.setNodoInicio(lista.get(i).getIdNodo());
						}
						if(enlace.getNodoFin().getNombre().equals(lista.get(i).getNombre())) {
							//System.out.println(enlace.getAtributo()+"\t"+lista.get(i).getIdNodo());
							enlaceBDD.setNodoFin(lista.get(i).getIdNodo());
						}	
					}
					enlaceBDD.setCiclo(enlace.isCiclo());
					enlaceBDD.setColorRed(enlace.getTextcolor().getRed());
					enlaceBDD.setColorGreen(enlace.getTextcolor().getGreen());
					enlaceBDD.setColorBlue(enlace.getTextcolor().getBlue());
					enlaceBDD.setGrafoId(iddGrafo);
					enlaceBDD.insertEnlace();
					
					//System.out.println(enlaceBDD);
					
					
					
				}
				
			}
		});
		btnNewButton_1_3_1.setForeground(Color.WHITE);
		btnNewButton_1_3_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_3_1.setBackground(new Color(21, 88, 16));
		btnNewButton_1_3_1.setBounds(343, 52, 160, 30);
		panel_1.add(btnNewButton_1_3_1);
		
		JButton btnNewButton_1_3_2 = new JButton("CARGAR GRAFO");
		
		
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<GrafoBDD> lista = GrafoBDD.listGrafo();
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
				
				
				lienzo.vectorEnlace.clear();
				lienzo.vectorNodos.clear();
				lienzo.g.eliminarGrafo(lienzo.vertices);
				lienzo.vertices.clear();
				
				NodoBDD nodoBDD = new NodoBDD();
				nodoBDD.setIdGrafo(index);
				ArrayList<NodoBDD> listnodoBDD = nodoBDD.getNodoByGrafoId();
				//System.out.println(listnodoBDD);
				
				EnlaceBDD enlaceBDD = new EnlaceBDD();
				enlaceBDD.setGrafoId(index);
				ArrayList<EnlaceBDD> listEnlaceBDD = enlaceBDD.getEnlaceByGrafoId();
				//System.out.println(listEnlaceBDD);
				
				for(NodoBDD nodoGraphic: listnodoBDD) {
					
					lienzo.vertices.add(nodoGraphic.getNombre());
					lienzo.aux = new HashMap<String,Integer>();
					lienzo.g.crearVertice(nodoGraphic.getNombre(), lienzo.aux);
					
					Nodo nodo = new Nodo();
					
					nodo.setX(nodoGraphic.getX());
					nodo.setY(nodoGraphic.getY());
					nodo.setNombre(nodoGraphic.getNombre());
					nodo.setNroActividad(nodoGraphic.getNroActividad());
					nodo.setColor(new Color(nodoGraphic.getColorRed(),
									nodoGraphic.getColorGreen(),
									nodoGraphic.getColorBlue()));
					
					lienzo.vectorNodos.add(nodo);
					
				}
				
				//System.out.println(lienzo.vertices);
				
				for(EnlaceBDD enlaceBdd: listEnlaceBDD) {
					
					int idNodoInicio = enlaceBdd.getNodoInicio();
					int idNodoFin = enlaceBdd.getNodoFin();
					
					NodoBDD nodoInicioBDDAux = new NodoBDD();
					nodoInicioBDDAux.setIdNodo(enlaceBdd.getNodoInicio());
					NodoBDD nodoInicioBDD = nodoInicioBDDAux.getNodoById();
					
					NodoBDD nodoFinBDDAux = new NodoBDD();
					nodoFinBDDAux.setIdNodo(enlaceBdd.getNodoFin());
					NodoBDD nodoFinBDD = nodoFinBDDAux.getNodoById();
							
					Nodo nodoAuxInicio = null;
					Nodo nodoAuxFin = null;
					int indexNodoAuxInicio = 0;
					int indexNodoAuxFin = 0;
					for(int i=0;i<lienzo.vectorNodos.size();i++) {
						if(lienzo.vectorNodos.get(i).getNombre().equals(nodoInicioBDD.getNombre())) {
							nodoAuxInicio= lienzo.vectorNodos.get(i);
							indexNodoAuxInicio = i;
						}
					}
					
					for(int i=0;i<lienzo.vectorNodos.size();i++) {
						if(lienzo.vectorNodos.get(i).getNombre().equals(nodoFinBDD.getNombre())) {
							nodoAuxFin = lienzo.vectorNodos.get(i);
							indexNodoAuxFin = i;
						}
						
					}
					Enlace enlace = new Enlace();
					enlace.setX1(enlaceBdd.getX1());
					enlace.setY1(enlaceBdd.getY1());
					enlace.setX2(enlaceBdd.getX2());
					enlace.setY2(enlaceBdd.getY2());
					enlace.setAtributo(enlaceBdd.getAtributo());
					enlace.setThickness(enlaceBdd.getThickness());
					enlace.setTextcolor(new Color(enlaceBdd.getColorRed(), enlaceBdd.getColorGreen(),
							enlaceBdd.getColorBlue()));
					enlace.setTextDirection(enlaceBdd.getTextDirection());
					enlace.setNodoInicio(nodoAuxInicio);
					enlace.setNodoFin(nodoAuxFin);
					enlace.setCiclo(enlaceBdd.isCiclo());
					System.out.println("LA VARIABLE ES: "+enlaceBDD.getNroActividadNodoInicio());
					enlace.setNroActividadNodoInicio(enlaceBdd.getNroActividadNodoInicio());
					enlace.setNroActividadNodoFin(enlaceBdd.getNroActividadNodoFin());
					lienzo.vectorEnlace.add(enlace);
					
					lienzo.aux = lienzo.g.getVertice(nodoAuxInicio.getNombre());
					lienzo.aux.put(nodoAuxFin.getNombre(),enlaceBdd.getAtributo());
					
				}
				lienzo.repaint();
				
			}
		});
		btnNewButton_1_3_2.setForeground(Color.WHITE);
		btnNewButton_1_3_2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_3_2.setBackground(new Color(21, 88, 16));
		btnNewButton_1_3_2.setBounds(502, 52, 160, 30);
		panel_1.add(btnNewButton_1_3_2);
		
		JButton btnNewButton_1_3_2_1 = new JButton("JOHNSON");
		btnNewButton_1_3_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for(Enlace enlace:lienzo.vectorEnlace){
					enlace.setJohnson(true);
				}

				//System.out.println(lienzo.vectorNodos);
				//System.out.println(lienzo.vectorEnlace);
				Johnson johnson = new Johnson();
				johnson.setEnlaces(lienzo.vectorEnlace);
				johnson.setNodos(lienzo.vectorNodos);
				johnson.eject();
				//System.out.println(lienzo.vectorNodos);
				//System.out.println(lienzo.vectorEnlace);
				JOptionPane.showMessageDialog(null,"ALGORITMO DE JOHNSON EJECUTANDOSE");


			}
		});
		btnNewButton_1_3_2_1.setForeground(Color.WHITE);
		btnNewButton_1_3_2_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnNewButton_1_3_2_1.setBackground(new Color(21, 88, 16));
		btnNewButton_1_3_2_1.setBounds(663, 52, 160, 30);
		panel_1.add(btnNewButton_1_3_2_1);


		JButton btnAsignacion = new JButton("ASIGNACION");
		btnAsignacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String strOrigen = JOptionPane.showInputDialog("Introducir maximo nodos origen / DISPONIBILIDAD");
				int origen = Integer.parseInt(strOrigen);
				String strDestino = JOptionPane.showInputDialog("Introducir maximo nodos destino / DEMANDA");
				int destino = Integer.parseInt(strDestino);

				VentanaAsignacion window=new VentanaAsignacion(origen, destino);
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
				frame.dispose();
			}
		});

		btnAsignacion.setForeground(Color.WHITE);
		btnAsignacion.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnAsignacion.setBackground(new Color(21, 88, 16));
		btnAsignacion.setBounds(25, 52, 160, 30);
		panel_1.add(btnAsignacion);





		
		
		
		
		
		
	}
	/*****************
	public HashMap<String, ArrayList<Integer>> saveListAtributos(){
		HashMap<String, ArrayList<Integer>> listaAtributos = new HashMap();
		int sumMax = 0;
		for(Nodo nodo: lienzo.vectorNodos) {
			//int sizeEnlaceEntrante = nodo.getEnlaceEntrantes().size();
			//int sizeEnlaceSaliente = nodo.getEnlaceSalientes().size();
			int sumVal = 0;
			ArrayList<Integer>lista = new ArrayList<>();
			//for(int i=0;i<sizeEnlaceEntrante;i++) {
			//	int atributo = nodo.getEnlaceEntrantes().get(i).getAtributo();
			//	lista.add(atributo );
			//}
			//listaAtributos.put(nodo.getNombre(),lista);
		}
		return listaAtributos;
		
	}*/
	
}
