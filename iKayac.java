/**
 * @author Diego Oswaldo Flores Rivas - 23714
 * @description Programa encargado de llevar la administracion de las reservas de vuelos
 * para dos tipos de usuarios, base y premium cada uno con sus propias caracteristicas
 */
public interface iKayac {

    public abstract Usuario login(String username, String password);
    public abstract void registroUsuario(String username, String password, String tipo);
    public abstract void cambiarPassword(String nuevaPassword);
    public abstract void cambiarTipoUsuario();
    public abstract void reservacion(String fechaVuelo,boolean tipoVuelo, int cantidadBoletos, String aerolinea, String username);
    public abstract void confirmacion(String numeroTarjeta, int cuotas, String claseVuelo, String numeroAsiento, int cantidadMaletas);
    public abstract String itinerario();
    public abstract void guardarUsuario();
    public abstract void guardarReserva();
    public abstract void leerUsuario();
    public abstract void leerReserva();
} 
