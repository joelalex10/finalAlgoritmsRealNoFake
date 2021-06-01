package windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DibujarCurvaCuadratica extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawLine(10, 100, 290, 100);
		g.drawLine(150, 10, 150, 190);
		
		g.setColor(Color.red);
		Path2D ruta = new Path2D.Double();
		
		ruta.moveTo(50, 150);
		ruta.quadTo(250, 100, 250, 100);
		
		Graphics2D g2 =(Graphics2D)g;
		g2.draw(ruta);
		
		
	}
	
	
	public static void main(String []args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		JFrame frame = new JFrame("Dibujar la curva");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setBackground(Color.orange);
		frame.setSize(320,240);
		DibujarCurvaCuadratica panel = new DibujarCurvaCuadratica();
		frame.add(panel);
		frame.setVisible(true);
		
	}

}
