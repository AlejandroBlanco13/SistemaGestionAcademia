package academia.model;

public class Estudiante extends Persona {
    private String carnet;
    private String carrera;
    private String nivel;

    public Estudiante(String carnet, String carrera, String nivel, String nombres, String apellidos, String email, String telefono) {
        super(nombres, apellidos, email, telefono);
        this.carnet = carnet;
        this.carrera = carrera;
        this.nivel = nivel;
    }

    public String getCarnet() { return carnet; }
    public String getCarrera() { return carrera; }
    public String getNivel() { return nivel; }

    public void setCarrera(String carrera) { this.carrera = carrera; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    @Override
    public String toString() {
        return "Carnet: " + carnet + " | " + super.toString() + " | Carrera: " + carrera + " | Nivel: " + nivel;
    }
}