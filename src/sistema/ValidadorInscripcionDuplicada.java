/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.util.List;

public class ValidadorInscripcionDuplicada
        extends ValidadorInscripcion {

    @Override
    public String validar(
            Alumno alumno,
            Carrera carrera,
            Materia materia,
            List<Inscripcion> inscripciones) {

        for (Inscripcion inscripcion : inscripciones) {

            if (inscripcion.getAlumno().getLegajo()
                    == alumno.getLegajo()
                    &&
                    inscripcion.getMateria()
                            .getNombre()
                            .equalsIgnoreCase(
                                    materia.getNombre()
                            )) {

                return "El alumno ya esta inscripto en esta materia.";
            }
        }

        return validarSiguiente(
                alumno,
                carrera,
                materia,
                inscripciones
        );
    }
}
