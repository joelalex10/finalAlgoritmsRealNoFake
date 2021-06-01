package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GrafoBDD {
	private int idGrafo;
	private String nombre;
	
	public GrafoBDD() {
		
	}
	

	public GrafoBDD(int idGrafo, String nombre) {
		super();
		this.idGrafo = idGrafo;
		this.nombre = nombre;
	}


	public int getIdGrafo() {
		return idGrafo;
	}

	public void setIdGrafo(int idGrafo) {
		this.idGrafo = idGrafo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public GrafoBDD selectGrafobyId() {
		
		ResultSet resultado;
		Conexion conexion=new Conexion();
		resultado=conexion.getQuery("select a.id_grafo as id, a.nombre\r\n"
				+ "from grafo a where a.id_grafo ="+this.getIdGrafo());
		
		GrafoBDD grafoBDD = new GrafoBDD();
		
		try {
			while(resultado.next()) {
				nombre=resultado.getString("nombre");
				idGrafo=resultado.getInt("id");
				grafoBDD.setIdGrafo(idGrafo);
				grafoBDD.setNombre(nombre);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return grafoBDD;	
	}
	
	public int insertGrafo() {
		Conexion conexion=new Conexion();
		PreparedStatement ps;
		int res = 0;
		Connection con = null;
		try {
			con = conexion.getConexionMYSQL();
			ps = (PreparedStatement) con.prepareStatement("insert into grafo (nombre) VALUES (?)");
			ps.setString(1,this.getNombre());
			ps.executeUpdate();
			res = (int) ps.getLastInsertID();
			System.out.println("EL VALOR DE RES PUEDE SER: "+ps.getLastInsertID());
			System.out.println("INSERCION CORRECTA");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return res;
	}
	public static ArrayList<GrafoBDD>listGrafo(){
		ArrayList<GrafoBDD> lista = new ArrayList<GrafoBDD>();
		
		ResultSet resultado;
		Conexion conexion=new Conexion();
		resultado=conexion.getQuery("SELECT * FROM grafo");
		
		GrafoBDD grafoBDD = new GrafoBDD();
		
		try {
			while(resultado.next()) {
				String nombre=resultado.getString("nombre");
				int idGrafo=resultado.getInt("id_grafo");
				lista.add(new GrafoBDD(idGrafo,nombre));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

	@Override
	public String toString() {
		return "GrafoBDD [idGrafo=" + idGrafo + ", nombre=" + nombre + "]";
	}
	
	
}
