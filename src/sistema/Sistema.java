package sistema;

import java.util.List;
import java.util.Scanner;

public class Sistema {

    private final Scanner scanner;
    private final SistemaAcademicoFacade fachada;

    public Sistema() {

        scanner = new Scanner(System.in);
        fachada = new SistemaAcademicoFacade();
    }

    public void menu() {

        int opcion;

        do {

            System.out.println("\n--- MENU DEL SISTEMA ---");
            System.out.println("1. Matricular alumno en una carrera");
            System.out.println("2. Inscribir alumno a una materia");
            System.out.println("3. Registrar asistencia / inasistencia");
            System.out.println("4. Cargar situacion final");
            System.out.println("5. Mostrar informacion de carreras");
            System.out.println("6. Mostrar alumnos de una materia");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> matricularAlumno();
                case 2 -> inscribirAlumno();
                case 3 -> registrarAsistencia();
                case 4 -> cargarSituacionFinal();
                case 5 -> mostrarCarreras();
                case 6 -> mostrarAlumnosMateria();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 0);
    }

    public void matricularAlumno() {

        System.out.println("\n--- MATRICULAR ALUMNO ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("DNI: ");
        int dni = scanner.nextInt();

        System.out.print("Legajo: ");
        int legajo = scanner.nextInt();
        scanner.nextLine();

        mostrarListaCarreras();

        System.out.print("Seleccione carrera: ");
        int indiceCarrera = scanner.nextInt() - 1;
        scanner.nextLine();

        String resultado =
                fachada.matricularAlumno(
                        nombre,
                        apellido,
                        dni,
                        legajo,
                        indiceCarrera
                );

        System.out.println(resultado);
    }

    public void inscribirAlumno() {

        System.out.println("\n--- INSCRIBIR ALUMNO A MATERIA ---");

        System.out.print("Legajo del alumno: ");
        int legajo = scanner.nextInt();
        scanner.nextLine();

        mostrarListaCarreras();

        System.out.print("Seleccione carrera: ");
        int indiceCarrera = scanner.nextInt() - 1;
        scanner.nextLine();

        List<Carrera> carreras = fachada.getCarreras();

        if (indiceCarrera < 0
                || indiceCarrera >= carreras.size()) {

            System.out.println("Opcion de carrera invalida.");
            return;
        }

        Carrera carrera = carreras.get(indiceCarrera);

        System.out.println("Materias disponibles:");

        for (Materia materia : carrera.getMaterias()) {
            System.out.println("- " + materia.getNombre());
        }

        System.out.print("Nombre de la materia: ");
        String nombreMateria = scanner.nextLine();

        String resultado =
                fachada.inscribirAlumno(
                        legajo,
                        indiceCarrera,
                        nombreMateria
                );

        System.out.println(resultado);
    }

    public void registrarAsistencia() {

        System.out.println("\n--- REGISTRAR ASISTENCIA ---");

        System.out.print("Legajo: ");
        int legajo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre de la materia: ");
        String materia = scanner.nextLine();

        System.out.println("1. Asistencia");
        System.out.println("2. Inasistencia");
        System.out.print("Seleccione: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion != 1 && opcion != 2) {
            System.out.println("Opcion incorrecta.");
            return;
        }

        String resultado =
                fachada.registrarAsistencia(
                        legajo,
                        materia,
                        opcion == 1
                );

        System.out.println(resultado);
    }

    public void cargarSituacionFinal() {

        System.out.println("\n--- CARGAR SITUACION FINAL ---");

        System.out.print("Legajo: ");
        int legajo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre de la materia: ");
        String materia = scanner.nextLine();

        System.out.print(
                "Estado final (Promocionado, Regular o Libre): "
        );

        String estado = scanner.nextLine();

        String resultado =
                fachada.cargarSituacionFinal(
                        legajo,
                        materia,
                        estado
                );

        System.out.println(resultado);
    }

    public void mostrarCarreras() {

        System.out.println("\n--- CARRERAS REGISTRADAS ---");

        for (Carrera carrera : fachada.getCarreras()) {

            System.out.println(carrera);

            System.out.println("Materias:");

            for (Materia materia : carrera.getMaterias()) {
                System.out.println("- " + materia);
            }
        }
    }

    public void mostrarAlumnosMateria() {

        System.out.println("\n--- ALUMNOS INSCRIPTOS ---");

        System.out.print("Nombre de la materia: ");
        String nombreMateria = scanner.nextLine();

        boolean encontrado = false;

        for (Inscripcion inscripcion
                : fachada.getInscripciones()) {

            if (inscripcion.getMateria()
                    .getNombre()
                    .equalsIgnoreCase(nombreMateria)) {

                Alumno alumno =
                        inscripcion.getAlumno();

                System.out.println(
                        "- Legajo: "
                        + alumno.getLegajo()
                        + " | "
                        + alumno.getApellido()
                        + ", "
                        + alumno.getNombre()
                );

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(
                    "No hay alumnos inscriptos en esta materia."
            );
        }
    }

    private void mostrarListaCarreras() {

        System.out.println("Carreras disponibles:");

        List<Carrera> carreras = fachada.getCarreras();

        for (int i = 0; i < carreras.size(); i++) {

            System.out.println(
                    (i + 1)
                    + ". "
                    + carreras.get(i).getNombre()
            );
        }
    }

    public static void main(String[] args) {

        Sistema sistema = new Sistema();
        sistema.menu();
    }
}