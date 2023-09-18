import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Casilla extends JPanel {
    private Tablero tablero;
    private Vector2 position;
    private JLabel jlabel;
    private boolean isBomb;
    private boolean isSelected;
    private boolean isFlagged;
    private int nearBombs;
    static Casilla primeraCasilla;
    private Color casillaColor;
    static final Color color1 = new Color(100, 200, 100); // Verde claro
    static final Color color2 = new Color(0, 128, 0); // Verde oscuro
    private Color selectedCasillaColor = Color.gray;
    private Color bombColor = Color.red;
    private Color flagColor = Color.yellow;

    public Casilla() {
        this.jlabel = new JLabel();
        add(jlabel);
        this.isBomb = false;
        this.isSelected = false;
        this.isFlagged = false;

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isLeftButton(e)) {
                    if (primeraCasilla == null) {
                        setPrimeraCasilla(getThisInstance());
                    }
                    desvelarCasilla(isBomb());
                } else if (isRightButton(e)) {
                    desvelarFlag(isFlagged());
                }
            }


            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public Casilla(Tablero tablero, Color color, Vector2 position) {
        this();
        this.tablero = tablero;
        this.position = position;
        this.casillaColor = color;
        super.setBackground(color);
    }

    private void desvelarFlag(boolean isFlagged) {
        if (isFlagged()) {
            jlabel.setText("");
            toFlag(false);
            setBackground(casillaColor);
        } else {
            jlabel.setText("F");
            toFlag(true);
            setBackground(flagColor);
        }
    }


    private void desvelarCasilla(boolean isBomb) {
        if (isBomb) {
            setBackground(bombColor);
            jlabel.setText("B");
        } else {
            jlabel.setText(nearBombs+"");
            select(true);
            setBackground(selectedCasillaColor);
        }
    }
    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void select(boolean selected) {
        isSelected = selected;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void toFlag(boolean flagged) {
        isFlagged = flagged;
    }

    private boolean isLeftButton(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON1;
    }

    private boolean isRightButton(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON3;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setNearBombs(int nearBombs) {
        this.nearBombs = nearBombs;
    }

    public Casilla getThisInstance() {
        return this;
    }

    public void setPrimeraCasilla(Casilla primeraCasilla) {
        this.primeraCasilla = primeraCasilla;
    }

    public static Casilla getPrimeraCasilla() {
        return primeraCasilla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casilla casilla = (Casilla) o;
        return Objects.equals(position, casilla.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}