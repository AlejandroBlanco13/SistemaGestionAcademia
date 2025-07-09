package academia.model;

import java.time.LocalDate;

public class Matricula {
    private Estudiante estudiante;
    private Curso curso;
    private LocalDate fechaMatricula;

    public Matricula(Estudiante estudiante, Curso curso, LocalDate fechaMatricula) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaMatricula = fechaMatricula;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    @Override
    public String toString() {
        return "Estudiante: " + estudiante.getNombres() + " " + estudiante.getApellidos() +
                " | Curso: " + curso.getNombre() + " | Fecha: " + fechaMatricula;
    }
}
