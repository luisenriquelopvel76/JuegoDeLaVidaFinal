/*
Esta clase representa a la planta en su estado inicial
 */
public class Planta extends Celda {
    private int energia;
    public Planta(int energiaInicial) {
        super();
        setPlanta(true);
        setEnergia(energiaInicial);
    }
    public int getEnergia(){
        return energia;
    }
    public void incrementarEnergia(int cantidadDeEnergia){
        energia += cantidadDeEnergia;
    }
    public void restarEnergia(int cantidadDeEnergia){
        energia -= cantidadDeEnergia;
    }
}
