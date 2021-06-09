package GrafoCompet;

import javax.swing.*;
import java.awt.*;

public class NodoCompet extends JPanel{
	
	private int con;
	private int x=0;
	private int y=0;
	private int a,b;
	private double xx;
	private double yy;
	private String name;
	private Color c;
	private int iz;
	private double auxX;
	private double auxY;

	public double getAuxX() {
		return auxX;
	}

	public void setAuxX(double auxX) {
		this.auxX = auxX;
	}

	public double getAuxY() {
		return auxY;
	}

	public void setAuxY(double auxY) {
		this.auxY = auxY;
	}

	public double getXx() {
		return xx;
	}



	public double getYy() {
		return yy;
	}



	public void setXx(double xx) {
		this.xx = xx;
	}



	public void setYy(double yy) {
		this.yy = yy;
	}




	public int getIz() {
		return iz;
	}



	public int getDer() {
		return der;
	}



	public void setIz(int iz) {
		this.iz = iz;
	}



	public void setDer(int der) {
		this.der = der;
	}

	private int der;
	
	public NodoCompet(int x, int y, int b, int a, String n, Color c, int iz,int der) {
		super();
		this.x = x;
		this.y = y;
		this.a = a;
		this.b=b;
		this.name = n;
		this.c = c;
		this.iz=iz;
		this.der=der;
	}
	public NodoCompet(int x, int y,  String n, Color c) {
		super();
		this.x = x;
		this.y = y;
		this.a = a;
		this.b=b;
		this.name = n;
		this.c = c;
	}
	public NodoCompet(int x, int y,double xx, double yy, int b, int a, String n, Color c ) {
		super();
		this.x = x;
		this.y = y;
		this.a = a;
		this.b=b;
		this.name = n;
		this.c = c;
		this.xx = xx;
		this.yy = yy;
	}
	public NodoCompet(double xx, double yy, String name) {
		super();
		this.xx = xx;
		this.yy = yy;
		this.name = name;
	}
	public NodoCompet(int x, int y, int b, int a, String n) {
		super();
		this.x = x;
		this.y = y;
		this.a = a;
		this.b=b;
		this.name = n;
	}



	
	
	

	public int getCon() {
		return con;
	}



	public void setCon(int con) {
		this.con = con;
	}



	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void dibujar (Graphics g) {
		super.paintComponent(g);
		g.setColor(getC());
		g.fillOval(getX(), getY(), getB(), getA());
		
	}
	public void dibujarNd (Graphics g) {
		super.paintComponent(g);
		g.setColor(this.c);
		g.fillOval(getX()-getB()/2, getY()-getA()/2, getB(), getA());
		
	}

	@Override
	public String toString() {
		return "NodoCompet{" +
				"con=" + con +
				", x=" + x +
				", y=" + y +
				", a=" + a +
				", b=" + b +
				", xx=" + xx +
				", yy=" + yy +
				", name='" + name + '\'' +
				", c=" + c +
				", iz=" + iz +
				", der=" + der +
				'}';
	}
}