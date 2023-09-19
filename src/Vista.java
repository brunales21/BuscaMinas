import javax.swing.*;

public class Vista {
    private JFrame ventana;
    private Tablero tablero;
    public Vista() {
        tablero = new Tablero(16, 16);
        initVista();
    }

    private void initVista() {
        // Crear un objeto JFrame (ventana principal)
        ventana = new JFrame("Mi Ventana");

        // Establecer el tamaño de la ventana
        ventana.setSize(700, 700);

        // Establecer operación de cierre por defecto
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar el panel a la ventana
        ventana.add(tablero);

        // Hacer visible la ventana
        ventana.setVisible(true);

        // Centrar la ventana en la pantalla
        ventana.setLocationRelativeTo(null);
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
