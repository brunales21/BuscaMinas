import java.util.*;

public class Juego {
    private Vista vista;
    private int cBombs;
    private Random rand;

    public Juego() {
        this.vista = new Vista();
        this.cBombs = 200;
        this.rand = new Random();

        do {
            System.out.println("esperando a primer casilla");
        } while (Casilla.primeraCasilla == null);

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
        Arrays.stream(vista.getTablero().getComponents()).forEach(a -> ((Casilla) a).setNearBombs(getNumOfbombs(((Casilla) a))));
    }

}
