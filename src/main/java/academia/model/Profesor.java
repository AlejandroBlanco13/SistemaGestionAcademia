package academia.model;

import academia.model.TipoContrato;

public class Profesor extends Persona {
    private String idProfesor;
    private String especialidad;
    private TipoContrato tipoContrato;

    public Profesor(String idProfesor, String especialidad, TipoContrato tipoContrato,
                    String nombres, String apellidos, String email, String telefono) {
        super(nombres, apellidos, email, telefono);
        this.idProfesor = idProfesor;
        this.especialidad = especialidad;
        this.tipoContrato = tipoContrato;
    }
    
    // Getters y setters...
}