import javax.swing.*;
import java.awt.*;

public class Vista {
    private JFrame ventana;
    private int ventanaWidth;
    private int ventanaHeight;
    private Tablero tablero;
    private JPanel marcador;
    public Vista() {
        tablero = new Tablero(99, 20, 24);
        initVista(700, 800);
        initMarcador();
    }

    private void initVista(int ventanaWidth, int ventanaHeight) {
        // Crear un objeto JFrame (ventana principal)
        ventana = new JFrame("Mi Ventana");
        ventana.setLayout(new BorderLayout());

        // Establecer el tamaño de la ventana
        ventana.setSize(ventanaWidth, ventanaHeight);

        // Establecer operación de cierre por defecto
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar el panel a la ventana
        ventana.add(tablero);

        // Hacer visible la ventana
        ventana.setVisible(true);

        // Centrar la ventana en la pantalla
        ventana.setLocationRelativeTo(null);
    }

    private void initMarcador() {
        marcador = new JPanel();
        marcador.setPreferredSize(new Dimension(ventanaWidth, 30)); // Ajusta la altura según tus necesidades

        JLabel data = new JLabel("Flags: "+tablero.getFlags());
        marcador.add(data);
        ventana.add(marcador);

        ventana.add(marcador, BorderLayout.NORTH);
        ventana.add(tablero, BorderLayout.CENTER);
    }


    public JFrame getVentana() {
        return ventana;
    }

    public void setVentana(JFrame ventana) {
        this.ventana = ventana;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

}
