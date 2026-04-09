import java.time.LocalDate;
import java.util.Date;

public class Persona {
    private String nombre;
    private String dni;
    private String telefono;
    private LocalDate fecha;

    public Persona(String nombre, String dni, String telefono, LocalDate fecha) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.fecha = fecha;
    }

}
