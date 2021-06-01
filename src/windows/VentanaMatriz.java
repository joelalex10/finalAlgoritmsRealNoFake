package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMatriz {

	public JFrame frame;
	private JTable table;
	private Object[][]matriz;
	private String[]titulos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMatriz window = new VentanaMatriz();
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
	public VentanaMatriz(Object[][]matriz, String[]titulos) {
		this.matriz = matriz;
		this.titulos = titulos;
		initialize();
	}
	public VentanaMatriz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 642, 592);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 626, 553);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("MATRIZ DE COEFICIENTES");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 21, 222, 35);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(101, 67, 434, 233);
		panel.add(scrollPane);
		table = new JTable();
		
		VentanaPrincipal principal = new VentanaPrincipal();
		
		
		Object[][]matComp = new Object[matriz.length+1][matriz.length+2];
		String []titles = new String[titulos.length + 2];
		
		//*********************************************************************************************************************
		getMatrizCompleta(matComp,titles);
		//System.out.println("TITULOS");
		for(int i=0;i<(matriz.length+1);i++) {
			//System.out.println("");
			for(int j=0;j<(matriz.length+2);j++) {
				//System.out.print(matComp[i][j]+"\t");	
			}
		}
		//*********************************************************************************************************************
		
		
		
		
		
		
		table.setModel(new DefaultTableModel(
				matComp,
				titles
	        ));
		table.setFocusable(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRowHeight(25);
		//table.setSelectionBackground(new Color(232, 57, 95));
		table.getTableHeader().setReorderingAllowed(false);
		

		JTableHeader header = table.getTableHeader(); 
	    header.setBackground(new Color(9,11,48)); 
	    header.setForeground(Color.white); 
	    header.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		for(int i=0;i<matComp.length+1;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
			
		
		}
		
		scrollPane.setViewportView(table);
		/*
		JLabel lblNewLabel_1 = new JLabel("SUMA DE COLUMNAS");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(203, 211, 222, 35);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(101, 257, 434, 63);
		panel.add(scrollPane_1);
		
		JTable table_sumcolumnas = new JTable();
		
		Object[][]sumColumnas = new Object[1][matriz.length];
		 for(int i=0;i<matriz.length;i++) {
			 int sum=0;
			 for(int j=0;j<matriz.length;j++) {
				 sum=sum+(int)matriz[j][i];
			 }
			 sumColumnas[0][i]=sum;
		 }
		
		
		table_sumcolumnas.setModel(new DefaultTableModel(
				sumColumnas,
				titulos
				));
		table_sumcolumnas.setFocusable(false);
		table_sumcolumnas.setIntercellSpacing(new Dimension(0, 0));
		table_sumcolumnas.setRowHeight(25);
		//table_sumcolumnas.setSelectionBackground(new Color(232, 57, 95));
		table_sumcolumnas.getTableHeader().setReorderingAllowed(false);
		
		JTableHeader header_1 = table_sumcolumnas.getTableHeader(); 
		header_1.setBackground(new Color(9,11,48)); 
		header_1.setForeground(Color.white); 
		header_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		DefaultTableCellRenderer modelocentrar_1 = new DefaultTableCellRenderer();
		modelocentrar_1.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<matriz.length;i++) {
			table_sumcolumnas.getColumnModel().getColumn(i).setCellRenderer(modelocentrar_1);
		}
		
		 scrollPane_1.setViewportView(table_sumcolumnas);
		 
		 JScrollPane scrollPane_2 = new JScrollPane();
		 scrollPane_2.setBounds(101, 380, 434, 63);
		 panel.add(scrollPane_2);
		 JTable table_2 = new JTable();
		 System.out.println("");
		 Object[][]sumFilas = new Object[1][matriz.length];
		 for(int i=0;i<matriz.length;i++) {
			 int sum=0;
			 for(int j=0;j<matriz.length;j++) {
				 sum=sum+(int)matriz[i][j];
			 }
			 sumFilas[0][i]=sum;
		 }
		 
		 table_2.setModel(new DefaultTableModel(
				sumFilas,
	            titulos
	        ));
		 table_2.setFocusable(false);
		 table_2.setIntercellSpacing(new Dimension(0, 0));
		 table_2.setRowHeight(25);
		 //table_2.setSelectionBackground(new Color(232, 57, 95));
		 table_2.getTableHeader().setReorderingAllowed(false);
		

		 JTableHeader header_2 = table_2.getTableHeader(); 
		 header_2.setBackground(new Color(9,11,48)); 
		 header_2.setForeground(Color.white); 
		 header_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		 DefaultTableCellRenderer modelocentrar_2 = new DefaultTableCellRenderer();
		 modelocentrar_2.setHorizontalAlignment(SwingConstants.CENTER);
		 for(int i=0;i<matriz.length;i++) {
			 table_2.getColumnModel().getColumn(i).setCellRenderer(modelocentrar_2);
		 }
		
		 scrollPane_2.setViewportView(table_2);
		 
		 JLabel lblNewLabel_1_1 = new JLabel("SUMA DE FILAS");
		 lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		 lblNewLabel_1_1.setBounds(203, 331, 222, 35);
		 panel.add(lblNewLabel_1_1);
		 */
		 JButton btnNewButton = new JButton("ATRAS");
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		VentanaPrincipal principal = new VentanaPrincipal();
		 		frame.dispose();
		 	}
		 });
		 btnNewButton.setBounds(261, 492, 89, 23);
		 btnNewButton.setForeground(Color.WHITE);
		 btnNewButton.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 13));
		 btnNewButton.setBackground(new Color(21, 88, 16));
		 panel.add(btnNewButton);
		 
	}
	
	private void getMatrizCompleta(Object[][] matComp, String[]titles){
		titles[0] = "*";
		titles[titles.length-1] =  "SUM";
		for(int i=0;i<titulos.length;i++) {
			titles[i+1] = titulos[i];
		}
		
		for(int i=0;i<titulos.length;i++) {
			matComp[i][0] = titulos[i];
		}

		matComp[matriz.length][0] = "SUM";
		matComp[matriz.length][matriz.length+1] = "*";
		
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz.length;j++) {
				matComp[i][j+1] = matriz[i][j];
			}
		}
		
		int[]sumColumna = new int[matriz.length];
		for(int i=0;i<matriz.length;i++) {
			int sum=0;
			for(int j=0;j<matriz.length;j++) {
				sum=sum+(int)matriz[j][i];		
			}
			sumColumna[i]=sum;
		}
		
		for(int i=0;i<matriz.length;i++) {
			matComp[matriz.length][i+1] = sumColumna[i];
		}
		
		int[]sumFila = new int[matriz.length];
		for(int i=0;i<matriz.length;i++) {
			int sum=0;
			for(int j=0;j<matriz.length;j++) {
				sum=sum+(int)matriz[i][j];	
			}
			sumFila[i]=sum;	
		}
		for(int i=0;i<matriz.length;i++) {
			matComp[i][matriz.length+1] = sumFila[i];	
		}
	}
}
