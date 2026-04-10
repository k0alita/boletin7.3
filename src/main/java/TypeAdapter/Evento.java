package TypeAdapter;

import java.time.LocalDate;

public class Evento {
    private String nombre;
    private LocalDate fecha;

    public Evento(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Evemto{" +
                "nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
