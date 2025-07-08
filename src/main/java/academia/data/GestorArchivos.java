package academia.data;

import academia.model.*;
import java.io.*;
import java.util.*;

public class GestorArchivos {
    private static final String DIR = "resources/";
    
public static void guardarEstudiantes(List<Estudiante> estudiantes) throws IOException {
    new File(DIR).mkdirs(); // Asegura que la carpeta exista

    try (PrintWriter pw = new PrintWriter(new FileWriter(DIR + "estudiantes.txt"))) {
        for (Estudiante e : estudiantes) {
            pw.println(String.join("|", 
                e.getCarnet(),
                e.getCarrera(),
                String.valueOf(e.getNivel()),
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
        File file = new File(DIR + "estudiantes.txt");
        
        if (!file.exists()) {
            file.createNewFile();
            return estudiantes;
        }
        
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] datos = sc.nextLine().split("\\|");
                if (datos.length == 7) {
                    estudiantes.add(new Estudiante(
                        datos[0], datos[1], Integer.parseInt(datos[2]),
                        datos[3], datos[4], datos[5], datos[6]
                    ));
                }
            }
        }
        return estudiantes;
    }
    
    // Implementar métodos similares para Profesor, Curso y Matricula
}