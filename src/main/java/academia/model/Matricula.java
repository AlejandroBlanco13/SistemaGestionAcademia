package academia.model;

import java.time.LocalDate;

public class Matricula {
    private Estudiante estudiante;
    private Curso curso;
    private LocalDate fechaMatricula;

    public Matricula(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaMatricula = LocalDate.now();
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Curso getCurso() { return curso; }
    public LocalDate getFechaMatricula() { return fechaMatricula; }

    @Override
    public String toString() {
        return "Estudiante: " + estudiante.getCarnet() + " | Curso: " + curso.getCodigo() + " | Fecha: " + fechaMatricula;
    }
}
