package academia.data;

import academia.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
    private static final String DIR = "resources/";
    private static final String ARCHIVO_ESTUDIANTES = DIR + "estudiantes.txt";
    private static final String ARCHIVO_PROFESORES = DIR + "profesores.txt";
    private static final String ARCHIVO_CURSOS = DIR + "cursos.txt";
    private static final String ARCHIVO_MATRICULAS = DIR + "matriculas.txt";

    static {
        File dir = new File(DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // ================== ESTUDIANTES ==================
    public static void guardarEstudiantes(List<Estudiante> estudiantes) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_ESTUDIANTES))) {
            for (Estudiante e : estudiantes) {
                pw.println(String.join("|",
                        e.getCarnet(),
                        e.getCarrera(),
                        e.getNivel(),
                        e.getNombres(),
                        e.getApellidos(),
                        e.getEmail(),
                        e.getTelefono()
                ));
            }
        }
    }

    public static List<Estudiante> cargarEstudiantes() throws IOException {
        List<Estudiante> estudiantes = new ArrayList<>();
        File archivo = new File(ARCHIVO_ESTUDIANTES);
        if (!archivo.exists()) archivo.createNewFile();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                if (p.length == 7) {
                    estudiantes.add(new Estudiante(p[0], p[1], p[2], p[3], p[4], p[5], p[6]));
                }
            }
        }
        return estudiantes;
    }

    // ================== PROFESORES ==================
    public static void guardarProfesores(List<Profesor> profesores) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_PROFESORES))) {
            for (Profesor p : profesores) {
                pw.println(String.join("|",
                        p.getIdProfesor(),
                        p.getEspecialidad(),
                        p.getTipoContrato().name(),
                        p.getNombres(),
                        p.getApellidos(),
                        p.getEmail(),
                        p.getTelefono()
                ));
            }
        }
    }

    public static List<Profesor> cargarProfesores() throws IOException {
        List<Profesor> profesores = new ArrayList<>();
        File archivo = new File(ARCHIVO_PROFESORES);
        if (!archivo.exists()) archivo.createNewFile();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                if (p.length == 7) {
                    TipoContrato contrato = TipoContrato.valueOf(p[2]);
                    profesores.add(new Profesor(p[0], p[1], contrato, p[3], p[4], p[5], p[6]));
                }
            }
        }
        return profesores;
    }

    // ================== CURSOS ==================
    public static void guardarCursos(List<Curso> cursos) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_CURSOS))) {
            for (Curso c : cursos) {
                pw.println(String.join("|",
                        c.getCodigo(),
                        c.getNombre(),
                        c.getDescripcion(),
                        c.getProfesor().getIdProfesor(),
                        String.valueOf(c.getCapacidadMaxima())
                        // listaEstudiantes no se guarda aquí
                ));
            }
        }
    }

    public static List<Curso> cargarCursos(List<Profesor> profesores) throws IOException {
        List<Curso> cursos = new ArrayList<>();
        File archivo = new File(ARCHIVO_CURSOS);
        if (!archivo.exists()) archivo.createNewFile();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                if (p.length == 5) {
                    String codigo = p[0];
                    String nombre = p[1];
                    String descripcion = p[2];
                    String idProfesor = p[3];
                    int capacidad = Integer.parseInt(p[4]);

                    Profesor profesor = profesores.stream()
                            .filter(pr -> pr.getIdProfesor().equals(idProfesor))
                            .findFirst()
                            .orElse(null);

                    if (profesor != null) {
                        Curso curso = new Curso(codigo, nombre, descripcion, profesor, capacidad);
                        cursos.add(curso);
                    }
                }
            }
        }
        return cursos;
    }

    // ================== MATRICULAS ==================
    public static void guardarMatriculas(List<Matricula> matriculas) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_MATRICULAS))) {
            for (Matricula m : matriculas) {
                pw.println(String.join("|",
                        m.getEstudiante().getCarnet(),
                        m.getCurso().getCodigo(),
                        m.getFechaMatricula().toString()
                ));
            }
        }
    }

    public static List<Matricula> cargarMatriculas(List<Estudiante> estudiantes, List<Curso> cursos) throws IOException {
        List<Matricula> matriculas = new ArrayList<>();
        File archivo = new File(ARCHIVO_MATRICULAS);
        if (!archivo.exists()) archivo.createNewFile();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                if (p.length == 3) {
                    Estudiante estudiante = estudiantes.stream()
                            .filter(e -> e.getCarnet().equals(p[0]))
                            .findFirst()
                            .orElse(null);

                    Curso curso = cursos.stream()
                            .filter(c -> c.getCodigo().equals(p[1]))
                            .findFirst()
                            .orElse(null);

                    if (estudiante != null && curso != null) {
                        Matricula matricula = new Matricula(estudiante, curso, LocalDate.parse(p[2]));
                        matriculas.add(matricula);
                        curso.getListaEstudiantes().add(estudiante); // sincronicemos el curso
                    }
                }
            }
        }
        return matriculas;
    }
}
