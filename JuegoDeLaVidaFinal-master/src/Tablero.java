/*
En esta clase estan contenidos los principales metodos que hacen que el porgrama funcione, los metodos estan bien identificados con nombres que indican su funcion
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero implements Runnable {
    private final Celda[][] celdas;
    private final Configuracion configuracion;
    private final Estadisticas estadisticas;
    private final Random random;
    private final List<String> eventos;


    public Tablero(Configuracion configuracion, Estadisticas estadisticas) {
        this.configuracion = configuracion;
        this.estadisticas = estadisticas;
        this.celdas = new Celda[configuracion.getAltoDelTablero()][configuracion.getAnchoDelTablero()];
        this.random = new Random();
        this.eventos = new ArrayList<>();
        inicializarCeldas();
        distribuirElementosSobreElTablero();
    }

    private void inicializarCeldas() {
        for (int i = 0; i < configuracion.getAltoDelTablero(); i++) {
            for (int j = 0; j < configuracion.getAnchoDelTablero(); j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    private void distribuirElementosSobreElTablero() {
        int animalesDistribuidos = 0;
        int plantasDistribuidas = 0;

        while (animalesDistribuidos < configuracion.getCantidadDeAnimales()) {
            int x = random.nextInt(configuracion.getAltoDelTablero());
            int y = random.nextInt(configuracion.getAnchoDelTablero());

            if (!celdas[x][y].tieneAnimal() && !celdas[x][y].tienePlanta()) {
                celdas[x][y] = new Animal(configuracion.getEnergiaInicialDeAnimales());
                animalesDistribuidos++;
            }
        }

        while (plantasDistribuidas < configuracion.getCantidadDePlantas()) {
            int x = random.nextInt(configuracion.getAltoDelTablero());
            int y = random.nextInt(configuracion.getAnchoDelTablero());

            if (!celdas[x][y].tieneAnimal() && !celdas[x][y].tienePlanta()) {
                celdas[x][y] = new Planta(configuracion.getEnergiaInicialDePlantas());
                plantasDistribuidas++;
            }
        }
    }

    @Override
    public void run() {
        ejecutarMetodosDeFuncionalidad();
    }

    public void ejecutarMetodosDeFuncionalidad() {
        for (int i = 1; i <= configuracion.getCantidadDeEjecuciones(); i++) {
            System.out.println("Tiempo: " + i);
            System.out.println("_____________________________");
            estadisticas.resetearEventos();
            eventos.clear();
            moverAnimales();
            reproducirAnimales();
            incrementarEnergiaDePlantas();
            comerPlantas();
            muerteDeAnimales();
            actualizarEstadisticas();
            imprimirTablero();
            mostrarCambiosDelPasoAPasoDeLaEjecucionDelPrograma();
            estadisticas.registrarEstadisticas(i);
            System.out.println("_____________________________");

            try {
                Thread.sleep(1000); // Pausa de 1 segundo entre cada iteracion
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void moverAnimales() {
        for (int i = 1; i < configuracion.getAltoDelTablero(); i++) {
            for (int j = 1; j < configuracion.getAnchoDelTablero(); j++) {
                if (celdas[i][j].tieneAnimal()) {
                    ((Animal) celdas[i][j]).mover(this, i, j);
                }
            }
        }
    }

    private void reproducirAnimales() {
        for (int i = 1; i < configuracion.getAltoDelTablero(); i++) {
            for (int j = 1; j < configuracion.getAnchoDelTablero(); j++) {
                if (celdas[i][j].tieneAnimal()) {
                    Animal animal = (Animal) celdas[i][j];
                    int coordenadaX = i;
                    int coordenadaY = j;
                    estadisticas.agregarEvento("Un nacimiento en [" + coordenadaX + "," + coordenadaY + "]");
                    animal.reproducir(this, i, j, configuracion, estadisticas);
                }
            }
        }
    }
    private void incrementarEnergiaDePlantas(){
        for (int i = 0; i < configuracion.getAltoDelTablero(); i++) {
            for (int j = 0; j < configuracion.getAnchoDelTablero(); j++) {
                if (celdas[i][j].tienePlanta()){
                    Planta planta = (Planta) celdas[i][j];
                    planta.incrementarEnergia(1); // Incrementa energia en cada iteracion
                }

            }

        }
    }

    private void comerPlantas() {
        for (int i = 0; i < configuracion.getAltoDelTablero(); i++) {
            for (int j = 0; j < configuracion.getAnchoDelTablero(); j++) {
                if (celdas[i][j].tieneAnimal()) {
                    ((Animal) celdas[i][j]).comer(this, i, j);
                }
            }

        }
    }

    private void muerteDeAnimales() {
        for (int i = 0; i < configuracion.getAltoDelTablero(); i++) {
            for (int j = 0; j < configuracion.getAnchoDelTablero(); j++) {
                if (celdas[i][j].tieneAnimal()) {
                    Animal animal = (Animal) celdas[i][j];
                    animal.incrementarEdad();
                    animal.restarEnergiaDeVida(5); // Restar energía por iteracion
                    if (animal.getEnergia() <= 0 || animal.alcanzoLaEdadMaxima()) {
                        animal.morir();
                        celdas[i][j] = new Celda();
                        estadisticas.incrementarMuertes();
                        estadisticas.agregarEvento("Animal muerto en [" + i + "," + j + "]");

                    }
                }
            }
        }
    }

    private void actualizarEstadisticas() {
        int numeroPlantas = 0;
        int numeroAnimales = 0;

        for (int i = 0; i < configuracion.getAltoDelTablero(); i++) {
            for (int j = 0; j < configuracion.getAnchoDelTablero(); j++) {
                if (celdas[i][j].tienePlanta()) {
                    numeroPlantas++;
                } else if (celdas[i][j].tieneAnimal()) {
                    numeroAnimales++;
                }
            }
        }

        estadisticas.setNumeroPlantas(numeroPlantas);
        estadisticas.setNumeroAnimales(numeroAnimales);
    }


    private void imprimirTablero() {

        for (int i = 0; i < configuracion.getAltoDelTablero(); i++) {
            for (int j = 0; j < configuracion.getAltoDelTablero(); j++) {
                if (celdas[i][j].tieneAnimal()) {
                    System.out.print("A ");
                } else if (celdas[i][j].tienePlanta()) {
                    System.out.print("P ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void mostrarCambiosDelPasoAPasoDeLaEjecucionDelPrograma() {
        for (String evento : eventos) {
            // System.out.println(evento);
        }
    }

    public boolean elSerVivoEstaDentroDelTablero(int x, int y) {
        return x >= 0 && x < configuracion.getAltoDelTablero() && y >= 0 && y < configuracion.getAnchoDelTablero();
    }

    public boolean laCeldaEstaVacia(int x, int y) {
        return !celdas[x][y].tieneAnimal() && !celdas[x][y].tienePlanta();
    }

    public boolean laCeldaTienePlanta(int x, int y) {
        return celdas[x][y].tienePlanta();
    }

    public void moverCelda(int origenX, int origenY, int destinoX, int destinoY) {
        celdas[destinoX][destinoY] = celdas[origenX][origenY];
        celdas[origenX][origenY] = new Celda();
    }

    public void colocarAnimal(int x, int y, int energiaInicial) {
        celdas[x][y] = new Animal(energiaInicial);
    }

    public void removerPlantaQueSeComio(int x, int y) {
        celdas[x][y] = new Celda();
    }
}
