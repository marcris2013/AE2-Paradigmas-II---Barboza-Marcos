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

        // Profesores
        Profesor profesor1 =
                new Profesor("Carlos", "Gomez", 12345678);

        Profesor profesor2 =
                new Profesor("Laura", "Fernandez", 23456789);

        Profesor profesor3 =
                new Profesor("Mario", "Lopez", 34567890);

        Profesor profesor4 =
                new Profesor("Sofia", "Martinez", 45678901);

        // Coordinadores
        Coordinador coordinador1 =
                new Coordinador("Ana", "Martinez", 87654321);

        Coordinador coordinador2 =
                new Coordinador("Pedro", "Ramirez", 76543210);

        // Carreras
        Carrera carrera1 =
                new Carrera(
                        "Ingenieria en Sistemas",
                        5,
                        coordinador1,
                        15000.0,
                        20000.0
                );

        Carrera carrera2 =
                new Carrera(
                        "Licenciatura en Informatica",
                        4,
                        coordinador2,
                        14000.0,
                        19000.0
                );

        // Materias de la primera carrera
        Materia materia1 =
                new Materia(
                        "Programacion I",
                        1,
                        1,
                        profesor1
                );

        Materia materia2 =
                new Materia(
                        "Matematica Discreta",
                        1,
                        1,
                        profesor2
                );

        // Materias de la segunda carrera
        Materia materia3 =
                new Materia(
                        "Algoritmos",
                        1,
                        1,
                        profesor3
                );

        Materia materia4 =
                new Materia(
                        "Base de Datos",
                        1,
                        1,
                        profesor4
                );

        carrera1.agregarMateria(materia1);
        carrera1.agregarMateria(materia2);

        carrera2.agregarMateria(materia3);
        carrera2.agregarMateria(materia4);

        carreras.add(carrera1);
        carreras.add(carrera2);

        profesores.add(profesor1);
        profesores.add(profesor2);
        profesores.add(profesor3);
        profesores.add(profesor4);

        coordinadores.add(coordinador1);
        coordinadores.add(coordinador2);
    }

    public List<Carrera> getCarreras() {
        return carreras;
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

    public String matricularAlumno(
        String nombre,
        String apellido,
        int dni,
        int legajo,
        int indiceCarrera) {

    if (buscarAlumnoPorLegajo(legajo) != null) {
        return "Error: ya existe un alumno con ese legajo.";
    }

    if (indiceCarrera < 0 || indiceCarrera >= carreras.size()) {
        return "Opcion de carrera invalida.";
    }

    Alumno nuevoAlumno =
            new Alumno(nombre, apellido, dni, legajo);

    Carrera carrera =
            carreras.get(indiceCarrera);

    if (!carrera.matricularAlumno(nuevoAlumno)) {
        return "El alumno ya estaba matriculado en la carrera.";
    }

    alumnos.add(nuevoAlumno);

    return "Alumno matriculado con exito en "
            + carrera.getNombre();
}

    public String inscribirAlumno(
        int legajo,
        int indiceCarrera,
        String nombreMateria) {

    Alumno alumno =
            buscarAlumnoPorLegajo(legajo);

    if (alumno == null) {
        return "No existe un alumno con ese legajo.";
    }

    if (indiceCarrera < 0
            || indiceCarrera >= carreras.size()) {

        return "Opcion de carrera invalida.";
    }

    Carrera carrera =
            carreras.get(indiceCarrera);

    Materia materia =
            buscarMateriaPorNombre(nombreMateria);

    if (materia == null) {
        return "No existe una materia con ese nombre.";
    }

    ValidadorInscripcion validadorMatricula =
            new ValidadorMatriculaCarrera();

    ValidadorInscripcion validadorMateria =
            new ValidadorMateriaCarrera();

    ValidadorInscripcion validadorDuplicado =
            new ValidadorInscripcionDuplicada();

    validadorMatricula
            .setSiguiente(validadorMateria)
            .setSiguiente(validadorDuplicado);

    String error =
            validadorMatricula.validar(
                    alumno,
                    carrera,
                    materia,
                    inscripciones
            );

    if (error != null) {
        return error;
    }

    Inscripcion nuevaInscripcion =
            new Inscripcion(alumno, materia);

    inscripciones.add(nuevaInscripcion);

    return "Inscripcion realizada con exito.";
}

    public String registrarAsistencia(
            int legajo,
            String nombreMateria,
            boolean presente) {

        Inscripcion inscripcion =
                buscarInscripcion(legajo, nombreMateria);

        if (inscripcion == null) {
            return "No existe una inscripcion para ese alumno y materia.";
        }

        if (presente) {
            inscripcion.registrarAsistencia();
            return "Asistencia registrada correctamente.";
        }

        inscripcion.registrarInasistencia();

        return "Inasistencia registrada correctamente.";
    }

    public String cargarSituacionFinal(
            int legajo,
            String nombreMateria,
            String estado) {

        Inscripcion inscripcion =
                buscarInscripcion(legajo, nombreMateria);

        if (inscripcion == null) {
            return "No existe una inscripcion para ese alumno y materia.";
        }

        inscripcion.cargarSituacionFinal(estado);

        return "Situacion final registrada correctamente.";
    }
}