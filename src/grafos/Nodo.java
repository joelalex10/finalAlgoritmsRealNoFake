package grafos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class Nodo {
	private int idNodo;
	private int x;
	private int y;
	public static final int d = 50;
	private String nombre;
	private Color color;
	private int rec1;
	private int rec2;
	private int nroActividad;
	private boolean origenAsignacion;
	private  boolean destinoAsignacion;
	private int definicion;
	private int idGrafo;
	public int asignacion;
	
	
	public Nodo() {
	}

	public int getDefinicion() {
		return definicion;
	}

	public void setDefinicion(int definicion) {
		this.definicion = definicion;
	}

	public void pintar(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x -d/2, this.y - d/2, d*3, d);
		
		g.setColor(Color.white);
		g.drawString(this.nombre, this.x+45, this.y);

		//g.setColor(Color.white);
		//g.drawString(this.rec1 + "|"+this.rec2, this.x+90, this.y);
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRec1() {
		return rec1;
	}

	public void setRec1(int rec1) {
		this.rec1 = rec1;
	}

	public int getRec2() {
		return rec2;
	}

	public void setRec2(int rec2) {
		this.rec2 = rec2;
	}

	public boolean isOrigenAsignacion() {
		return origenAsignacion;
	}

	public void setOrigenAsignacion(boolean origenAsignacion) {
		this.origenAsignacion = origenAsignacion;
	}

	public boolean isDestinoAsignacion() {
		return destinoAsignacion;
	}

	public void setDestinoAsignacion(boolean destinoAsignacion) {
		this.destinoAsignacion = destinoAsignacion;
	}

	@Override
	public String toString() {
		return "Nodo{" +
				"x=" + x +
				", y=" + y +
				", nombre='" + nombre + '\'' +
				", color=" + color +
				", rec1=" + rec1 +
				", rec2=" + rec2 +
				", nroActividad=" + nroActividad +
				'}';
	}
	public int getNroActividad() {
		return nroActividad;
	}

	public void setNroActividad(int nroActividad) {
		this.nroActividad = nroActividad;
	}

	public int getIdGrafo() {
		return idGrafo;
	}

	public void setIdGrafo(int idGrafo) {
		this.idGrafo = idGrafo;
	}

	public int getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(int asignacion) {
		this.asignacion = asignacion;
	}

	public int getIdNodo() {
		return idNodo;
	}

	public void setIdNodo(int idNodo) {
		this.idNodo = idNodo;
	}
}
