import java.util.*;

public class Juego {
    private static Juego juego;
    private Tablero tablero;
    private int cBombs;
    private Random rand;

    private Juego() {
        this.tablero = new Tablero(40, 14, 18);
        this.cBombs = tablero.getFlags();
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
                int x = rand.nextInt(tablero.getRows());
                int y = rand.nextInt(tablero.getCols());
                Casilla casilla = tablero.getCasillaByPosition(new Vector2(x, y));
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
        tablero.getCasillas().stream()
                .filter(a -> !a.isBomb())
                .forEach(a -> a.setNearBombs(getNumOfbombs(a)));
    }

}
