import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket implements Comparable<Ticket> {
    private static int cantidad = 0;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final int id;
    private final String descripcion;
    private final String nombreCompleto;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    public Ticket(String descripcion, String nombreCompleto) {
        cantidad++;
        this.id = cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaResolucion = null;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public void resolver() {
        this.fechaResolucion = LocalDateTime.now();
    }

    @Override
    public int compareTo(Ticket otro) {
        int comparacion = this.fechaCreacion.compareTo(otro.fechaCreacion);
        if (comparacion != 0) {
            return comparacion;
        }
        return Integer.compare(this.id, otro.id);
    }

    @Override
    public String toString() {
        String resolucion = (fechaResolucion == null)
                ? "Pendiente"
                : fechaResolucion.format(FORMATO);
        return "------------------------------\n"
                + "Ticket #" + id + "\n"
                + "Usuario:          " + nombreCompleto + "\n"
                + "Descripción:      " + descripcion + "\n"
                + "Fecha creación:   " + fechaCreacion.format(FORMATO) + "\n"
                + "Fecha resolución: " + resolucion + "\n"
                + "------------------------------";
    }
}
