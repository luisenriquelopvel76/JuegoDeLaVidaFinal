/*
En esta clase se muestran los estados de las celdas del tablero de juego
 */
public class Celda {

    private boolean tieneAnimal;
    private boolean tienePlanta;
    private int energia;

    public Celda() {
        this.tieneAnimal = false;
        this.tienePlanta = false;
        this.energia = 0;
    }

    // Métodos para establecer y obtener el estado de la celda, especificando en el mismo nombre la accion de dicho metodo

    public boolean tieneAnimal() {
        return tieneAnimal;
    }

    public void setAnimal(boolean tieneAnimal) {
        this.tieneAnimal = tieneAnimal;
    }

    public boolean tienePlanta() {
        return tienePlanta;
    }

    public void setPlanta(boolean tienePlanta) {
        this.tienePlanta = tienePlanta;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void restarEnergiaDeVida(int cantidadDeEnergiaConsumida) {
        this.energia -= cantidadDeEnergiaConsumida;
    }
}
