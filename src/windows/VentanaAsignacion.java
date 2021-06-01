package windows;
import Database.EnlaceBDD;
import Database.GrafoBDD;
import Database.NodoBDD;
import grafos.Enlace;
import grafos.Nodo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

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

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 * @param origen
	 * @param destino
	 */
	public VentanaAsignacion(int origen, int destino) {
		this.origen = origen;
		this.destino = destino;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
					/*ds	*/
					AlgoritmoAsignacion cc = new AlgoritmoAsignacion (lienzoAsignacion.matrizCostos, true,lienzoAsignacion.vectorNodosOrigen,
							lienzoAsignacion.vectorNodosDestino, lienzoAsignacion.vectorEnlace);
					cc.efectuarAlgoritmo();
					optimo = cc.getMatrizOptimo();
				}
				if (valor.equalsIgnoreCase ("Minimo")) {
					AlgoritmoAsignacion cc = new AlgoritmoAsignacion (lienzoAsignacion.matrizCostos, false,lienzoAsignacion.vectorNodosOrigen,
							lienzoAsignacion.vectorNodosDestino,lienzoAsignacion.vectorEnlace);
					cc.efectuarAlgoritmo();
					optimo = cc.getMatrizOptimo();
				}
				JFrame ventanaMatriz = new JFrame("Matriz de Adyacentes");
				ventanaMatriz.setSize(500, 300);
				JTextArea matriz  = new JTextArea(lienzoAsignacion.imprimirMatrizAdyString(optimo));
				ventanaMatriz.add(new JScrollPane(matriz), BorderLayout.CENTER);
				ventanaMatriz.setVisible(true);
				ventanaMatriz.setLocationRelativeTo(null);

				/***System.out.println("DATOS VECTOREES");
				System.out.println("");
				for(Nodo nodo: lienzoAsignacion.vectorNodosOrigen){
					System.out.println(nodo);
				}
				System.out.println("");
				for(Nodo nodo: lienzoAsignacion.vectorNodosDestino){
					System.out.println(nodo);
				}
				System.out.println("");
				for(Enlace enlace: lienzoAsignacion.vectorEnlace){
					System.out.println(enlace);
				}

				System.out.println("");
				for(int i=0;i<origen;i++){
					System.out.println("");
					for(int j=0;j<destino;j++){
						System.out.print(lienzoAsignacion.matrizCostos[i][j]+"\t");
					}
				}*/

			}
		});
		btnAsignacion.setForeground(Color.WHITE);
		btnAsignacion.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		btnAsignacion.setBackground(new Color(21, 88, 16));
		btnAsignacion.setBounds(25, 11, 160, 30);
		panel_1.add(btnAsignacion);
		
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
}
