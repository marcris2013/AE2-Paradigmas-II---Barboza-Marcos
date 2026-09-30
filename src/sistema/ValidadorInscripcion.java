/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.util.List;

public abstract class ValidadorInscripcion {

    protected ValidadorInscripcion siguiente;

    public ValidadorInscripcion setSiguiente(
            ValidadorInscripcion siguiente) {

        this.siguiente = siguiente;
        return siguiente;
    }

    public abstract String validar(
            Alumno alumno,
            Carrera carrera,
            Materia materia,
            List<Inscripcion> inscripciones);

    protected String validarSiguiente(
            Alumno alumno,
            Carrera carrera,
            Materia materia,
            List<Inscripcion> inscripciones) {

        if (siguiente == null) {
            return null;
        }

        return siguiente.validar(
                alumno,
                carrera,
                materia,
                inscripciones
        );
    }
}
