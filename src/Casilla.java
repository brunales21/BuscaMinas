import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Casilla extends JPanel {
    private Vista vista;
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

                if (isLeftClick(e)) {
                    if (primeraCasilla == null) {
                        setPrimeraCasilla(getThisInstance());
                        Juego.getInstance().init();
                    }
                    desvelarCasilla();
                } else if (isRightClick(e)) {
                    desvelarFlag();
                }
                if (gano()) {
                    vista.mostrarMensajeVictoria();
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

    public Casilla(Vista vista, Color color, Vector2 position) {
        this();
        this.vista = vista;
        this.position = position;
        this.casillaColor = color;
        super.setBackground(color);
    }

    private boolean gano() {
        return vista.getTablero().getCasillas().stream().filter(Casilla::isBomb).allMatch(c -> c.isFlagged)
                && vista.getTablero().getCasillas().stream().filter(c -> !c.isBomb).allMatch(c -> c.isSelected);
    }
    private void desvelarFlag() {
        if (isSelected) {
            return;
        }
        if (isFlagged) {
            jlabel.setText("");
            setBackground(casillaColor);
        } else {
            jlabel.setText("F");
            setBackground(flagColor);
        }
        updateNOfFlags();
        isFlagged = !isFlagged;
    }


    private void desvelarCasilla() {
        if (isBomb) {
            desvelarBombas();
        } else {
            barrer();
        }

    }

    private void destapar() {
        isSelected = true;
        jlabel.setText(String.valueOf(nearBombs));
        setBackground(selectedCasillaColor);
    }

    private void barrer() {
        if (isBomb) {
            return;
        }
        if (nearBombs == 0) {
            destapar();
            getCasillasAround().stream().filter(a -> !a.isSelected).forEach(Casilla::barrer);
        } else {
            destapar();
        }
    }
    public List<Casilla> getCasillasAround() {
        List<Casilla> casillasAround = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    Casilla closeCasilla = vista.getTablero()
                            .getCasillaByPosition(new Vector2(position.getX()+i, position.getY()+j));
                    casillasAround.add(closeCasilla);
                } catch (IndexOutOfBoundsException e) {
                    //System.out.println(e.getMessage());
                }
            }
        }
        return casillasAround;
    }


    private void desvelarBombas() {
        vista.getTablero().getCasillas().stream()
                .filter(Casilla::isBomb)
                .forEach(a -> {
                    a.setBackground(bombColor);
                    jlabel.setText("B");
                });
        vista.mostrarMensajeDerrota();
    }

    private void updateNOfFlags() {
        if (isFlagged) {
            vista.getData().setText("Flags: " + (vista.getTablero().getFlags() + 1));
            vista.getTablero().setFlags(vista.getTablero().getFlags() + 1);
        } else {
            vista.getData().setText("Flags: " + (vista.getTablero().getFlags() - 1));
            vista.getTablero().setFlags(vista.getTablero().getFlags() - 1);
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

    private boolean isLeftClick(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON1;
    }

    private boolean isRightClick(MouseEvent e) {
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

    public int getNearBombs() {
        return nearBombs;
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
