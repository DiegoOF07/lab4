import java.util.Scanner;
/**
 * @author Diego Oswaldo Flores Rivas - 23714
 * @description Programa encargado de llevar la administracion de las reservas de vuelos
 * para dos tipos de usuarios, base y premium cada uno con sus propias caracteristicas
 */
public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        iKayac kayac = new KayacController();
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU PRINCIPAL\n");
            System.out.println("1. Modo registro");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            String op = sc.nextLine();
            switch (op) {
                case "1":
                    System.out.println("\n REGISTRO \n");
                    System.out.print("Ingresa tu nombre de usuario: ");
                    String username1 = sc.nextLine();
                    System.out.print("Ingresa tu contraseña: ");
                    String password1 = sc.nextLine();
                    System.out.print("Ingresa el tipo de usuario que deseas ser (Base o Premium): ");
                    String tipo = sc.nextLine();
                    kayac.registroUsuario(username1, password1, tipo);
                    break;
                case "2":
                    System.out.println("\n INICIO DE SESIÓN \n");
                    System.out.print("Ingresa tu nombre de usuario: ");
                    String username2 = sc.nextLine();
                    System.out.print("Ingresa tu contraseña: ");
                    String password2 = sc.nextLine();
                    if(kayac.login(username2, password2)!=null){
                        System.out.println("Has iniciado sesión correctamente\n");
                        boolean continuar2 = true;
                        while (continuar2) {
                            System.out.println("\nMENU\n");
                            System.out.println("1. Realizar una reserva");
                            System.out.println("2. Confirmar tu reserva");
                            System.out.println("3. Tu perfil");
                            System.out.println("4. Regresar");
                            String op2 = sc.nextLine();
                            switch (op2) {
                                case "1":
                                    try {
                                        System.out.println("\n RESERVACIÓN \n");
                                        System.out.print("Ingresa la fecha de vuelo: ");
                                        String fechaVuelo = sc.nextLine();
                                        System.out.print("Ingresa si es viaje completo(true) o solo de ida(false): ");
                                        boolean tipoVuelo = Boolean.parseBoolean(sc.nextLine());
                                        System.out.print("Ingresa la cantidad de boletos: ");
                                        int cantidadBoletos = Integer.parseInt(sc.nextLine());
                                        System.out.print("Ingresa la aerolinea: ");
                                        String aerolinea = sc.nextLine();
                                        kayac.reservacion(fechaVuelo, tipoVuelo, cantidadBoletos, aerolinea, username2);
                                    } catch (Exception e) {
                                        System.out.println("Ingresa los datos como se te solicitan");
                                    }
                                    break;
                                case "2":
                                    try {
                                        System.out.println("\n CONFIRMACIÓN \n"); 
                                        System.out.print("Ingresa el numero de tu tarjeta: ");
                                        String numeroTarjeta = sc.nextLine();
                                        System.out.print("Ingresa el numero de cuotas (1-24): ");
                                        int cuotas = Integer.parseInt(sc.nextLine());
                                        System.out.print("Define la clase de vuelo (Coach o Primera Clase)");
                                        String claseVuelo = sc.nextLine();
                                        String numeroAsiento =String.valueOf((int)Math.random()*200+1);
                                        int cantMaletas = (int)(Math.random()*25+1);
                                        if(kayac.login(username2, password2).getTipo().equals("Premium")){
                                            System.out.print("Selecciona el numero de asiento: ");
                                            numeroAsiento = sc.nextLine();
                                            System.out.print("Ingresa la cantidad de maletas: ");
                                            cantMaletas = Integer.parseInt(sc.nextLine());
                                        }
                                        kayac.confirmacion(numeroTarjeta, cuotas, claseVuelo, numeroAsiento, cantMaletas);
                                        kayac.itinerario();
                                    } catch (Exception e) {
                                        System.out.println("Ingresa los datos como se te solicitan");
                                    }
                                    break;
                                case "3":
                                    try {
                                        System.out.println("\n TU PERFIL \n");
                                        if(kayac.login(username2, password2).getTipo().equals("Premium")){
                                            System.out.println("Cambia tu contraseña");
                                            System.out.print("Ingresa tu nueva contraseña: ");
                                            String password3 = sc.nextLine();
                                            kayac.cambiarPassword(password3);
                                            System.out.println("Tu contraseña se actualizo con exito");
                                        }else{
                                            System.out.println("1. Actualizar plan");
                                            System.out.println("2. Cambiar contraseña");
                                            System.out.println("3. Aplicar cupon");
                                            System.out.print("Elije la opcion que deseas: ");
                                            String op3 = sc.nextLine();
                                            switch (op3) {
                                                case "1":
                                                    kayac.cambiarTipoUsuario();
                                                    System.out.println("Tipo de usuario cambiado correctamente");
                                                    break;
                                                case "2":
                                                    System.out.print("Ingresa tu nueva contraseña: ");
                                                    String password3 = sc.nextLine();
                                                    kayac.cambiarPassword(password3);
                                                    System.out.println("Tu contraseña se actualizo con exito");
                                                    break;
                                                case "3":
                                                    System.out.println("Se ha aplicado un cupon con el 10% de descuento");
                                                    break;
                                            
                                                default:
                                                    System.out.println("Opcion del menu no valida");
                                                    break;
                                            }
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ingresa los datos como se te solicitan");
                                    }
                                    break;
                                case "4":
                                    continuar2 = false;
                                    break;
                                default:
                                    System.out.println("Opcion del menu no valida");
                                    break;
                            }
                        }
                    }else{
                        System.out.println("Credenciales incorrectas vuelve a intentarlo");
                    }
                    break;
                case "3":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion del menu no valida");
                    break;
            }
        }
        
    }
}
