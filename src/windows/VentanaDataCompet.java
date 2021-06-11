package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaDataCompet extends JFrame {

    private DefaultTableModel modelo=new DefaultTableModel();
    private JTable tablaDatos;

    private VentanaCompet ventanaCompet;
    public VentanaDataCompet(VentanaCompet ventanaCompet){
        this.ventanaCompet = ventanaCompet;
        initialize();
    }

    public void initialize() {

        setTitle("DATOS COMPET");
        setBounds(100, 100, 890, 347);
        getContentPane().setLayout(null);
        tablaDatos = new JTable();
        tablaDatos.setBounds(50, 111, 747, 75);

        for(int i=0; i<2;i++) {
            modelo.addColumn("");
        }
        for(int i=0; i<3;i++) {
            modelo.addRow(new Object[]{"","",""});

        }

        tablaDatos.setRowHeight(25);
        tablaDatos.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));


        modelo.setValueAt("Punto/Nombre", 0, 0);
        modelo.setValueAt("X", 1, 0);
        modelo.setValueAt("Y", 2, 0);
        tablaDatos.setModel(modelo);
        add(tablaDatos);

        if(ventanaCompet.getLista().size()==1)
        {
            modelo.setValueAt(ventanaCompet.getLista().get(0).getName(), 0, 1);
            modelo.setValueAt(ventanaCompet.getLista().get(0).getXx(), 1, 1);
            modelo.setValueAt(ventanaCompet.getLista().get(0).getYy(), 2, 1);

        }else if(ventanaCompet.getLista().size()>1) {

            for(int i=0; i<ventanaCompet.getLista().size();i++){
                modelo.addColumn("");
                modelo.setValueAt(ventanaCompet.getLista().get(i).getName(), 0, (i+1));
                modelo.setValueAt(ventanaCompet.getLista().get(i).getXx(), 1, (i+1));
                modelo.setValueAt(ventanaCompet.getLista().get(i).getYy(), 2, (i+1));
            }
        }
    }
}
