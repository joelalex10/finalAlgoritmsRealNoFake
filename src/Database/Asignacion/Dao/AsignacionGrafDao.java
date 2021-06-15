package Database.Asignacion.Dao;

import Database.Asignacion.ConexionAsignacion;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import grafos.Grafo;
import grafos.Nodo;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsignacionGrafDao {

    static ResultSet resultado;
    static ConexionAsignacion conexion;
    static PreparedStatement ps;

    public static int addGrafo(Grafo grafo){
        conexion=new ConexionAsignacion();
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into grafo (nombre) VALUES (?)");
            ps.setString(1,grafo.getNombre());
            ps.executeUpdate();
            res = (int) ps.getLastInsertID();
            System.out.println("EL VALOR DE RES PUEDE SER: "+ps.getLastInsertID());
            System.out.println("INSERCION CORRECTA");

        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static List<Grafo> listGrafos(){
        List<Grafo> lista = new ArrayList<Grafo>();
        ResultSet resultado;
        ConexionAsignacion conexion=new ConexionAsignacion();
        resultado=conexion.getQuery("SELECT * FROM grafo");

        try {
            while(resultado.next()) {
                int idGrafo = resultado.getInt("id_grafo");
                String nombre = resultado.getString("nombre");
                Grafo grafo = new Grafo();
                grafo.setIdGrafo(idGrafo);
                grafo.setNombre(nombre);
                lista.add(grafo);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;

    }
}
