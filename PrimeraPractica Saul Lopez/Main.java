import java.util.Scanner;

public class Main {
    private static final ColaPrioridad colaPendientes = new ColaPrioridad();
    private static final ListaEnlazadaSimple listaResueltos = new ListaEnlazadaSimple();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuAdministrador();
                    break;
                case 0:
                    System.out.println("\n¡Hasta pronto!");
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== SISTEMA DE TICKETS =====");
        System.out.println("1. Menú de usuario");
        System.out.println("2. Menú de administrador");
        System.out.println("0. Salir");
    }

    private static void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n----- MENÚ DE USUARIO -----");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket por ID");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    crearTicket();
                    break;
                case 2:
                    buscarTicket();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void menuAdministrador() {
        int opcion;
        do {
            System.out.println("\n----- MENÚ DE ADMINISTRADOR -----");
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    verTicketAlFrente();
                    break;
                case 2:
                    resolverTicketAlFrente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void crearTicket() {
        String nombre = leerTextoNoVacio("Nombre completo: ");
        String descripcion = leerTextoNoVacio("Descripción del problema: ");

        Ticket ticket = new Ticket(descripcion, nombre);
        colaPendientes.insertar(ticket);

        System.out.println("\nTicket creado con éxito. Guarde su ID para dar seguimiento: "
                + ticket.getId());
    }

    private static void buscarTicket() {
        int id = leerEntero("Ingrese el ID del ticket: ");
        Nodo nodo = listaResueltos.buscarNodo(id);

        if (nodo != null) {
            System.out.println("\nEl ticket está resuelto:");
            System.out.println(nodo.getTicket());
        } else {
            System.out.println("\nEl ticket está pendiente.");
        }
    }

    private static void verTicketAlFrente() {
        Ticket frente = colaPendientes.verFrente();
        if (frente == null) {
            System.out.println("\nNo hay tickets pendientes.");
        } else {
            System.out.println("\nTicket al frente de la cola:");
            System.out.println(frente);
        }
    }

    private static void resolverTicketAlFrente() {
        Ticket frente = colaPendientes.remover();
        if (frente == null) {
            System.out.println("\nNo hay tickets pendientes para resolver.");
            return;
        }
        frente.resolver();
        listaResueltos.insertarNodoFinal(frente);
        System.out.println("\nTicket resuelto:");
        System.out.println(frente);
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }
        }
    }

    private static String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            if (!linea.isEmpty()) {
                return linea;
            }
            System.out.println("Este campo no puede estar vacío.");
        }
    }
}
