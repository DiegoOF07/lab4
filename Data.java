import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
/**
 * @author Diego Oswaldo Flores Rivas - 23714
 * @description Programa encargado de llevar la administracion de las reservas de vuelos
 * para dos tipos de usuarios, base y premium cada uno con sus propias caracteristicas
 */
public class Data {

    private File file;

    /**
     * @description Constructor encargado de crear la instancia del archivo
     * @param fileName
     */
    public Data(String fileName) {
        file = new File(fileName);
    }

    /**
     * @throws IOException
     * @description Metodo encargado de crear el archivo .csv en caso de que no exista
     * @throws Exception
     */
    public void createFile() throws IOException{
        if (!file.exists()) {
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            String line = null;
            if(file.getName().equals("usuarios.csv")){
                line = "username,password,tipo\n";
            }else if(file.getName().equals("reservaciones.csv")){
                line = "fechaVuelo,tipoVuelo,cantidadBoletos,aerolinea,numeroTarjeta,cuotas,claseVuelo,numeroAsiento,cantMaletas,username\n";
            }
            writer.print(line);
            writer.close();
        }
    }

    /**
     * @description Metodo encargado de escribir los usuarios en el archivo .csv
     * @param usuarios
     * @throws Exception
     */
    public void writeUserData(ArrayList<Usuario> usuarios) throws Exception{
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        String line ="username,password,tipo\n";
        writer.print(line);
        for (Usuario usuario:usuarios){
            line = usuario.getUsername() + "," +usuario.getPassword() + "," + usuario.getTipo()+"\n";
            writer.print(line);
        }
        writer.close();
    }

    /**
     * @description Metodo encargado de escribir las reservaciones en el archivo .csv
     * @param reservaciones
     * @throws Exception
     */
    public void writeReservacionData(ArrayList<Reservacion> reservaciones) throws Exception{
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        String line = "fechaVuelo,tipoVuelo,cantidadBoletos,aerolinea,numeroTarjeta,cuotas,claseVuelo,numeroAsiento,cantMaletas,username\n";
        writer.print(line);
        for (Reservacion reservacion: reservaciones){
            line = reservacion.getFechaVuelo()+","
            +reservacion.getTipoVuelo()+","
            +reservacion.getCantidadBoletos()+","
            +reservacion.getAerolinea()+","
            +reservacion.getNumeroTarjeta()+","
            +reservacion.getCuotas()+","
            +reservacion.getClaseVuelo()+","
            +reservacion.getNumeroAsiento()+","
            +reservacion.getCantMaletas()+","
            +reservacion.getUsername()+"\n";
            writer.print(line);
        }
        writer.close();
    }

    /** 
     * @description Metodo que se encargara de leer los datos del archivo y retornarlos como una lista de usuarios
     * @return ArrayList<Usuario>
     * @throws Exception
     */
    public ArrayList<Usuario> readUserData() throws Exception{
        ArrayList<Usuario> almacenados = new ArrayList<Usuario>();
        BufferedReader bufer = new BufferedReader(new FileReader(file));
        String line = bufer.readLine();
        while(line != null){
            List<String> items = Stream.of(line.split("\\s*,\\s*")).toList();
            if(!items.get(0).equals("username"))
            almacenados.add(new Usuario(items.get(0), items.get(1), items.get(2)));
            line = bufer.readLine();
        }
        if(line !=null){
            bufer.close();
        }
        return almacenados;
    }

    /** 
     * @description Metodo que se encargara de leer los datos del archivo y retornarlos como una lista de reservaciones
     * @return ArrayList<Reservacion>
     * @throws Exception
     */
    public ArrayList<Reservacion> readReservacionData() throws Exception{
        ArrayList<Reservacion> almacenados = new ArrayList<Reservacion>();
        BufferedReader bufer = new BufferedReader(new FileReader(file));
        String line = bufer.readLine();
        while(line != null){
            List<String> items = Stream.of(line.split("\\s*,\\s*")).toList();
            if(!items.get(0).equals("fechaVuelo"))
            almacenados.add(
                new Reservacion(
                    items.get(0), 
                    Boolean.parseBoolean(items.get(1)), 
                    Integer.parseInt(items.get(2)),
                    items.get(3), 
                    items.get(4), 
                    Integer.parseInt(items.get(5)), 
                    items.get(6),
                    items.get(7), 
                    Integer.parseInt(items.get(8)), 
                    items.get(9)
                )
            );
            line = bufer.readLine();
        }
        if(line !=null){
            bufer.close();
        }
        return almacenados;
    }
}
