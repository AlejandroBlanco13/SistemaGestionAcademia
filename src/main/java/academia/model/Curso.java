public void matricularEstudiante(Estudiante estudiante) 
    throws CapacidadExcedidaException, DuplicadoException {
    
    if (estudiantes.size() >= capacidadMaxima) {
        throw new CapacidadExcedidaException("Curso lleno. Capacidad: " + capacidadMaxima);
    }
    
    if (estudiantes.contains(estudiante)) {
        throw new DuplicadoException("Estudiante ya matriculado: " + estudiante.getCarnet());
    }
    
    estudiantes.add(estudiante);
}