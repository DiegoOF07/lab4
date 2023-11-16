import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Diego Oswaldo Flores Rivas - 23714
 * @description Programa encargado de llevar la administracion de las reservas de vuelos
 * para dos tipos de usuarios, base y premium cada uno con sus propias caracteristicas
 */
public class KayacController implements iKayac{
    private ArrayList<Usuario> usuarios;
    private ArrayList<Reservacion> reservaciones;
    private Data userData;
    private Data reservacionData;
    private String loggedUser;
    private boolean reservacionPendiente;
    private Reservacion reservacionEnProceso;

    /**
     * @description Controlador que inicializa todos los atributos
     */
    public KayacController(){
        usuarios = new ArrayList<Usuario>();
        reservaciones = new ArrayList<Reservacion>();
        userData = new Data("usuarios.csv");
        reservacionData = new Data("reservaciones.csv");
        loggedUser = null;
        reservacionPendiente = false;
        reservacionEnProceso = null;
        try {
            userData.createFile();
            reservacionData.createFile();
        } catch (Exception e) {
            System.out.println("Ocurrio un error al crear los arhivos CSV");
        }
        
    }

    /**
     * @description Metodo que inicia sesion para cualquier usuario
     * @param username 
     * @param password
     * @return Usuario
     */
    @Override
    public Usuario login(String username, String password) {
        Usuario miUsuario = null;
        for (Usuario usuario : usuarios) {
            if(usuario.getUsername().equals(username) && usuario.getPassword().equals(password)){
                miUsuario = usuario;
                loggedUser = usuario.getUsername();
            }
        }
        return miUsuario;
    }

    /**
     * @description Metodo que registra cualquier usuario
     * @param username 
     * @param password
     * @param tipo
     */
    @Override
    public void registroUsuario(String username, String password, String tipo) {
        usuarios.clear();
        leerUsuario();
        usuarios.add(new Usuario(username, password, tipo));
        guardarUsuario();
    }

    /**
     * @description Metodo que cambia la contraseña del usuario
     * @param password
     */
    @Override
    public void cambiarPassword(String nuevaPassword) {
        usuarios.clear();
        leerUsuario();
        for (Usuario usuario : usuarios) {
            if(usuario.getUsername().equals(loggedUser)){
                usuario.setPassword(nuevaPassword);
            }
        }
        guardarUsuario();
        
    }

    /**
     * @description Metodo que cambia el tipo de usuario
     */
    @Override
    public void cambiarTipoUsuario() {
        usuarios.clear();
        leerUsuario();
        for (Usuario usuario : usuarios) {
            if(usuario.getUsername().equals(loggedUser)){
                usuario.setTipo("Premium");
            }
        }
        guardarUsuario();
    }

    /**
     * @description Metodo que crea una reservacion
     * @param fechaVuelo
     * @param tipoVuelo
     * @param cantidadBoletos 
     * @param aerolinea
     * @param username
     */
    @Override
    public void reservacion(String fechaVuelo, boolean tipoVuelo, int cantidadBoletos, String aerolinea,
            String username) {
        if(!reservacionPendiente){
            reservacionPendiente = true;
            reservacionEnProceso = new Reservacion(
                fechaVuelo, 
                tipoVuelo, 
                cantidadBoletos, 
                aerolinea, 
                "", 
                0, 
                "", 
                "", 
                0, 
                loggedUser
                );
        }else{
            System.out.println("No puedes realizar otra reservacion hasta confirmar la anterior");
        }
    }

    /**
     * @description Metodo que confirma la reservacion y la guarda
     * @param numeroTarjeta
     * @param cuotas
     * @param claseVuelo
     * @param numeroAsiento
     * @param cantidadMaletas
     */
    @Override
    public void confirmacion(String numeroTarjeta, int cuotas, String claseVuelo, String numeroAsiento,
            int cantidadMaletas) {
        if(reservacionPendiente){
            reservaciones.clear();
            leerReserva();
            reservacionEnProceso.setNumeroTarjeta(numeroTarjeta);
            reservacionEnProceso.setCuotas(cuotas);
            reservacionEnProceso.setClaseVuelo(claseVuelo);
            reservacionEnProceso.setNumeroAsiento(numeroAsiento);
            reservacionEnProceso.setCantMaletas(cantidadMaletas);
            reservaciones.add(reservacionEnProceso);
            reservacionPendiente = false;
            guardarReserva();
        }else{
            System.out.println("No puedes confirmar una reservacion sin antes haberla creado");
        }  
    }
    /**
     * @description Metodo que devuelve la informacion de la reservacion
     */
    @Override
    public String itinerario() {
        String info = reservacionEnProceso.toString();
        return info;
    }

    /**
     * @description Metodo que guarda a los usuarios en el archivo CSV
     */
    @Override
    public void guardarUsuario() {
        try {
            userData.writeUserData(usuarios);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al gaurdar los usuarios en el archivo");
        }
    }

    /**
     * @description Metodo que guarda a las reservas en el archivo CSV
     */
    @Override
    public void guardarReserva() {
        try {
            reservacionData.writeReservacionData(reservaciones);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al gaurdar las reservaciones en el archivo");
        }
    }

    /**
     * @description Metodo que lee a los usuarios almacenados en el CSV
     */
    @Override
    public void leerUsuario() {
        try {
           for (Usuario usuario : userData.readUserData()) {
                usuarios.add(usuario);
           }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al cargar los usuarios del archivo CSV");
        } 
    }

    /**
     * @description Metodo que lee a las reservas en el archivo CSV
     */
    @Override
    public void leerReserva() {
        try {
           for (Reservacion reserva : reservacionData.readReservacionData()) {
                reservaciones.add(reserva);
           }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al cargar las reservaciones del archivo CSV");
        }
    }
}
