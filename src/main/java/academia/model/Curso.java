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

    public boolean agregarEstudiante(Estudiante estudiante) {
        if (listaEstudiantes.size() >= capacidadMaxima) {
            return false; // ya está lleno
        }
        return listaEstudiantes.add(estudiante);
    }

    public boolean estaLleno() {
        return listaEstudiantes.size() >= capacidadMaxima;
    }

    public boolean contieneEstudiante(String carnet) {
        return listaEstudiantes.stream().anyMatch(e -> e.getCarnet().equals(carnet));
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Nombre: " + nombre + " | Profesor: " + profesor.getNombres() +
               " " + profesor.getApellidos() + " | Capacidad: " + capacidadMaxima + " | Inscritos: " + listaEstudiantes.size();
    }
}