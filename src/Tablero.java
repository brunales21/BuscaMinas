import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel {
    private int rows;
    private int cols;
    public Tablero(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        super.setLayout(new GridLayout(rows, cols));
        initTablero();
    }

    private void initTablero() {
        boolean flag = true;
        for (int i = 0; i < getRows(); i++) {
            flag = !flag;
            for (int j = 0; j < getCols(); j++) {
                if (flag) {
                    super.add(new Casilla(this, Casilla.color1, new Vector2(i, j)));
                    flag = false;
                } else {
                    super.add(new Casilla(this, Casilla.color2, new Vector2(i, j)));
                    flag = true;
                }
            }
        }
        super.revalidate();
    }

    public Casilla getCasillaIndex(int x, int y) throws ArrayIndexOutOfBoundsException {
        String index = x+""+y;
        try {
            return (Casilla) super.getComponent(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            throw new ArrayIndexOutOfBoundsException();
        }

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
}
