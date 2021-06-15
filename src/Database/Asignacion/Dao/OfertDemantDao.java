package Database.Asignacion.Dao;

import Database.Asignacion.ConexionAsignacion;
import Database.Asignacion.Models.Demanda;
import Database.Asignacion.Models.Oferta;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import grafos.Enlace;
import grafos.Nodo;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfertDemantDao {

    public static int insertDemanda(Demanda demanda) {
        ConexionAsignacion conexion=new ConexionAsignacion();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into demandas (dato,grafo_id) VALUES (?,?)");
            ps.setInt(1, demanda.getDato());
            ps.setInt(2, demanda.getGrafoId());
            res = ps.executeUpdate();
            System.out.println("EL ID DEL demanda ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static int insertOferta(Oferta oferta) {
        ConexionAsignacion conexion=new ConexionAsignacion();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into ofertas (dato,grafo_id) VALUES (?,?)");
            ps.setInt(1, oferta.getDato());
            ps.setInt(2, oferta.getGrafoId());
            res = ps.executeUpdate();
            System.out.println("EL ID DEL demanda ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static List<Demanda> getListDemands(int pIdGrafo){
        List<Demanda>lista = new ArrayList<Demanda>();
        ResultSet resultado;
        ConexionAsignacion conexion=new ConexionAsignacion();
        resultado=conexion.getQuery("SELECT *\n" +
                "from demandas a\n" +
                "WHERE a.grafo_id = "+pIdGrafo);

        try {
            while(resultado.next()) {

                int idDemanda = resultado.getInt("id");
                int dato = resultado.getInt("dato");
                int idGrafo = resultado.getInt("grafo_id");

                lista.add(new Demanda(idDemanda, dato, idGrafo));
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static List<Oferta> getListOfertas(int pIdGrafo){
        List<Oferta>lista = new ArrayList<Oferta>();
        ResultSet resultado;
        ConexionAsignacion conexion=new ConexionAsignacion();
        resultado=conexion.getQuery("SELECT *\n" +
                "from ofertas a\n" +
                "WHERE a.grafo_id = "+pIdGrafo);

        try {
            while(resultado.next()) {

                int idOferta = resultado.getInt("id");
                int dato = resultado.getInt("dato");
                int idGrafo = resultado.getInt("grafo_id");

                lista.add(new Oferta(idOferta, dato, idGrafo));
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
