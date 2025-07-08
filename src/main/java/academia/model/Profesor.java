package academia.model;

public class Profesor extends Persona {
    private String idProfesor;
    private String especialidad;
    private TipoContrato tipoContrato;

    public Profesor(String idProfesor, String especialidad, TipoContrato tipoContrato, String nombres, String apellidos, String email, String telefono) {
        super(nombres, apellidos, email, telefono);
        this.idProfesor = idProfesor;
        this.especialidad = especialidad;
        this.tipoContrato = tipoContrato;
    }

    public String getIdProfesor() { return idProfesor; }
    public String getEspecialidad() { return especialidad; }
    public TipoContrato getTipoContrato() { return tipoContrato; }

    @Override
    public String toString() {
        return "ID: " + idProfesor + " | " + super.toString() + " | Especialidad: " + especialidad + " | Contrato: " + tipoContrato;
    }
}