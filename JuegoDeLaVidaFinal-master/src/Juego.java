/*
Este programa es un automata celular que simula la vida de plantas y animales en un entorno prederminado
 */
/*
En el main se crean solamente los objetos: "configuracion, estadisticas, tablero, que son los necesarios para que se ejecute el programa ya que la clase "Tablero" es la que se encarga de los pricipales metodos
y tambien por supuesto se ejecuta el hilo que se encarga de mostrar el paso a paso
 */
public class Juego {
    public static void main(String[] args) {
        Configuracion configuracion = new Configuracion();
        Estadisticas estadisticas = new Estadisticas("estadisticas.csv");
        Tablero tablero = new Tablero(configuracion, estadisticas);
        Thread hiloTablero = new Thread(tablero);
        hiloTablero.start();

        try {
            hiloTablero.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

