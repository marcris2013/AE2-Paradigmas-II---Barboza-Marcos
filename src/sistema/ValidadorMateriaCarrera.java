/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.util.List;

public class ValidadorMateriaCarrera
        extends ValidadorInscripcion {

    @Override
    public String validar(
            Alumno alumno,
            Carrera carrera,
            Materia materia,
            List<Inscripcion> inscripciones) {

        if (!carrera.tieneMateria(materia)) {

            return "La materia no pertenece a la carrera seleccionada.";
        }

        return validarSiguiente(
                alumno,
                carrera,
                materia,
                inscripciones
        );
    }
}
