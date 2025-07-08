package academia;

import academia.data.GestorArchivos;
import academia.model.Estudiante;
import academia.model.Profesor;
import academia.model.TipoContrato;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Estudiante> estudiantes = new ArrayList<>();
    private static List<Profesor> profesores = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            estudiantes = GestorArchivos.cargarEstudiantes();
            profesores = GestorArchivos.cargarProfesores();
            System.out.println("✅ Estudiantes cargados: " + estudiantes.size());
            System.out.println("✅ Profesores cargados: " + profesores.size());
        } catch (IOException e) {
            System.out.println("⚠️ Error al cargar archivos: " + e.getMessage());
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarEstudiante();
                case 2 -> mostrarEstudiantes();
                case 3 -> registrarProfesor();
                case 4 -> mostrarProfesores();
                case 0 -> salir();
                default -> System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("""
                
                ===== MENÚ PRINCIPAL =====
                1. Registrar estudiante
                2. Mostrar estudiantes registrados
                3. Registrar profesor
                4. Mostrar profesores registrados
                0. Salir
                """);
    }

    private static void registrarEstudiante() {
        System.out.println("\n--- Registro de Estudiante ---");

        String carnet = leerTexto("Carnet: ");
        boolean existe = estudiantes.stream().anyMatch(e -> e.getCarnet().equalsIgnoreCase(carnet));
        if (existe) {
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
        try {
            GestorArchivos.guardarEstudiantes(estudiantes);
            System.out.println("✅ Estudiante registrado y guardado con éxito.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el estudiante: " + e.getMessage());
        }
    }

    private static void mostrarEstudiantes() {
        System.out.println("\n--- Lista de Estudiantes ---");
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            estudiantes.forEach(System.out::println);
        }
    }

    private static void registrarProfesor() {
        System.out.println("\n--- Registro de Profesor ---");

        String id = leerTexto("ID Profesor: ");
        boolean existe = profesores.stream().anyMatch(p -> p.getIdProfesor().equalsIgnoreCase(id));
        if (existe) {
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
        try {
            GestorArchivos.guardarProfesores(profesores);
            System.out.println("✅ Profesor registrado y guardado con éxito.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el profesor: " + e.getMessage());
        }
    }

    private static void mostrarProfesores() {
        System.out.println("\n--- Lista de Profesores ---");
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        } else {
            profesores.forEach(System.out::println);
        }
    }

    private static void salir() {
        System.out.println("👋 Gracias por usar el sistema.");
    }

    // Métodos auxiliares
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
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
}
