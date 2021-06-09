package Database.Compet.Dao;

import Database.Asignacion.ConexionAsignacion;
import Database.Compet.ConexionCompet;
import Database.Compet.Model.CompetGrafoModel;
import Database.Compet.Model.CompetNodoModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import grafos.Nodo;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompetNodoDao {
    static ResultSet resultado;
    static ConexionCompet conexion;
    static PreparedStatement ps;
    public static int insertNodo(CompetNodoModel nodoModel) {
        conexion=new ConexionCompet();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into nodo_compet (id_grafo,x,y,a,b,xx,yy,nombre,red,green,blue) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, nodoModel.getIdGrafo());
            ps.setInt(2, nodoModel.getX());
            ps.setInt(3, nodoModel.getY());
            ps.setInt(4, nodoModel.getA());
            ps.setInt(5, nodoModel.getB());
            ps.setDouble(6, nodoModel.getXx());
            ps.setDouble(7, nodoModel.getYy());
            ps.setString(8, nodoModel.getNombre());
            ps.setInt(9, nodoModel.getRed());
            ps.setInt(10, nodoModel.getGreen());
            ps.setInt(11, nodoModel.getBlue());
            res = ps.executeUpdate();
            System.out.println("EL VALOR DEL ID INSERTADO ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static List<CompetNodoModel> getListNodoModelByIdGrafo(int pIdGrafo){
        List<CompetNodoModel> lista = new ArrayList<CompetNodoModel>();
        ResultSet resultado;
        conexion=new ConexionCompet();
        resultado=conexion.getQuery("SELECT * \n" +
                "FROM nodo_compet a\n" +
                "WHERE a.id_grafo ="+pIdGrafo);
        try {
            while(resultado.next()) {

                int idNodo = resultado.getInt("id");
                int idGrafo = resultado.getInt("id_grafo");
                int x = resultado.getInt("x");
                int y = resultado.getInt("y");
                int a = resultado.getInt("a");
                int b = resultado.getInt("b");
                int xx = resultado.getInt("xx");
                int yy = resultado.getInt("yy");
                String nombre = resultado.getString("nombre");
                int red = resultado.getInt("red");
                int green = resultado.getInt("green");
                int blue = resultado.getInt("blue");
                CompetNodoModel nodo = new CompetNodoModel(idNodo,idGrafo,
                        x,y,a,b,xx,yy,nombre,red,green,blue);
                lista.add(nodo);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;

    }
}
