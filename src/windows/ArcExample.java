package windows;

import java.awt.*;
import java.awt.event.MouseAdapter;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class ArcExample extends JComponent
{
	
	

    public static void main ( String[] args )
    {
        JFrame frame = new JFrame ();
        frame.add ( new ArcExample () );
        frame.pack ();
        frame.setLocationRelativeTo ( null );
        frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        frame.setVisible ( true );
    }
}