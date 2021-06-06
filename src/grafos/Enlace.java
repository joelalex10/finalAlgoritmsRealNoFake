package grafos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Enlace {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int atributo;
	private Color textcolor;
	private int textDirection;
	private Nodo nodoInicio;
	private Nodo nodoFin;
	private int nroActividadNodoInicio;
	private int nroActividadNodoFin;
	
	private int thickness;
	private boolean ciclo;
	private int holgura;
	private int idGrafo;
	private int idNodoInicio;
	private int idNodoFin;


	private boolean isJohnson=false;

	/***public Enlace(int x1, int y1, int x2, int y2, int atributo,int thickness, 
			Color textcolor, int textDirection, Nodo nodoInicio, Nodo nodoFin, boolean ciclo) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.atributo = atributo;
		this.thickness = thickness;
		this.textcolor = textcolor;
		this.textDirection = textDirection;
		this.nodoInicio = nodoInicio;
		this.nodoFin = nodoFin;
		this.ciclo = ciclo;
	}*/
	public Enlace() {
		// TODO Auto-generated constructor stub
	}
	private static final Polygon ARROW_HEAD = new Polygon();

    static {
    	ARROW_HEAD.addPoint(0, 0);
        ARROW_HEAD.addPoint(-8, -13);
        ARROW_HEAD.addPoint(8, -13);
    }
	
	public void pintar(Graphics g1) {
		Graphics2D g2 = (Graphics2D) g1;
		double angle = Math.atan2(this.y2 - this.y1, this.x2 - this.x1);
		g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(this.thickness));
        
        //g2.drawArc(this.x1, this.y1, this.x2, this.y2, 0, 0);
        
        
        if(this.isCiclo()==false) {
        	g2.drawLine(this.x1, this.y1, (int) (this.x2 - 10 * Math.cos(angle)), (int) (this.y2 - 10 * Math.sin(angle)));
            AffineTransform tx1 = g2.getTransform();
            AffineTransform tx2 = (AffineTransform) tx1.clone();
            tx2.translate(this.x2, this.y2);
            tx2.rotate(angle - Math.PI / 2);
            g2.setColor(this.textcolor);
            g2.setTransform(tx2);
            g2.fill(ARROW_HEAD);
            g2.setTransform(tx1);
    		
    		g1.setFont(new Font("Segoe UI Bold", Font.PLAIN, 15));
    		if(x1 > x2 && y1 > y2) {
    			g1.setColor(this.textcolor);
    			// x:+ y:-
    			//System.out.println("CASO 1 x:+ y:-");
    			int xA = x1 - Math.abs((x1-x2)/2);
    			int yA = y1 - Math.abs((y1-y2)/2);

    			g1.drawString(atributo+"", xA, yA+this.textDirection);
				if(isJohnson){
					g1.setColor(this.textcolor);
					g1.drawString("h="+this.getHolgura(), xA+20, yA+this.textDirection);
				}

    		}
    		
    		if(x1 < x2 && y1 < y2) {
    			g1.setColor(this.textcolor);
    			// x:- y:+
    			int xA = x2 - Math.abs((x1-x2)/2);
    			int yA = y2 - Math.abs((y1-y2)/2);
    			g1.drawString(atributo+"", xA, yA+ this.textDirection);

				if(isJohnson){
					g1.setColor(this.textcolor);
					g1.drawString("h="+this.getHolgura(), xA+20, yA+this.textDirection);
				}
    		}
    		
    		if(x1 >= x2 && y1 < y2) {
    			g1.setColor(this.textcolor);
    			// x:+ y:+
    			//System.out.println("CASO 2 x:+ y:+");
    			int xA = x1 - Math.abs((x1-x2)/2);
    			int yA = y2 - Math.abs((y1-y1)/2);
    			g1.drawString(atributo+"", xA, yA+ this.textDirection);

				if(isJohnson){
					g1.setColor(this.textcolor);
					g1.drawString("h="+this.getHolgura(), xA+20, yA+this.textDirection);
				}
    		}
    		
    		if(x1 < x2 && y1 >y2) {
    			g1.setColor(this.textcolor);
    			// x:- y:-
    			int xA = x2 - Math.abs((x1-x2)/2);
    			int yA = y1 - Math.abs((y1-y2)/2);
    			g1.drawString(atributo+"", xA, yA+ this.textDirection);

				if(isJohnson){
					g1.setColor(this.textcolor);
					g1.drawString("h="+this.getHolgura(), xA+20, yA+this.textDirection);
				}
    		}
        }else {
        	
        	g2.drawOval(this.x1 -30/2, this.y1 - 40, 30*3, 30);
        	AffineTransform tx1 = g2.getTransform();
            AffineTransform tx2 = (AffineTransform) tx1.clone();
            tx2.translate(this.x2, this.y2);
            tx2.rotate(angle - Math.PI / 2);
            g2.setTransform(tx2);
            g2.setTransform(tx1);
            
            g1.setColor(this.textcolor);
            g1.setFont(new Font("Segoe UI Bold", Font.PLAIN, 15));
            g1.drawString(atributo+"", this.x1+30, this.y1-50);
        }
        
        
		
	}
	
	
	public int getThickness() {
		return thickness;
	}


	public void setThickness(int thickness) {
		this.thickness = thickness;
	}


	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getAtributo() {
		return atributo;
	}

	public void setAtributo(int atributo) {
		this.atributo = atributo;
	}


	public Color getTextcolor() {
		return textcolor;
	}


	public void setTextcolor(Color textcolor) {
		this.textcolor = textcolor;
	}


	public int getTextDirection() {
		return textDirection;
	}


	public void setTextDirection(int textDirection) {
		this.textDirection = textDirection;
	}


	public Nodo getNodoInicio() {
		return nodoInicio;
	}


	public void setNodoInicio(Nodo nodoInicio) {
		this.nodoInicio = nodoInicio;
	}


	public Nodo getNodoFin() {
		return nodoFin;
	}


	public void setNodoFin(Nodo nodoFin) {
		this.nodoFin = nodoFin;
	}
	
	


	public boolean isCiclo() {
		return ciclo;
	}
	public void setCiclo(boolean ciclo) {
		this.ciclo = ciclo;
	}
	
	
	
	public int getHolgura() {
		return holgura;
	}


	public void setHolgura(int holgura) {
		this.holgura = holgura;
	}

	public int getNroActividadNodoInicio() {
		return nroActividadNodoInicio;
	}

	public void setNroActividadNodoInicio(int nroActividadNodoInicio) {
		this.nroActividadNodoInicio = nroActividadNodoInicio;
	}

	public int getNroActividadNodoFin() {
		return nroActividadNodoFin;
	}

	public void setNroActividadNodoFin(int nroActividadNodoFin) {
		this.nroActividadNodoFin = nroActividadNodoFin;
	}

	public boolean isJohnson() {
		return isJohnson;
	}

	public void setJohnson(boolean johnson) {
		isJohnson = johnson;
	}

	@Override
	public String toString() {
		return "Enlace{" +
				"x1=" + x1 +
				", y1=" + y1 +
				", x2=" + x2 +
				", y2=" + y2 +
				", atributo=" + atributo +
				", textcolor=" + textcolor +
				", textDirection=" + textDirection +
				", nroActividadNodoInicio=" + nroActividadNodoInicio +
				", nroActividadNodoFin=" + nroActividadNodoFin +
				", thickness=" + thickness +
				", ciclo=" + ciclo +
				", holgura=" + holgura +
				'}';
	}

	public int getIdNodoInicio() {
		return idNodoInicio;
	}

	public void setIdNodoInicio(int idNodoInicio) {
		this.idNodoInicio = idNodoInicio;
	}

	public int getIdNodoFin() {
		return idNodoFin;
	}

	public void setIdNodoFin(int idNodoFin) {
		this.idNodoFin = idNodoFin;
	}

	public int getIdGrafo() {
		return idGrafo;
	}

	public void setIdGrafo(int idGrafo) {
		this.idGrafo = idGrafo;
	}
}
