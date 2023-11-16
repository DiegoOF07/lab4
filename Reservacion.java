/**
 * @author Diego Oswaldo Flores Rivas - 23714
 * @description Programa encargado de llevar la administracion de las reservas de vuelos
 * para dos tipos de usuarios, base y premium cada uno con sus propias caracteristicas
 */
public class Reservacion {
    private String fechaVuelo;
    private boolean tipoVuelo;
    private int cantidadBoletos;
    private String aerolinea;
    private String numeroTarjeta;
    private int cuotas;
    private String claseVuelo;
    private String numeroAsiento;
    private int cantMaletas;
    private String username;

    public Reservacion(String fechaVuelo, boolean tipoVuelo, int cantidadBoletos, String aerolinea,
            String numeroTarjeta, int cuotas, String claseVuelo, String numeroAsiento, int cantMaletas,
            String username) {
        this.fechaVuelo = fechaVuelo;
        this.tipoVuelo = tipoVuelo;
        this.cantidadBoletos = cantidadBoletos;
        this.aerolinea = aerolinea;
        this.numeroTarjeta = numeroTarjeta;
        this.cuotas = cuotas;
        this.claseVuelo = claseVuelo;
        this.numeroAsiento = numeroAsiento;
        this.cantMaletas = cantMaletas;
        this.username = username;
    }

    public String getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(String fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public boolean getTipoVuelo() {
        return tipoVuelo;
    }

    public void setTipoVuelo(boolean tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public void setCantidadBoletos(int cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public String getClaseVuelo() {
        return claseVuelo;
    }

    public void setClaseVuelo(String claseVuelo) {
        this.claseVuelo = claseVuelo;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public int getCantMaletas() {
        return cantMaletas;
    }

    public void setCantMaletas(int cantMaletas) {
        this.cantMaletas = cantMaletas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Reservacion\n"+
        "fechaVuelo: " + fechaVuelo + 
        "\ntipoVuelo: " + tipoVuelo + 
        "\ncantidadBoletos: "+ cantidadBoletos + 
        "\naerolinea: " + aerolinea + 
        "\nnumeroTarjeta: " + numeroTarjeta + 
        "\ncuotas: "+ cuotas + 
        "\nclaseVuelo: " + claseVuelo + 
        "\nnumeroAsiento: " + numeroAsiento + 
        "\ncantMaletas: "+ cantMaletas + 
        "\nA nombre de: " + username;
    }

    

    
}
