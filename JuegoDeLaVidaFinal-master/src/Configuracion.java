/*
En esta clase el usuario determina los valores iniciales del juego antes de la primera iteracion
 */
public class Configuracion {
    private static final int cantidadInicialDeAnimales = 50;
    private static final int cantidadInicialDePlantas = 50;
    private static final int anchoDelTablero = 30;
    private static final int altoDelTablero = 30;
    private static final int energiaInicialDeAnimales = 40;
    private static final int energiaInicialDePlantas = 20;
    private static final int cantidadDeEjecuciones = 10;

    public int getCantidadDeAnimales() {
        return cantidadInicialDeAnimales;
    }

    public int getCantidadDePlantas() {
        return cantidadInicialDePlantas;
    }

    public int getAnchoDelTablero() {
        return anchoDelTablero;
    }

    public int getAltoDelTablero() {
        return altoDelTablero;
    }

    public int getEnergiaInicialDePlantas() {
        return energiaInicialDePlantas;
    }

    public int getEnergiaInicialDeAnimales() {
        return energiaInicialDeAnimales;
    }

    public int getCantidadDeEjecuciones() {
        return cantidadDeEjecuciones;
    }

}
