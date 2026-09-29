import java.util.ArrayList;

public class ColaPrioridad {
    private final ArrayList<Ticket> colaTickets;

    public ColaPrioridad() {
        colaTickets = new ArrayList<>();
    }

    public boolean estaVacia() {
        return colaTickets.isEmpty();
    }

    public void insertar(Ticket nuevoTicket) {
        int posicion = colaTickets.size();

        for (int i = 0; i < colaTickets.size(); i++) {
            if (nuevoTicket.compareTo(colaTickets.get(i)) < 0) {
                posicion = i;
                break;
            }
        }
        colaTickets.add(posicion, nuevoTicket);
    }

    public Ticket remover() {
        if (estaVacia()) {
            return null;
        }
        return colaTickets.remove(0);
    }

    public Ticket verFrente() {
        if (estaVacia()) {
            return null;
        }
        return colaTickets.get(0);
    }
}
