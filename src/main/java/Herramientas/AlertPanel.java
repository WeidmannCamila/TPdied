package main.java.Herramientas;

import main.java.Enumeration.EnumTipoAlerta;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class AlertPanel {

    public JFrame frame;

    public AlertPanel(EnumTipoAlerta tipoAlerta, String tituloVentana, String contenidoTitulo, String contenidoMensaje, Exception excepcion){ this.initializate(tipoAlerta, tituloVentana, contenidoTitulo, contenidoMensaje, excepcion);}


    private void initializate(EnumTipoAlerta tipoAlerta, String tituloVentana, String contenidoTitulo, String contenidoMensaje, Exception excepcion){
        this.frame =new JFrame();
        this.frame.setType(Window.Type.POPUP);
        this.frame.setResizable(false);
        this.frame.setBounds(100, 100, 405, 197);
        this.frame.setDefaultCloseOperation(3);
        this.frame.getContentPane().setLayout((LayoutManager)null);
        this.frame.setBackground(Color.blue);

        switch (tipoAlerta){
            case ERROR:
                JLabel lblError = new JLabel("ERROR!");
                lblError.setBackground(Color.cyan);
                lblError.setFont(new Font("Tahoma", 1, 31));
                lblError.setBounds(130, 11, 204, 53);
                this.frame.getContentPane().add(lblError);
                JLabel lblMensaje = new JLabel(contenidoMensaje);
                lblMensaje.setBackground(Color.red);
                lblMensaje.setBounds(21, 86, 368, 28);
                this.frame.getContentPane().add(lblMensaje);
                JButton btnAceptar = new JButton("Aceptar");
                this.frame.getContentPane().add(btnAceptar);

                break;

            case EXCEPCION:
                JOptionPane.showMessageDialog(frame,"EXCEPTION" , "Failure",  JOptionPane.INFORMATION_MESSAGE);
                tituloVentana = "Excepción!";
                contenidoTitulo = null;
                break;

            case INFORMACION:
                JOptionPane.showMessageDialog(frame,"iNFORMACION" , "Failure", JOptionPane.INFORMATION_MESSAGE);
                break;

            case CONFIRMACION:
                JOptionPane.showMessageDialog(frame,"Error" , "Failure", JOptionPane.YES_NO_CANCEL_OPTION);
                break;
        }

        frame.setTitle(tituloVentana);




    }
}
