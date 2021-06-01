package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class NodoBDD {
	
	private int idNodo;
	private int x;
	private int y;
	private String nombre;
	private int idGrafo;
	private int colorRed;
	private int colorGreen;
	private int colorBlue;
	private int nroActividad;
	
	public NodoBDD() {
		
	}
	
	public NodoBDD(int idNodo, int x, int y, String nombre, int idGrafo, int colorRed, int colorGreen, int colorBlue, int nroActividad) {
		super();
		this.idNodo = idNodo;
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.idGrafo = idGrafo;
		this.colorRed = colorRed;
		this.colorGreen = colorGreen;
		this.colorBlue = colorBlue;
		this.nroActividad = nroActividad;
	}

	public int getIdNodo() {
		return idNodo;
	}

	public void setIdNodo(int idNodo) {
		this.idNodo = idNodo;
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
	public int getIdGrafo() {
		return idGrafo;
	}
	public void setIdGrafo(int idGrafo) {
		this.idGrafo = idGrafo;
	}
	public int getColorRed() {
		return colorRed;
	}
	public void setColorRed(int colorRed) {
		this.colorRed = colorRed;
	}
	public int getColorGreen() {
		return colorGreen;
	}
	public void setColorGreen(int colorGreen) {
		this.colorGreen = colorGreen;
	}
	public int getColorBlue() {
		return colorBlue;
	}
	public void setColorBlue(int colorBlue) {
		this.colorBlue = colorBlue;
	}
	
	public NodoBDD getNodoById() {
		
		NodoBDD nodoBDD = null;
		
		Conexion conexion=new Conexion();
		ResultSet resultado = conexion.getQuery("SELECT *\r\n"
				+ "from nodo a\r\n"
				+ "WHERE a.id_nodo = "+this.getIdNodo());
		
		try {
			while (resultado.next()) {
				int newX = resultado.getInt("x");
				int newY = resultado.getInt("y");
				String newNombre = resultado.getString("nombre");
				int newGrafoId = resultado.getInt("grafo_id_grafo");
				int newColorRed = resultado.getInt("color_red");
				int newColorGreen = resultado.getInt("color_green");
				int newColorBlue = resultado.getInt("color_blue");
				int newNroActividad = resultado.getInt("nro_actividad");
				nodoBDD= new NodoBDD(this.getIdNodo(), newX, newY, newNombre, newGrafoId,
						newColorRed, newColorGreen, newColorBlue,newNroActividad);
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return nodoBDD;
	}
	
	public ArrayList<NodoBDD> getNodoByGrafoId(){
		
		ArrayList<NodoBDD> newListNodoBDD= new ArrayList<NodoBDD>();
		newListNodoBDD.clear();
		Conexion conexion=new Conexion();
		ResultSet resultado = conexion.getQuery("select *\r\n"
				+ "from nodo c\r\n"
				+ "WHERE c.grafo_id_grafo = "+this.getIdGrafo());
		
		try {
			while (resultado.next()) {
				int newIdNodo = resultado.getInt("id_nodo");
				int newX = resultado.getInt("x");
				int newY = resultado.getInt("y");
				String newNombre = resultado.getString("nombre");
				int newIdGrafo = resultado.getInt("grafo_id_grafo");
				int newColorRed = resultado.getInt("color_red");
				int newColorGreen = resultado.getInt("color_green");
				int newColorBlue = resultado.getInt("color_blue");
				int newNroActividad = resultado.getInt("nro_actividad");
				newListNodoBDD.add(new NodoBDD(newIdNodo,newX,newY,newNombre,
						newIdGrafo,newColorRed,newColorGreen,newColorBlue,newNroActividad));
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return newListNodoBDD;
	}
	
	public int insertNodo() {
		Conexion conexion=new Conexion();
		PreparedStatement ps;
		int res = 0;
		Connection con = null;
		try {
			con = conexion.getConexionMYSQL();
			ps = (PreparedStatement) con.prepareStatement("insert into nodo (x,y,nombre,grafo_id_grafo,color_red,color_green,color_blue,nro_actividad) VALUES (?,?,?,?,?,?,?,?)");
			ps.setInt(1, this.getX());
			ps.setInt(2, this.getY());
			ps.setString(3,this.getNombre());
			ps.setInt(4, this.getIdGrafo());
			ps.setInt(5, this.getColorRed());
			ps.setInt(6, this.getColorGreen());
			ps.setInt(7, this.getColorBlue());
			ps.setInt(8,this.getNroActividad());
			res = ps.executeUpdate();
			System.out.println("EL VALOR DE RES EzxS: "+ps.getLastInsertID());
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return res;
	}

	public int getNroActividad() {
		return nroActividad;
	}

	public void setNroActividad(int nroActividad) {
		this.nroActividad = nroActividad;
	}

	@Override
	public String toString() {
		return "NodoBDD [idNodo=" + idNodo + ", x=" + x + ", y=" + y + ", nombre=" + nombre + ", idGrafo=" + idGrafo
				+ ", colorRed=" + colorRed + ", colorGreen=" + colorGreen + ", colorBlue=" + colorBlue + "]";
	}
	
	
	

}
