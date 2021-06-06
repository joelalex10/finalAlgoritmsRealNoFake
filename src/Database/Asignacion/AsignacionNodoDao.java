package Database.Asignacion;

import Database.Asignacion.ConexionAsignacion;
import Database.Conexion;
import Database.GrafoBDD;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import grafos.Nodo;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsignacionNodoDao {

    static ResultSet resultado;
    static ConexionAsignacion conexion;
    static PreparedStatement ps;

    public static int insertNodo(Nodo nodo) {
        ConexionAsignacion conexion=new ConexionAsignacion();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into nodo (x,y,nombre,grafo_id_grafo,color_red,color_green,color_blue,nro_actividad,asignacion) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, nodo.getX());
            ps.setInt(2, nodo.getY());
            ps.setString(3,nodo.getNombre());
            ps.setInt(4, nodo.getIdGrafo());
            ps.setInt(5, nodo.getColor().getRed());
            ps.setInt(6, nodo.getColor().getGreen());
            ps.setInt(7, nodo.getColor().getBlue());
            ps.setInt(8, nodo.getNroActividad());
            ps.setInt(9,nodo.getAsignacion());
            res = ps.executeUpdate();
            System.out.println("EL ID DEL NODO ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
    public static List<Nodo> getNodoByIdGrafoAndAsignation(int pGrafoId, int pAsignacion){

        List<Nodo> lista = new ArrayList<Nodo>();


        ResultSet resultado;
        ConexionAsignacion conexion=new ConexionAsignacion();
        resultado=conexion.getQuery("SELECT * FROM nodo\n" +
                "WHERE grafo_id_grafo = "+pGrafoId+" \n" +
                "and asignacion ="+pAsignacion);

        try {
            while(resultado.next()) {

                int idNodo = resultado.getInt("id_nodo");
                int x = resultado.getInt("x");
                int y = resultado.getInt("y");
                String nombre = resultado.getString("nombre");
                int idGrafo = resultado.getInt("grafo_id_grafo");
                int red = resultado.getInt("color_red");
                int green = resultado.getInt("color_green");
                int blue = resultado.getInt("color_blue");
                int nroActividad =  resultado.getInt("nro_actividad");
                int asignacion =  resultado.getInt("asignacion");

                Nodo nodo = new Nodo();
                nodo.setIdNodo(idNodo);
                nodo.setX(x);
                nodo.setY(y);
                nodo.setNombre(nombre);
                nodo.setIdGrafo(idGrafo);
                nodo.setColor(new Color(red,green,blue));
                nodo.setNroActividad(nroActividad);
                nodo.setAsignacion(asignacion);
                lista.add(nodo);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
