/*
En esta clase se establecieron los parametros y acciones que puede realizar el animal en la ejecucion del programa
 */

import java.util.Random;

public class Animal extends Celda {

    private static final Random random = new Random();
    private int energia;
    private int edad;
    private static final int edadMaxima = 30;

    public Animal(int energiaInicial) {
        super();
        setAnimal(true);
        this.energia = energiaInicial;
    }

    public int getEnergia() {
        return energia;
    }

    public void restarEnergiaDeVida(int cantidad) {
        this.energia -= cantidad;
    }

    public void incrementarEdad(){
        edad++;
    }
    public boolean alcanzoLaEdadMaxima(){
        return edad >= edadMaxima;
    }

    public void incrementarEnergiaDeVidaPorComer(int cantidad) {
        this.energia += cantidad;
    }

    public void morir() {
        this.energia = 0;
        this.alcanzoLaEdadMaxima();
    }

    public void mover(Tablero tablero, int x, int y) {
        int nuevaX = x + random.nextInt(3) - 1;
        int nuevaY = y + random.nextInt(3) - 1;

        if (tablero.elSerVivoEstaDentroDelTablero(nuevaX, nuevaY) && tablero.laCeldaEstaVacia(nuevaX, nuevaY)) {
            tablero.moverCelda(x, y, nuevaX, nuevaY);
        }
    }

    public void reproducir(Tablero tablero, int x, int y, Configuracion configuracion, Estadisticas estadisticas) {
        if (this.energia >= 30) { // Umbral de energía para reproducción
            int nuevaX = x + random.nextInt(3) - 1;
            int nuevaY = y + random.nextInt(3) - 1;

            if (tablero.elSerVivoEstaDentroDelTablero(nuevaX, nuevaY) && tablero.laCeldaEstaVacia(nuevaX, nuevaY)) {
                tablero.colocarAnimal(nuevaX, nuevaY, configuracion.getEnergiaInicialDeAnimales());
                this.restarEnergiaDeVida(5); // Restar energía por reproducción
                estadisticas.incrementarNacimientos();
            }
        }
    }

    public void comer(Tablero tablero, int x, int y) {
        for (int i = -1; i < 0; i++) {
            for (int j = -1; j < 0; j++) {
                int nuevoX = x + i;
                int nuevoY = y + j;
                if (tablero.elSerVivoEstaDentroDelTablero(nuevoX, nuevoY) && tablero.laCeldaTienePlanta(nuevoX, nuevoY)) {
                    this.incrementarEnergiaDeVidaPorComer(10);
                    tablero.removerPlantaQueSeComio(nuevoX, nuevoY);
                }
            }
        }
    }
}
