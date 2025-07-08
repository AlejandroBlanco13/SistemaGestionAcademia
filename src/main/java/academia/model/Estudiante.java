package academia.model;

public class Estudiante extends Persona {
    private String carnet;
    private String carrera;
    private int nivel;

    public Estudiante(String carnet, String carrera, int nivel, 
                     String nombres, String apellidos, String email, String telefono) {
        super(nombres, apellidos, email, telefono);
        this.carnet = carnet;
        this.carrera = carrera;
        this.nivel = nivel;
    }
    
    // Getters y setters...
}