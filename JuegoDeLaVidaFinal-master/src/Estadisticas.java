/*
En esta clase se registran e imprimen las estaditicas del comportamiento de programa
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Estadisticas {
    private int numeroPlantas;
    private int numeroAnimales;
    private int numeroNacimientos;
    private int numeroMuertes;
    private final List<String> eventos;
    private final String archivo;


    public Estadisticas(String archivo) {
        this.archivo = archivo;
        this.eventos = new ArrayList<>();
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write("Tiempo;Plantas;Animales;Nacimientos;Muertes;Eventos\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void resetearEventos() {
        eventos.clear();
    }

    public void incrementarNacimientos() {
        numeroNacimientos++;
    }

    public void incrementarMuertes() {
        numeroMuertes++;
    }

    public void agregarEvento(String evento) {
        eventos.add(evento);
    }

    public void setNumeroPlantas(int numeroPlantas) {
        this.numeroPlantas = numeroPlantas;
    }

    public void setNumeroAnimales(int numeroAnimales) {
        this.numeroAnimales = numeroAnimales;
    }

    public void registrarEstadisticas(int iteracion) {


        try (FileWriter writer = new FileWriter("estadisticas.csv", true)) {

            writer.append(String.valueOf(iteracion)).append(";")
                    .append(String.valueOf(numeroPlantas)).append(";")
                    .append(String.valueOf(numeroAnimales)).append(";")
                    .append(String.valueOf(numeroNacimientos)).append(";")
                    .append(String.valueOf(numeroMuertes)).append(";")
                    .append(String.join(",", eventos)).append("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
