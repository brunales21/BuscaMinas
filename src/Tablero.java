import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Tablero extends JPanel {
    private Vista vista;
    private List<Casilla> casillas;
    private int flags;
    private int cBombs;
    private int rows;
    private int cols;
    public Tablero(int flags, int rows, int cols) {
        this.flags = flags;
        this.cBombs = flags;
        this.rows = rows;
        this.cols = cols;
        this.casillas = new ArrayList<>();

        vista = new Vista(this);
        initTablero();

        super.setLayout(new GridLayout(rows, cols));
    }

    private void initTablero() {
        boolean flag = true;
        for (int i = 0; i < rows; i++) {
            flag = !flag;
            for (int j = 0; j < cols; j++) {
                if (flag) {
                    addCasilla(new Casilla(this.vista, Casilla.color1, new Vector2(i, j)));
                    flag = false;
                } else {
                    addCasilla(new Casilla(this.vista, Casilla.color2, new Vector2(i, j)));
                    flag = true;
                }
            }
        }
        super.revalidate();
    }

    private void addCasilla(Casilla casilla) {
        casillas.add(casilla);
        super.add(casilla);
    }

    public Casilla getCasillaByPosition(Vector2 position) throws IndexOutOfBoundsException {
        if (position.getX()<0 || position.getY()<0 || position.getX()>rows || position.getY()>cols) {
            throw new IndexOutOfBoundsException();
        }
        return casillas.stream().filter(a -> a.getPosition().equals(position)).toList().get(0);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public List<Casilla> getCasillas() {
        return casillas;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getcBombs() {
        return cBombs;
    }
}
