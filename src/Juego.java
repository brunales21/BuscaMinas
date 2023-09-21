import java.util.*;

public class Juego {
    private static Juego juego;
    private Vista vista;
    private int cBombs;
    private Random rand;

    private Juego() {
        this.vista = new Vista();
        this.cBombs = 99;
        this.rand = new Random();

    }

    public static Juego getInstance() {
        if (juego == null) {
            juego = new Juego();
        }
        return juego;
    }

    public void init() {
        insertBombs();
        getAllNumOfBombs();
    }

    private void insertBombs() {
        for (int i = 0; i < cBombs; i++) {
            boolean colocada = false;
            do {
                int x = rand.nextInt(vista.getTablero().getRows());
                int y = rand.nextInt(vista.getTablero().getCols());
                Casilla casilla = vista.getTablero().getCasillaByPosition(new Vector2(x, y));
                if (isValidPosition(casilla)) {
                    casilla.setBomb(true);
                    colocada = true;
                }
            } while (!colocada);
        }
    }

    private boolean isValidPosition(Casilla casilla) {
        return casilla.getCasillasAround().stream()
                .noneMatch(a -> a.equals(Casilla.primeraCasilla)) && !casilla.isBomb();
    }



    private int getNumOfbombs(Casilla casilla) {
        return casilla.getCasillasAround().stream().filter(Casilla::isBomb).toList().size();
    }

    private void getAllNumOfBombs() {
        vista.getTablero().getCasillas().stream()
                .filter(a -> !a.isBomb())
                .forEach(a -> a.setNearBombs(getNumOfbombs(a)));
    }

}
