import javax.swing.*;
import java.awt.*;

public class Vista {
    private JFrame ventana;
    private int ventanaWidth;
    private int ventanaHeight;
    private Tablero tablero;
    private JPanel marcador;
    private JLabel data;
    public Vista(Tablero tablero) {
        this.tablero = tablero;
        initVista(700, 700);
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

        data = new JLabel("Flags: "+tablero.getFlags());
        marcador.add(data);
        ventana.add(marcador);

        ventana.add(marcador, BorderLayout.NORTH);
        ventana.add(tablero, BorderLayout.CENTER);
    }

    public void mostrarMensajeVictoria() {
        JOptionPane.showMessageDialog(ventana, "¡Has ganado! Felicidades", "Victoria", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensajeDerrota() {
        JOptionPane.showMessageDialog(ventana, "¡Pisaste una bomba!\nSuerte la proxima vez.", "Derrota", JOptionPane.ERROR_MESSAGE);
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

    public JPanel getMarcador() {
        return marcador;
    }

    public JLabel getData() {
        return data;
    }

    public void setData(JLabel data) {
        this.data = data;
    }
}
