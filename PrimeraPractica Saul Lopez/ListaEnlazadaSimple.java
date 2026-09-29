public class ListaEnlazadaSimple {
    private Nodo primero;

    public ListaEnlazadaSimple() {
        this.primero = null;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public void insertarNodoInicio(Ticket ticket) {
        Nodo nuevoNodo = new Nodo(ticket);
        nuevoNodo.setSiguiente(primero);
        primero = nuevoNodo;
    }

    public void insertarNodoFinal(Ticket ticket) {
        Nodo nuevoNodo = new Nodo(ticket);
        if (primero == null) {
            primero = nuevoNodo;
            return;
        }
        Nodo nodoTemp = primero;
        while (nodoTemp.getSiguiente() != null) {
            nodoTemp = nodoTemp.getSiguiente();
        }
        nodoTemp.setSiguiente(nuevoNodo);
    }

    public Nodo buscarNodo(int idBuscar) {
        Nodo nodoActual = primero;
        while (nodoActual != null && nodoActual.getTicket().getId() != idBuscar) {
            nodoActual = nodoActual.getSiguiente();
        }
        return nodoActual;
    }

    public Nodo eliminarNodo(int idEliminar) {
        if (primero == null) {
            return null;
        }

        if (primero.getTicket().getId() == idEliminar) {
            Nodo eliminado = primero;
            primero = primero.getSiguiente();
            return eliminado;
        }
        Nodo anterior = primero;
        Nodo actual = primero.getSiguiente();
        while (actual != null && actual.getTicket().getId() != idEliminar) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (actual != null) {
            anterior.setSiguiente(actual.getSiguiente());
        }
        return actual;
    }

    public void mostrarLista() {
        if (primero == null) {
            System.out.println("La lista se encuentra vacía.\n");
            return;
        }
        Nodo nodoActual = primero;
        while (nodoActual != null) {
            System.out.println(nodoActual);
            nodoActual = nodoActual.getSiguiente();
        }
    }
}
