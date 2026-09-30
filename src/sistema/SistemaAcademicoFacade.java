/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.util.ArrayList;
import java.util.List;

public class SistemaAcademicoFacade {

    private final List<Carrera> carreras;
    private final List<Alumno> alumnos;
    private final List<Profesor> profesores;
    private final List<Coordinador> coordinadores;
    private final List<Inscripcion> inscripciones;

    public SistemaAcademicoFacade() {

        carreras = new ArrayList<>();
        alumnos = new ArrayList<>();
        profesores = new ArrayList<>();
        coordinadores = new ArrayList<>();
        inscripciones = new ArrayList<>();

        cargarDatosPrueba();
    }

    private void cargarDatosPrueba() {

        Profesor prof =
                new Profesor("Carlos", "Gómez", 12345678);

        Coordinador coord =
                new Coordinador("Ana", "Martínez", 87654321);

        Carrera carrera =
                new Carrera(
                        "Tecnicatura en Sistemas",
                        3,
                        coord,
                        15000.0,
                        20000.0
                );

        Materia materia =
                new Materia(
                        "Programación I",
                        1,
                        1,
                        prof
                );

        carrera.agregarMateria(materia);

        carreras.add(carrera);
        profesores.add(prof);
        coordinadores.add(coord);
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public Alumno buscarAlumnoPorLegajo(int legajo) {

        for (Alumno alumno : alumnos) {

            if (alumno.getLegajo() == legajo) {
                return alumno;
            }
        }

        return null;
    }

    public Materia buscarMateriaPorNombre(String nombre) {

        for (Carrera carrera : carreras) {

            Materia materia = carrera.buscarMateria(nombre);

            if (materia != null) {
                return materia;
            }
        }

        return null;
    }

    public Inscripcion buscarInscripcion(
            int legajo,
            String nombreMateria) {

        for (Inscripcion inscripcion : inscripciones) {

            if (inscripcion.getAlumno().getLegajo() == legajo
                    && inscripcion.getMateria()
                            .getNombre()
                            .equalsIgnoreCase(nombreMateria)) {

                return inscripcion;
            }
        }

        return null;
    }
}