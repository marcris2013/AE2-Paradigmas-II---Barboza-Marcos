/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.util.List;

public class ValidadorMatriculaCarrera
        extends ValidadorInscripcion {

    @Override
    public String validar(
            Alumno alumno,
            Carrera carrera,
            Materia materia,
            List<Inscripcion> inscripciones) {

        if (!carrera.estaMatriculado(alumno)) {

            return "El alumno no esta matriculado en esta carrera.";
        }

        return validarSiguiente(
                alumno,
                carrera,
                materia,
                inscripciones
        );
    }
}