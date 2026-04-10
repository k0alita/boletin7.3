package Estudiante;

public class Estudiante {
    private String nombre;
    private String curso;
    private double media;

    public Estudiante(String nombre, String curso, double media) {
        this.nombre = nombre;
        this.curso = curso;
        this.media = media;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", curso='" + curso + '\'' +
                ", media=" + media +
                '}';
    }
}
