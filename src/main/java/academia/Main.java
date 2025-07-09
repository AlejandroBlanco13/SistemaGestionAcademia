package academia;

import academia.data.GestorArchivos;
import academia.model.Estudiante;
import academia.model.Profesor;
import academia.model.TipoContrato;
import academia.model.Curso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // ================== Listas en memoria ==================
    private static List<Estudiante> estudiantes = new ArrayList<>();
    private static List<Profesor> profesores = new ArrayList<>();
    private static List<Curso> cursos = new ArrayList<>(); 
    private static final Scanner scanner = new Scanner(System.in);

    // ================== Método principal ==================
public static void main(String[] args) {
    cargarDatos();

    int opcion;
    do {
        mostrarMenu();
        opcion = leerEntero("Seleccione una opción: ");
        switch (opcion) {
            case 1 -> registrarEstudiante();
            case 2 -> mostrarEstudiantes();
            case 3 -> registrarProfesor();
            case 4 -> mostrarProfesores();
            case 5 -> registrarCurso();
            case 6 -> mostrarCursos();
            case 0 -> salir();
            default -> System.out.println("❌ Opción no válida. Intente de nuevo.");
        }
    } while (opcion != 0);
}

// ================== Métodos de menú ==================
private static void mostrarMenu() {
    System.out.println("""

            ===== MENÚ PRINCIPAL =====
            1. Registrar estudiante
            2. Mostrar estudiantes registrados
            3. Registrar profesor
            4. Mostrar profesores registrados
            5. Registrar curso
            6. Mostrar cursos registrados
            0. Salir
            """);
}

// ================== Carga de archivos ==================
private static void cargarDatos() {
    try {
        estudiantes = GestorArchivos.cargarEstudiantes();
        profesores = GestorArchivos.cargarProfesores();
        cursos = GestorArchivos.cargarCursos(profesores);
        System.out.println("✅ Estudiantes cargados: " + estudiantes.size());
        System.out.println("✅ Profesores cargados: " + profesores.size());
        System.out.println("✅ Cursos cargados: " + cursos.size());
    } catch (IOException e) {
        System.out.println("⚠️ Error al cargar archivos: " + e.getMessage());
    }
}

// ================== Registro y visualización ==================
private static void registrarEstudiante() {
    System.out.println("\n--- Registro de Estudiante ---");

    String carnet = leerTexto("Carnet: ");
    if (existeEstudiante(carnet)) {
        System.out.println("⚠️ Ya existe un estudiante con ese carnet.");
        return;
    }

    String carrera = leerTexto("Carrera: ");
    String nivel = leerTexto("Nivel: ");
    String nombres = leerTexto("Nombres: ");
    String apellidos = leerTexto("Apellidos: ");
    String email = leerTexto("Email: ");
    String telefono = leerTexto("Teléfono: ");

    Estudiante nuevo = new Estudiante(carnet, carrera, nivel, nombres, apellidos, email, telefono);
    estudiantes.add(nuevo);
    guardarEstudiantes();
}

private static void mostrarEstudiantes() {
    System.out.println("\n--- Lista de Estudiantes ---");
    if (estudiantes.isEmpty()) {
        System.out.println("⚠️ No hay estudiantes registrados.");
    } else {
        estudiantes.forEach(System.out::println);
    }
}

private static void registrarProfesor() {
    System.out.println("\n--- Registro de Profesor ---");

    String id = leerTexto("ID Profesor: ");
    if (existeProfesor(id)) {
        System.out.println("⚠️ Ya existe un profesor con ese ID.");
        return;
    }

    String especialidad = leerTexto("Especialidad: ");
    String tipo = leerTexto("Tipo de contrato (TIEMPO_COMPLETO / POR_HORAS): ").toUpperCase();

    TipoContrato contrato;
    try {
        contrato = TipoContrato.valueOf(tipo);
    } catch (IllegalArgumentException e) {
        System.out.println("❌ Tipo de contrato no válido.");
        return;
    }

    String nombres = leerTexto("Nombres: ");
    String apellidos = leerTexto("Apellidos: ");
    String email = leerTexto("Email: ");
    String telefono = leerTexto("Teléfono: ");

    Profesor nuevo = new Profesor(id, especialidad, contrato, nombres, apellidos, email, telefono);
    profesores.add(nuevo);
    guardarProfesores();
}

private static void mostrarProfesores() {
    System.out.println("\n--- Lista de Profesores ---");
    if (profesores.isEmpty()) {
        System.out.println("⚠️ No hay profesores registrados.");
    } else {
        profesores.forEach(System.out::println);
    }
}

private static void registrarCurso() {
    System.out.println("\n--- Registro de Curso ---");

    String codigo = leerTexto("Código del curso: ");
    if (cursos.stream().anyMatch(c -> c.getCodigo().equalsIgnoreCase(codigo))) {
        System.out.println("⚠️ Ya existe un curso con ese código.");
        return;
    }

    String nombre = leerTexto("Nombre del curso: ");
    String descripcion = leerTexto("Descripción: ");
    String idProfesor = leerTexto("ID del profesor encargado: ");
    int capacidad = leerEntero("Capacidad máxima: ");

    Profesor profesor = profesores.stream()
            .filter(p -> p.getIdProfesor().equalsIgnoreCase(idProfesor))
            .findFirst()
            .orElse(null);

    if (profesor == null) {
        System.out.println("❌ Profesor no encontrado.");
        return;
    }

    Curso nuevoCurso = new Curso(codigo, nombre, descripcion, profesor, capacidad);
    cursos.add(nuevoCurso);
    guardarCursos();
}

private static void mostrarCursos() {
    System.out.println("\n--- Lista de Cursos ---");
    if (cursos.isEmpty()) {
        System.out.println("⚠️ No hay cursos registrados.");
    } else {
        cursos.forEach(System.out::println);
    }
}

private static void salir() {
    System.out.println("👋 Gracias por usar el sistema. Hasta pronto.");
}

// ================== Métodos auxiliares ==================
private static String leerTexto(String mensaje) {
    System.out.print(mensaje);
    return scanner.nextLine().trim();
}

private static int leerEntero(String mensaje) {
    while (true) {
        try {
            System.out.print(mensaje);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Ingrese un número válido.");
        }
    }
}

private static boolean existeEstudiante(String carnet) {
    return estudiantes.stream().anyMatch(e -> e.getCarnet().equalsIgnoreCase(carnet));
}

private static boolean existeProfesor(String id) {
    return profesores.stream().anyMatch(p -> p.getIdProfesor().equalsIgnoreCase(id));
}

private static void guardarEstudiantes() {
    try {
        GestorArchivos.guardarEstudiantes(estudiantes);
        System.out.println("✅ Estudiante registrado y guardado con éxito.");
    } catch (IOException e) {
        System.out.println("❌ Error al guardar el estudiante: " + e.getMessage());
    }
}

private static void guardarProfesores() {
    try {
        GestorArchivos.guardarProfesores(profesores);
        System.out.println("✅ Profesor registrado y guardado con éxito.");
    } catch (IOException e) {
        System.out.println("❌ Error al guardar el profesor: " + e.getMessage());
    }
}

private static void guardarCursos() {
    try {
        GestorArchivos.guardarCursos(cursos);
        System.out.println("✅ Curso registrado y guardado con éxito.");
    } catch (IOException e) {
        System.out.println("❌ Error al guardar el curso: " + e.getMessage());
    }
  }
}