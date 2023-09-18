import java.util.*;

public class Juego {
    private Vista vista;
    private int cBombs;
    private Random rand;

    public Juego() {
        this.vista = new Vista();
        this.cBombs = 20;
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
                int index = rand.nextInt(vista.getTablero().getRows()*vista.getTablero().getCols());
                Casilla casilla = (Casilla) vista.getTablero().getComponent(index);
                if (isValidPosition(casilla)) {
                    casilla.setBomb(true);
                    colocada = true;
                }
            } while (!colocada);
        }
    }

    private boolean isValidPosition(Casilla casilla) {
        return getCasillasAround(casilla).stream()
                .noneMatch(a -> a.equals(Casilla.primeraCasilla)) && !casilla.isBomb();
    }

    private List<Casilla> getCasillasAround(Casilla casilla) {
        List<Casilla> casillasAround = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    casillasAround.add(getCasilla(casilla.getX() + i, casilla.getY() + j));
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return casillasAround;
    }

    private Casilla getCasilla(int x, int y) {
        return (Casilla) vista.getTablero().getComponent(Integer.parseInt(x+""+y));
    }

    private int getNumOfbombs(Casilla casilla) {
        return getCasillasAround(casilla).stream().filter(Casilla::isBomb).toList().size();
    }

    private void getAllNumOfBombs() {
        Arrays.stream(vista.getTablero().getComponents()).forEach(a -> ((Casilla) a).setNearBombs(getNumOfbombs(((Casilla) a))));
    }

}
