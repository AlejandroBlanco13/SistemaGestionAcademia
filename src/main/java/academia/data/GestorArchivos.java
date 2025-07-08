package academia.data;

import academia.model.*;
import academia.model.TipoContrato;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
    private static final String DIR = "resources/";
    private static final String ARCHIVO_ESTUDIANTES = DIR + "estudiantes.txt";
    private static final String ARCHIVO_PROFESORES = DIR + "profesores.txt";

    static {
        File dir = new File(DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // === ESTUDIANTES (ya implementado) ===
    public static void guardarEstudiantes(List<Estudiante> estudiantes) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_ESTUDIANTES))) {
            for (Estudiante e : estudiantes) {
                pw.println(String.join("|",
                        e.getCarnet(), e.getCarrera(), e.getNivel(),
                        e.getNombres(), e.getApellidos(), e.getEmail(), e.getTelefono()
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

    // === PROFESORES ===
    public static void guardarProfesores(List<Profesor> profesores) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_PROFESORES))) {
            for (Profesor p : profesores) {
                pw.println(String.join("|",
                        p.getIdProfesor(), p.getEspecialidad(), p.getTipoContrato().name(),
                        p.getNombres(), p.getApellidos(), p.getEmail(), p.getTelefono()
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
}
