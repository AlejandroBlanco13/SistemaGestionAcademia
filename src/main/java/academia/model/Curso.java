package academia.model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String codigo;
    private String nombre;
    private String descripcion;
    private Profesor profesor;
    private int capacidadMaxima;
    private List<Estudiante> listaEstudiantes;

    public Curso(String codigo, String nombre, String descripcion, Profesor profesor, int capacidadMaxima) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
        this.capacidadMaxima = capacidadMaxima;
        this.listaEstudiantes = new ArrayList<>();
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Profesor getProfesor() { return profesor; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public List<Estudiante> getListaEstudiantes() { return listaEstudiantes; }

    @Override
    public String toString() {
        return "Curso: " + nombre + " (" + codigo + ") | Profesor: " + profesor.getNombres() + " " + profesor.getApellidos() +
                " | Capacidad: " + capacidadMaxima + " | Inscritos: " + listaEstudiantes.size();
    }
}
