package academia;

import academia.data.GestorArchivos;
import academia.model.*;
import java.io.IOException;
import java.util.*;

public class Main {
    private static List<Estudiante> estudiantes = new ArrayList<>();
    private static List<Profesor> profesores = new ArrayList<>();
    private static List<Curso> cursos = new ArrayList<>();
    
    public static void main(String[] args) {
        cargarDatos();
        mostrarMenuPrincipal();
    }
    
    private static void cargarDatos() {
        try {
            estudiantes = GestorArchivos.cargarEstudiantes();
            profesores = GestorArchivos.cargarProfesores();
            cursos = GestorArchivos.cargarCursos();
        } catch (IOException e) {
            System.out.println("Error cargando datos: " + e.getMessage());
        }
    }
    
    private static void guardarDatos() {
        try {
            GestorArchivos.guardarEstudiantes(estudiantes);
            GestorArchivos.guardarProfesores(profesores);
            GestorArchivos.guardarCursos(cursos);
        } catch (IOException e) {
            System.out.println("Error guardando datos: " + e.getMessage());
        }
    }
    
    private static void mostrarMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN ACADÉMICA ---");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar profesor");
            System.out.println("3. Registrar curso");
            System.out.println("4. Matricular estudiante");
            System.out.println("5. Consultar cursos");
            System.out.println("6. Salir");
            System.out.print("Seleccione opción: ");
            
            opcion = sc.nextInt();
            sc.nextLine();  // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    registrarEstudiante(sc);
                    break;
                case 2:
                    registrarProfesor(sc);
                    break;
                case 3:
                    registrarCurso(sc);
                    break;
                case 4:
                    matricularEstudiante(sc);
                    break;
                case 5:
                    consultarCursos();
                    break;
                case 6:
                    guardarDatos();
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 6);
    }
    
    private static void registrarEstudiante(Scanner sc) {
        // Implementar lógica de registro
    }
    
    // Implementar otros métodos de operaciones...
}