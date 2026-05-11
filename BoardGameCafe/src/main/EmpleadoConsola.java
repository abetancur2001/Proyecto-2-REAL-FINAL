package main;

import articulos.*;
import exceptions.AutenticacionException;
import exceptions.MostrarException;
import exceptions.TorneosException;
import exceptions.VentaNoPermitidaException;
import modelo.*;
import persistencia.GestorPersistencia;
import sujetos.*;
import torneos.Torneo;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static main.ClienteConsola.*;

public class EmpleadoConsola {

    private static Scanner sc = new Scanner(System.in);
    private static Café cafe;
    private static Empleado empleado;

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        GestorPersistencia gp = new GestorPersistencia();
        cafe = null;
        try{
            cafe = gp.cargarCafe();
            System.out.println("Datos cargados");
        } catch (Exception e){
            System.out.println("No se encontró cafe, creando uno nuevo...");
            System.out.println("Causa: " + e.getMessage());
            Administrador admin = new Administrador("Admin", 30, 9999, new ArrayList<>(), 4545, "adminPro");
            cafe = new Café(50, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>(), new ArrayList<Juego>(), admin, new HashMap<>(), 1, 1, 9, 1, 0);
        }
        cafe.inicializarDatos();

        boolean salir = false;
        boolean auten = false;
        while(!salir){
            mostrarMenuInicial();
            int opcionI = leerInt("");
            switch (opcionI){
                case 1:
                    crearCuenta();
                    break;
                case 2:
                    if (iniciarSesion()){
                        auten = true;
                        salir = true;
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción Invalida");
            }
        }

        if(auten){
            boolean exit = false;
            while(!exit){
                mostrarMenuPrincipal();
                int opcionP = leerInt("");
                switch (opcionP){

                    case 1:
                        verTurno();
                        break;
                    case 2:
                        solicitarCambioTurno();
                        break;
                    case 3:
                        sugerirPlatillo();
                        break;
                    case 4:
                        inscribirTorneo();
                        break;
                    case 5:
                        desinscribirTorneo();
                        break;
                    case 6:
                        comprarProducto();
                        break;
                    case 7:
                        solicitarIntercambioTurno();
                        break;
                    case 8:
                        solicitarPrestamo();
                        break;
                    case 0:
                        exit = true;
                        break;
                        default:
                        System.out.println("Opción Invalida");
                }
            }
            gp.guardarCafe(cafe);
            System.out.println("Datos guardados");

        }

    }

    private static void solicitarPrestamo(){
        System.out.println("----- SOLICITAR PRESTAMO -----");

        cafe.mostrarCatalogoJuegos();
        int opcionJ = leerInt("Ingrese el numero del juego: ");
        System.out.println("Ingrese el dia de la entrega del juego: ");
        int diaEnt = leerIntRango("Dia: ", 1, 31);
        int mesEnt = leerIntRango("Mes: ", 1, 12);
        int anioEnt = leerIntRango("Anio: ", LocalDate.now().getYear(), LocalDate.now().getYear() + 1);

        LocalDate fechaEnt = null;
        try{
            fechaEnt = LocalDate.of(anioEnt, mesEnt, diaEnt);
        }catch(DateTimeException e){
            System.out.println("Fecha invalida");
            return;
        }


        cafe.crearPrestamo(LocalDate.now(), fechaEnt, cafe.getCatalogoJuegos().get(opcionJ), empleado, cafe.getIdPrestamo(), new Mesa(false, 10000, new ArrayList<>(), true, 0));
        System.out.println("Prestamo creado exitosamente");

    }

    private static void comprarProducto(){
        ArrayList<Item> items = new ArrayList<Item>();

        boolean salir = false;

        while(!salir){
            ClienteConsola.mostrarMenuProductos();
            int opcionP = leerInt("Ingrese una opcion: ");
            switch(opcionP){
                case 1:
                    items.addAll(comprarJuegos());
                    break;
                case 2:
                    items.addAll(comprarPlatillos());
                    break;
                case 3:
                    verVentaTotal(items);
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción Invalida");
            }
        }
    }

    private static void verVentaTotal(ArrayList<Item> items){
        System.out.println("----- VER TOTAL VENTA -----");

        if(items.isEmpty()){
            throw new VentaNoPermitidaException("No hay items para vender");
        }

        System.out.println("Los productos actualmente seleccionados son: ");
        mostrarProductos(items);
        int conf = leerInt("Confirme si esta correcto (1. SI 2. NO): ");
        boolean opcionPun = (leerInt("¿Desea usar puntos? (1. SI 2. NO): ") == 1);
        boolean opcionCod = (leerInt("¿Desea usar codigo descuento? (1. SI 2. NO): ") == 1);

        if(conf == 2){
            return;
        }
        else if(conf == 1){
            try{
                cafe.crearVenta(items, empleado, LocalDate.now(),null,opcionPun, opcionCod);
            } catch (VentaNoPermitidaException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean iniciarSesion(){

        System.out.println("----- LOGIN EMPLEADO -----");
        String login = leerString("Usuario: ");
        int pass = leerInt("Clave: ");

        if(cafe.autenticarEmpleado(login, pass)== null){
            throw new AutenticacionException("Usuario no encontrado");
        }

        empleado = cafe.autenticarEmpleado(login, pass);
        return true;

    }

    private static void desinscribirTorneo(){
        System.out.println("----- DESINSCRIBIRSE AL TORNEO -----");

        ArrayList<Torneo> tor = new ArrayList<>();
        Torneo torn = null;

        try{
            tor = cafe.mostrarTorneosCliente(empleado);
        } catch (TorneosException e){
            System.out.println(e.getMessage());
            return;
        }

        int i = 0;
        for(Torneo t : tor){
            System.out.println(
                    "Opcion: "+i+"\n"+
                            "Nombre: " + t.getNombre() +"\n" +
                            "Dia: " + t.getDiaSemana() +"\n" +
                            "Numero de jugadores: " + t.getNumParticipantes() +"\n"+
                            "Juego: " + t.getJuegoAsociado().getNombre()
            );
            i += 1;
        }


        System.out.println("Ingrese la opcion del torneo que desea desinscribirse: ");
        int opcionT = leerIntRango("Ingrese la opcion del torneo que desea desinscribirse: ", 0, tor.size()-1);
        torn = tor.get(opcionT);

        int opcionD = leerIntRango("¿Desea desinscribirse de torneo? (1. SI 2. NO): ", 1, 2);

        if (opcionD == 1) {
            try{
                torn.desinscribir(empleado);
                System.out.println("Desinscripcion exitosa");
            } catch (TorneosException e){
                System.out.println(e.getMessage());
            }

        }
        else if (opcionD == 2) {
            System.out.println("Desinscripcion cancelada");
            return;
        }
        else {
            System.out.println("Opcion invalida");
        }
    }

    private static void inscribirTorneo(){
        System.out.println("----- INSCRIBIRSE AL TORNEO -----");

        ArrayList<UsuarioComprador> usuarios = new ArrayList<>();
        usuarios.add(empleado);

        if (cafe.getTorneos().isEmpty()) {
            throw new TorneosException("No hay torneos disponibles");
        }

        cafe.mostrarTorneos();
        int opcionT = leerIntRango("Ingrese la opcion del torneo que desea inscribirse: ", 0, cafe.getTorneos().size()-1);
        if(opcionT < 0 || opcionT >= cafe.getTorneos().size()){
            throw new TorneosException("Opcion invalida");
        }

        int cantUs = leerInt("Ingrese la cantidad de usuarios que desea inscribir: ");

        if(cantUs <= 2){
            int i = 0;
            while(i<cantUs){
                int tipo = leerIntRango("Ingrese la opcion del tipo de usuario: ", 1, 2);

                if(tipo == 1){
                    String login = leerString("Ingrese el login del cliente: )");
                    Cliente u = cafe.getMapaClientes().get(login);

                    if(u == null){
                        throw new TorneosException("El cliente no existe");
                    }

                    usuarios.add(u);
                }
                else if(tipo == 2){
                    String login = leerString("Ingrese el login del empleado: )");
                    Empleado u = cafe.getMapaEmpleados().get(login);

                    if (u == null) {
                        throw new TorneosException("El empleado no existe");
                    }

                    usuarios.add(u);
                }
                i += 1;
            }
        }
        else{
            System.out.println("La cantidad de usuarios debe ser menor o igual a 2");
            return;
        }
        Torneo t = cafe.getTorneos().get(opcionT);

        t.inscribir(empleado,usuarios);
        System.out.println("Inscripcion exitosa");
    }

    private static void solicitarIntercambioTurno(){
        System.out.println("----- SOLICITAR INTERCAMBIO DE TURNO -----");
        Empleado empI = null;

        String login = leerString("Ingrese el login del empleado que desea intercambiar: ");

        try{
            empI = cafe.getMapaEmpleados().get(login);
        } catch (Exception e){
            System.out.println("Empleado no encontrado");
        }

        cafe.crearIntercambioTurno(empleado,empI);
        System.out.println("Se creó la solicitud de intercambio de turno");


    }

    private static void sugerirPlatillo(){
        System.out.println("----- SUGERIR PLATILLO -----");

        ArrayList<Alergenos> a = new ArrayList<>();
        Platillos plat = null;
        sc.nextLine();

        System.out.println("Ingrese tipo de Platillo (1. Bebida 2. Pasteleria): " );
        int opcionPla = leerIntRango("Ingrese tipo de Platillo (1. Bebida 2. Pasteleria): ", 1, 2);

        int precio = leerInt("Ingrese el precio de venta: ");
        sc.nextLine();
        String nom = leerString("Ingrese el nombre del Platillo: ");
        int cantA = leerInt("Cantidad de alergenos (int): ");

        if (opcionPla == 2){
            System.out.println("Alergenos Disponibles: ");
            for (Alergenos al : Alergenos.values()) {
                System.out.println(al.ordinal() + 1 + ". " + al.name());
            }
            int i = 0;
            while(i < cantA){
                System.out.println("Ingrese una opcion de alergeno: " );
                int opcionA = leerIntRango("Ingrese una opcion de alergeno: ", 1, Alergenos.values().length);
                Alergenos sel = Alergenos.values()[opcionA - 1];
                a.add(sel);
                i += 1;
            }
        }

        if(opcionPla == 1){
            int opcionA = leerIntRango("¿Es alcoholica? (1. SI 2. NO): ", 1, 2);

            boolean esAlc = (opcionA == 1);

            int opcionC = leerIntRango("¿Es una bebida caliente? : (1. SI 2. NO): ", 1, 2);

            boolean esCal = (opcionC == 1);

            plat = new Bebida(precio, nom, a, esAlc, esCal);

        }
        else if(opcionPla == 2){

            plat = new Pasteleria(precio, nom, a);

        }

        cafe.crearSugerenciaPlatillo(empleado, plat);
        System.out.println("Sugerencia creada exitosamente" );

    }

    private static void solicitarCambioTurno(){
        System.out.println("----- SOLICITAR CAMBIO TURNO -----");

        ArrayList<DayOfWeek> days = new ArrayList<>();
        System.out.println("--- DATOS PARA EL NUEVO TURNO ---" );

        int dias = leerInt("¿Cuantos dias trabajará?: ");

        if(dias < 0 || dias > 7){
            throw new MostrarException("Opcion invalida");
        }

        int w = 0;

        while(w < dias){
            System.out.println("Dias: 1. Lunes 2. Martes 3. Miercoles 4. Jueves 5. Viernes 6. Sabado 7. Domingo ");
            int opcionD = leerIntRango("Ingrese una opcion de dia de la semana: ", 1, 7);

            if(opcionD < 1 || opcionD > 7){
                throw new MostrarException("Opcion invalida");
            }

            days.add(DayOfWeek.of(opcionD));
            w += 1;
        }

        int horaE = leerIntRango("Ingrese la hora de inicio de trabajo (hh): ", 0, 23);
        int minE = leerIntRango("Ingrese la hora de fin de trabajo (hh): ", 0, 59);
        int horaF = leerIntRango("Ingrese la hora de fin de trabajo (hh): ", 0, 23);
        int minF = leerIntRango("Ingrese la hora de fin de trabajo (hh): ", 0, 59);

        LocalTime horaEntrada = LocalTime.of(horaE, minE);
        LocalTime horaSalida = LocalTime.of(horaF, minF);

        Turno turno = new Turno(days, horaEntrada, horaSalida);

        cafe.crearCambioTurno(empleado, turno);
        System.out.println("Solicitud creada exitosamente" );
    }

    private static void verTurno(){
        System.out.println("----- VER TURNO -----");

        try{
            System.out.println(
                    mostrarDias(empleado.getTurnoAsignado().getDias()) + "\n" +
                    "Hora Entrada: " + empleado.getTurnoAsignado().getHoraEntrada() + "\n" +
                    "Hora Salida: " + empleado.getTurnoAsignado().getHoraSalida()
            );
        } catch (Exception e){
            System.out.println("No hay turno asignado");
        }


    }

    private static String mostrarDias(ArrayList<DayOfWeek> dias){

        String days = "";

        for(DayOfWeek d : dias){
            days += " "+d;
        }

        return days;
    }

    private static void mostrarMenuInicial(){

        System.out.println("----- MENU EMPLEADO INICIAL-----");
        System.out.println("1. Crear Cuenta");
        System.out.println("2. Iniciar Sesion");
        System.out.println("0. Salir");

    }

    private static void crearCuenta(){

        ArrayList<Juego> juegos = new ArrayList<Juego>();
        ArrayList<Alergenos> a = new ArrayList<>();
        ArrayList<DayOfWeek> days = new ArrayList<>();

        System.out.println("----- CREAR CUENTA EMPLEADO-----");

        sc.nextLine();
        String nombre = leerLinea("Ingrese su Nombre Completo:");
        int edad = leerIntRango("Ingrese su Edad: ", 18, 100);
        int cedula = leerInt("Ingrese su Numero de Cedula: ");
        int cantidadJ = leerInt("¿Cuantos Juegos Favoritos quiere añadir?: ");

        if (cantidadJ != 0) {
            cafe.mostrarCatalogoJuegos();
        }

        int i = 0;
        while(i < cantidadJ){
            int opcion = leerIntRango("Ingrese una opcion: ", 0, cafe.getCatalogoJuegos().size()-1);

            if(opcion < 0 || opcion >= cafe.getCatalogoJuegos().size()){
                throw new MostrarException("Opcion invalida");
            }

            juegos.add(cafe.getCatalogoJuegos().get(opcion));
            i+=1;
        }
        int pass = leerInt("Ingrese una Contraseña: ");
        String usu = leerLinea("Ingrese un Usuario: ");
        int alergico = leerIntRango("¿Es alergico a algún alimento? (1. SI 2. NO): ", 1, 2);

        if(alergico != 1 && alergico != 2){
            throw new MostrarException("Opcion invalida");
        }

        if(alergico == 1){
            int cantA = leerInt("Cantidad de alergenos (int): ");
            System.out.println("Alergenos Disponibles: ");
            for (Alergenos al : Alergenos.values()) {
                System.out.println(al.ordinal() + 1 + ". " + al.name());
            }
            int in = 0;
            while(in < cantA){
                int opcionA = leerIntRango("Ingrese una opcion de alergeno: ", 1, Alergenos.values().length);
                Alergenos sel = Alergenos.values()[opcionA - 1];
                a.add(sel);
                in += 1;
            }
        }

        int tipo = leerIntRango("Ingrese el tipo de empleado (1. Mesero 2. Cocinero): ", 1, 2);

        int dias = leerInt("¿Cuantos dias trabajará?: ");

        if(dias < 0 || dias > 7){
            throw new MostrarException("Opcion invalida");
        }

        int w = 0;

        while(w < dias){
            System.out.println("Dias: 1. Lunes 2. Martes 3. Miercoles 4. Jueves 5. Viernes 6. Sabado 7. Domingo ");
            int opcionD = leerIntRango("Ingrese una opcion de dia de la semana: ", 1, 7);

            if(opcionD < 1 || opcionD > 7){
                throw new MostrarException("Opcion invalida");
            }

            days.add(DayOfWeek.of(opcionD));
            w += 1;
        }

        int horaE = leerIntRango("Ingrese la hora de inicio de trabajo (hh): ", 0, 23);
        int minE = leerIntRango("Ingrese los minutos de inicio de trabajo (mm): ", 0, 59);
        int horaF = leerIntRango("Ingrese la hora de fin de trabajo (hh): ", 0, 23);
        int minF = leerIntRango("Ingrese los minutos de fin de trabajo (mm): ", 0, 59);

        LocalTime horaEntrada = LocalTime.of(horaE, minE);
        LocalTime horaSalida = LocalTime.of(horaF, minF);

        Turno turno = new Turno(days, horaEntrada, horaSalida);

        if(tipo == 1){
            int numJ = leerInt("¿Cuantos juegos sabe enseñar?");
            if(numJ > 0){
                cafe.mostrarCatalogoJuegos();
                int t = 0;
                while(t < numJ){
                    int opcionJ = leerIntRango("Ingrese una opcion: ", 0, cafe.getCatalogoJuegos().size()-1);
                    if(opcionJ < 0 || opcionJ >= cafe.getCatalogoJuegos().size()){
                        throw new MostrarException("Opcion invalida");
                    }
                    juegos.add(cafe.getCatalogoJuegos().get(opcionJ));
                    t += 1;
                }
            }

            Mesero emp = new Mesero(nombre, edad, cedula, juegos, pass, usu, new ArrayList<Venta>(), new ArrayList<Prestamo>(), a, 0.0,0.0, turno, juegos);
            cafe.addEmpleado(emp);
            System.out.println("Cuenta creada exitosamente!");

        }
        else if(tipo == 2){
            Cocinero emp = new Cocinero(nombre, edad, cedula, juegos, pass, usu, new ArrayList<Venta>(), new ArrayList<Prestamo>(), a, 0.0,0.0, turno);
            cafe.addEmpleado(emp);
            System.out.println("Cuenta creada exitosamente!");

        }



    }

    private static void mostrarMenuPrincipal(){

        System.out.println("----- MENU EMPLEADO PRINCIPAL-----");
        System.out.println("1. Ver Turno");
        System.out.println("2. Solicitar Cambio de Turno");
        System.out.println("3. Sugerir Platillo");
        System.out.println("4. Inscribirse a Torneo");
        System.out.println("5. Desinscribirse de Torneo");
        System.out.println("6. Comprar Productos");
        System.out.println("7. Solicitar Intercambio de Turno");
        System.out.println("8. Solicitar Prestamo");
        System.out.println("0. Salir");

    }

    private static int leerInt(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            try {
                int valor = sc.nextInt();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número entero.");
                sc.next();
            }
        }
    }

    private static int leerIntRango(String mensaje, int min, int max) {
        while (true) {
            int valor = leerInt(mensaje);
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("Valor fuera de rango. Ingrese un número entre " + min + " y " + max + ".");
        }
    }

    private static String leerString(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String valor = sc.next();
            if (!valor.isBlank()) {
                return valor;
            }
            System.out.println("Entrada inválida. No puede estar vacío.");
        }
    }

    private static String leerLinea(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String valor = sc.nextLine();
            if (!valor.isBlank()) {
                return valor;
            }
            System.out.println("Entrada inválida. No puede estar vacío.");
        }
    }

}
