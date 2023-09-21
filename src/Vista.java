import javax.swing.*;
import java.awt.*;

public class Vista {
    private JFrame ventana;
    private int ventanaWidth;
    private int ventanaHeight;
    private Tablero tablero;
    private JFrame marcador;
    public Vista() {
        tablero = new Tablero(12, 6);
        initVista(700, 1200);
    }

    private void initVista(int ventanaWidth, int ventanaHeight) {
        // Crear un objeto JFrame (ventana principal)
        ventana = new JFrame("Mi Ventana");

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
        marcador = new JFrame("Marcador");
        marcador.setSize(ventanaWidth, 100);
        JLabel data = new JLabel("Flags: ");
        data.setBackground(Color.BLACK);
        marcador.add(data);
        ventana.add(marcador);

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
