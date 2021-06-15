package Database.Asignacion.Dao;

import Database.Asignacion.ConexionAsignacion;
import Database.Conexion;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import grafos.Enlace;
import grafos.Nodo;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsignacionEnlaceDao {

    static ResultSet resultado;
    static ConexionAsignacion conexion;
    static PreparedStatement ps;

    public static int insertEnlace(Enlace enlace) {
        conexion=new ConexionAsignacion();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into enlace (x1,y1,x2,y2,atributo,thickness,nodo_inicio,nodo_fin,ciclo,color_red,color_green,color_blue,grafo_id,text_direction,nroActividadNodoInicio,nroActividadNodoFin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, enlace.getX1());
            ps.setInt(2, enlace.getY1());
            ps.setInt(3, enlace.getX2());
            ps.setInt(4, enlace.getY2());
            ps.setInt(5, enlace.getAtributo());
            ps.setInt(6, enlace.getThickness());
            ps.setInt(7, enlace.getIdNodoInicio());
            ps.setInt(8, enlace.getIdNodoFin());
            ps.setBoolean(9, enlace.isCiclo());
            ps.setInt(10, Color.white.getRed());
            ps.setInt(11, Color.white.getGreen());
            ps.setInt(12, Color.white.getBlue());
            ps.setInt(13, enlace.getIdGrafo());
            ps.setInt(14, enlace.getTextDirection());
            ps.setInt(15, enlace.getNroActividadNodoInicio());
            ps.setInt(16,enlace.getNroActividadNodoFin());
            res = ps.executeUpdate();
            System.out.println("EL VALOR DE RES ES: "+ps.getLastInsertID());

        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static List<Enlace> getListEnlaceByIdGrafo(int pGrafoId){
        List<Enlace>lista = new ArrayList<Enlace>();
        ResultSet resultado;
        ConexionAsignacion conexion=new ConexionAsignacion();
        resultado=conexion.getQuery("SELECT * FROM enlace \n" +
                "WHERE grafo_id="+pGrafoId);

        try {
            while(resultado.next()) {

                int newIdEnlace = resultado.getInt("id_grafo");
                int newX1 = resultado.getInt("x1");
                int newX2 = resultado.getInt("x2");
                int newY1 = resultado.getInt("y1");
                int newY2 = resultado.getInt("y2");
                int newAtributo = resultado.getInt("atributo");
                int newThickness = resultado.getInt("thickness");
                int newNodo_inicio = resultado.getInt("nodo_inicio");
                int newNodo_fin = resultado.getInt("nodo_fin");
                boolean newCiclo = resultado.getBoolean("ciclo");
                int newColorRed = resultado.getInt("color_red");
                int newColorGreen = resultado.getInt("color_green");
                int newColorBlue = resultado.getInt("color_blue");
                int newGrafoId = resultado.getInt("grafo_id");
                int newTextDirection = resultado.getInt("text_direction");
                int newNroActividadNodoInicio = resultado.getInt("nroActividadNodoInicio");
                int newNroActividadNodoFin = resultado.getInt("nroActividadNodoFin");
                Enlace enlace  = new Enlace();
                enlace.setX1(newX1);
                enlace.setX2(newX2);
                enlace.setY1(newY1);
                enlace.setY2(newY2);
                enlace.setAtributo(newAtributo);
                enlace.setThickness(newThickness);
                enlace.setCiclo(newCiclo);
                enlace.setTextcolor(new Color(newColorRed,newColorGreen,newColorBlue));
                enlace.setTextDirection(newTextDirection);
                enlace.setNroActividadNodoInicio(newNroActividadNodoInicio);
                enlace.setNroActividadNodoFin(newNroActividadNodoFin);
                lista.add(enlace);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }



}
