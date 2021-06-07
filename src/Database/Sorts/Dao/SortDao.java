package Database.Sorts.Dao;

import Database.Asignacion.ConexionAsignacion;
import Database.Conexion;
import Database.Sorts.Model.SortModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import grafos.Nodo;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortDao {

    static ResultSet resultado;
    static Conexion conexion;
    static PreparedStatement ps;

    public static int insertSort(SortModel sort) {
        Conexion conexion=new Conexion();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into sorts (sortText,nombre) VALUES (?,?)");
            ps.setString(1, sort.getSort());
            ps.setString(2, sort.getName());
            res = ps.executeUpdate();
            System.out.println("EL ID DEL SORT ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static List<SortModel> getListSorts(){

        List<SortModel> lista = new ArrayList<SortModel>();


        ResultSet resultado;
        Conexion conexion=new Conexion();
        resultado=conexion.getQuery("SELECT * FROM sorts");

        try {
            while(resultado.next()) {

                int idSort = resultado.getInt("id");
                String sortContent = resultado.getString("sortText");
                String name = resultado.getString("nombre");

                SortModel sort = new SortModel();
                sort.setIdSort(idSort);
                sort.setSort(sortContent);
                sort.setName(name);
                lista.add(sort);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
