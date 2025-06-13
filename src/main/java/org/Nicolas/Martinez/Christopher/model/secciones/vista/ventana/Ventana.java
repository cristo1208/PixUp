package org.Nicolas.Martinez.Christopher.model.secciones.vista.ventana;

import org.Nicolas.Martinez.Christopher.model.secciones.Ejecutable;

import javax.swing.*;
public class Ventana extends JFrame implements Ejecutable {
    private static Ventana ventana;
    private JLabel jLabel;
    private JTextField jTextField;
    private JButton jButton;

    private Ventana() {
        super( "Programa Pixup" );
        init();
    }
    private void init(){

    }
    public static Ventana getInstance( ) {
        if( ventana == null ) {
            ventana = new Ventana();
        }
        return ventana;
    }
    @Override
    public void run() {
        setBounds(100, 100, 300, 250);
        setVisible(true);
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }

}