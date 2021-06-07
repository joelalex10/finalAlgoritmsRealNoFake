package Database.Arbol;

import Database.Conexion;
import Database.Sorts.Model.SortModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArbolDao
{
    static ResultSet resultado;
    static Conexion conexion;
    static PreparedStatement ps;

    public static int insertSort(ArbolModel arbol) {
        Conexion conexion=new Conexion();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into arboles (nombre,data_arbol) VALUES (?,?)");
            ps.setString(1, arbol.getNombre());
            ps.setString(2, arbol.getDataArbol());
            res = ps.executeUpdate();
            System.out.println("EL ID DEL ARBOL ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static ArrayList<ArbolModel> getListArboles(){

        ArrayList<ArbolModel> lista = new ArrayList<ArbolModel>();


        ResultSet resultado;
        Conexion conexion=new Conexion();
        resultado=conexion.getQuery("SELECT * FROM arboles");

        try {
            while(resultado.next()) {

                int idSort = resultado.getInt("id");
                String arbolContent = resultado.getString("data_arbol");
                String name = resultado.getString("nombre");

                ArbolModel arbol = new ArbolModel();
                arbol.setId(idSort);
                arbol.setDataArbol(arbolContent);
                arbol.setNombre(name);
                lista.add(arbol);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
