package windows;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LineArrowTest extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new LineArrowTest().setVisible(true));
    }

    public LineArrowTest() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        JPanel contentPane = new JPanel(new BorderLayout(0, 0));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        setContentPane(contentPane);

        JPanel drawPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.PINK);
                g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
                Insets insets = getInsets();
                LineArrow line1 = new LineArrow(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, this.getHeight(), Color.BLACK, 3);
                line1.draw(g);
                LineArrow line2 = new LineArrow(20, 40, 60, 80, Color.RED, 3);
                line2.draw(g);
                LineArrow line3 = new LineArrow(0, 0, this.getWidth(), this.getHeight(), Color.GREEN, 3);
                line3.draw(g);
                LineArrow line4 = new LineArrow(this.getWidth(), 0, 0, this.getHeight(), Color.MAGENTA, 3);
                line4.draw(g);
                LineArrow line5 = new LineArrow((insets.right + insets.left) / 2, (insets.top + insets.bottom) / 2, 140, 170, Color.BLUE, 3);
                line5.draw(g);
                
                LineArrow line6 = new LineArrow(this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2, Color.CYAN, 3);
                line6.draw(g);
                
                CircleArrow circle = new CircleArrow (20,50,30,Color.black,12);
                circle.draw(g);
            }
        };
        contentPane.add(drawPanel, BorderLayout.CENTER);
        pack();
    }

    private static final Polygon ARROW_HEAD = new Polygon();

    static {
        ARROW_HEAD.addPoint(0, 0);
        ARROW_HEAD.addPoint(-8, -13);
        ARROW_HEAD.addPoint(8, -13);
    }
    public static class CircleArrow{
    	
    	private int x;
		private int y;
		private int d;
		private Color color;
		private float thickness;
		
    	public CircleArrow(int x, int y, int d, Color color, float thickness) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.color = color;
			this.thickness = thickness;
		}

		public void draw(Graphics g) {
    		Graphics2D g2 = (Graphics2D) g;
    		
			g2.setColor(color);
			
			g2.drawOval(this.x -d/2, this.y - d/2, d*3, d);
			double angle = Math.atan2(x - y, y - x);
			g2.setStroke(new BasicStroke(this.thickness));
			AffineTransform tx1 = g2.getTransform();

            // Crea una copia de AffineTransform.
            AffineTransform tx2 = (AffineTransform) tx1.clone();

            // Traducción y rota el nuevo AffineTransform.
            tx2.translate(x, y);
            tx2.rotate(angle - Math.PI / 2);

            // Dibuja la punta con el AffineTransform transaccionado y girado.
            g2.setColor(Color.orange);
            g2.setTransform(tx2);
            g2.fill(ARROW_HEAD);
            
            // Restaura el AffineTransform original.
            g2.setTransform(tx1);
			
    	}
    }
    

    public static class LineArrow {

        private final int x;
        private final int y;
        private final int endX;
        private final int endY;
        private final Color color;
        private final int thickness;

        public LineArrow(int x, int y, int x2, int y2, Color color, int thickness) {
            super();
            this.x = x;
            this.y = y;
            this.endX = x2;
            this.endY = y2;
            this.color = color;
            this.thickness = thickness;
        }

        public void draw(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            // Calcula o ângulo da seta.
            double angle = Math.atan2(endY - y, endX - x);

            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));

            // Desenha a linha. Corta 10 pixels na ponta para a ponta não ficar grossa.
            g2.drawLine(x, y, (int) (endX - 10 * Math.cos(angle)), (int) (endY - 10 * Math.sin(angle)));

            // Obtém o AffineTransform original.
            AffineTransform tx1 = g2.getTransform();

            // Cria uma cópia do AffineTransform.
            AffineTransform tx2 = (AffineTransform) tx1.clone();

            // Translada e rotaciona o novo AffineTransform.
            tx2.translate(endX, endY);
            tx2.rotate(angle - Math.PI / 2);

            // Desenha a ponta com o AffineTransform transladado e rotacionado.
            g2.setColor(Color.orange);
            g2.setTransform(tx2);
            g2.fill(ARROW_HEAD);

            // Restaura o AffineTransform original.
            g2.setTransform(tx1);
        }
    }
}