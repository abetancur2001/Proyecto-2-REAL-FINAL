package main;

import articulos.*;
import com.sun.security.jgss.AuthorizationDataEntry;
import exceptions.*;
import modelo.*;
import persistencia.GestorPersistencia;
import sujetos.Administrador;
import sujetos.Cliente;
import sujetos.Empleado;
import sujetos.UsuarioComprador;
import torneos.Torneo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteConsola {

    private static Scanner sc = new Scanner(System.in);
    private static Café cafe;
    private static Cliente cliente;
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        GestorPersistencia gp = new GestorPersistencia();
        cafe = null;
        try{
            cafe = gp.cargarCafe();
            System.out.println("Datos cargados");
        } catch (Exception e){
            System.out.println("No se encontró cafe, creando uno nuevo...");
            Administrador admin = new Administrador("Admin", 30, 9999, new ArrayList<>(), 4545, "adminPro");
            cafe = new Café(50, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>(), new ArrayList<Juego>(), admin, new HashMap<>(), 1, 1, 9, 1, 0);
        }
        cafe.inicializarDatos();

        boolean salir = false;
        boolean auten = false;
        while(!salir){
            mostrarMenuInicial();
            int opcionI = leerInt("Ingrese una opcion: ");
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
                int opcionP = leerInt("Ingrese una opcion: ");
                switch (opcionP){
                    case 1:
                        hacerReserva();
                        break;
                    case 2:
                        solicitarPrestamo();
                        break;
                    case 3:
                        comprarProducto();
                        break;
                    case 4:
                        incribirTorneo();
                        break;
                    case 5:
                        desinscribirTorneo();
                        break;
                    case 6:
                        consultarPuntos();
                        break;
                    case 7:
                        verJuegosFavs();
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

    private static void crearCuenta(){

        ArrayList<Juego> juegos = new ArrayList<Juego>();
        ArrayList<Alergenos> a = new ArrayList<>();

        System.out.println("----- CREAR CUENTA CLIENTE -----");

        sc.nextLine();
        String nombre = leerLinea("Ingrese su Nombre Completo: ");
        int edad = leerInt("Ingrese su Edad: ");
        int cedula = leerInt("Ingrese su Numero de Cedula: ");
        int cantidadJ = leerInt("¿Cuantos Juegos Favoritos quiere añadir?: ");

        if (cantidadJ != 0) {
            cafe.mostrarCatalogoJuegos();
        }

        int i = 0;
        while(i < cantidadJ){
            int opcion = leerInt("Ingrese una opcion: ");

            if(opcion < 0 || opcion >= cafe.getCatalogoJuegos().size()){
                throw new MostrarException("Opcion invalida");
            }

            juegos.add(cafe.getCatalogoJuegos().get(opcion));
            i+=1;
        }
        int pass = leerInt("Ingrese una clave de 4 digitos: ");
        sc.nextLine();
        String usu = leerString("Ingrese un Usuario: ");
        int alergico = leerIntRango("¿Es alergico a algún alimento? (1. SI 2. NO): ", 1, 2);

        if(alergico != 1 && alergico != 2){
            throw new MostrarException("Opcion invalida");
        }

        if(alergico == 1){
            int cantA = leerInt("Ingrese la cantidad de alergenos: ");
            System.out.println("Alergenos Disponibles: ");
            for (Alergenos al : Alergenos.values()) {
                System.out.println(al.ordinal() + 1 + ". " + al.name());
            }
            int in = 0;
            while(in < cantA){
                System.out.println("Ingrese una opcion de alergeno: " );
                int opcionA = leerIntRango("Ingrese una opcion: ", 1, Alergenos.values().length);
                Alergenos sel = Alergenos.values()[opcionA - 1];
                a.add(sel);
                in += 1;
            }
        }

        Cliente cli = new Cliente(nombre, edad, cedula, juegos, pass, usu, new ArrayList<Venta>(), new ArrayList<Prestamo>(), a, 0.0,0.0);

        cafe.addCliente(cli);
        cliente = cli;
        System.out.println("Cuenta creada exitosamente");


    }

    private static boolean iniciarSesion(){

        System.out.println("----- LOGIN CLIENTE -----");
        String login = leerString("Ingrese su Usuario: ");
        int pass = leerInt("Ingrese su Clave: ");

        if(cafe.autenticarCliente(login, pass)== null){
            throw new AutenticacionException("Usuario no encontrado");
        }

        cliente = cafe.autenticarCliente(login, pass);
        return true;

    }

    private static void mostrarMenuInicial(){

        System.out.println("----- MENU CLIENTE INICIAL-----");
        System.out.println("1. Crear Cuenta");
        System.out.println("2. Iniciar Sesion");
        System.out.println("0. Salir");

    }

    private static void mostrarMenuPrincipal(){

        System.out.println("----- MENU CLIENTE PRINCIPAL-----");
        System.out.println("1. Hacer una Reserva");
        System.out.println("2. Solicitar Prestamo");
        System.out.println("3. Comprar Productos");
        System.out.println("4. Inscribirse a Torneo");
        System.out.println("5. Desinscribirse de Torneo");
        System.out.println("6. Consultar Puntos");
        System.out.println("7. Ver Juegos Favoritos");
        System.out.println("0. Salir");

    }

    private static void hacerReserva(){

        LocalDate fechaRes = null;
        Reserva res = null;
        System.out.println("----- HACER RESERVA-----");


        int dia = leerIntRango("Ingrese el Dia de la Reserva (1-31)", 1, 31);
        int mes = leerIntRango("Ingrese el Mes de la Reserva (1-12)", 1, 12);
        int anio = leerIntRango("Ingrese el año de la Reserva: ", LocalDate.now().getYear(), LocalDate.now().getYear() + 1);

        try{
            fechaRes = LocalDate.of(anio, mes, dia);
        }catch(DateTimeException e){
            System.out.println("Fecha invalida");
            return;
        }

        int cant = leerInt("Ingrese la cantidad de personas en la Reserva: ");
        int hora = leerIntRango("Ingrese la hora de la Reserva (hh): ", 0, 23);
        int min = leerIntRango("Ingrese los minutos de la hora de la Reserva (mm): ", 0, 59);
        sc.nextLine();

        LocalTime horaRes = LocalTime.of(hora, min);

        try {
            res = cafe.crearReserva(fechaRes, cliente, cant, horaRes);
            res.getMesaReserva().getPersonasSentadas().add(cliente);
        }catch (CapacidadExcedidaException e){
            System.out.println("No hay capacidad para la reserva");
            return;
        }


        int i = 1;

        while (i < cant + 1){
            String nombreA = leerLinea("Ingrese el nombre del acompañante " + i + " : ");
            int edadA = leerIntRango("Ingrese la edad del acompañante " + i + " : ", 1, 100);
            int cedulaA = leerInt("Ingrese el número de cedula del acompañante " + i + " : ");
            Cliente clin = new Cliente(nombreA, edadA, cedulaA, new ArrayList<>(), 0, null,
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0, 0.0);
            res.getMesaReserva().getPersonasSentadas().add(clin);
            i += 1;
        }

        System.out.println("Reserva creada exitosamente");


    }

    private static void solicitarPrestamo(){
        System.out.println("----- SOLICITAR PRESTAMO -----");

        boolean dispo = false;
        Mesa m = null;

        if(cafe.getMapaReservas().get(cliente.getCedula()) == null ||  cafe.getMapaReservas().get(cliente.getCedula()).size() == 0){
            throw new MostrarException("El cliente no tiene reservas activas");
        }

        if(cafe.getMapaReservas().get(cliente.getCedula()).size() > 0){
            for(Reserva res : cafe.getMapaReservas().get(cliente.getCedula())){
                if(res.getFechaReserva().isEqual(LocalDate.now())){
                    dispo = true;
                    m = res.getMesaReserva();
                    break;
                }
            }
        }

        if(!dispo){
            throw new MostrarException("El cliente no tiene reservas activas para el dia de hoy");
        }

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


        cafe.crearPrestamo(LocalDate.now(), fechaEnt, cafe.getCatalogoJuegos().get(opcionJ), cliente, cafe.getIdPrestamo(), m);
        System.out.println("Prestamo creado exitosamente");

    }

    private static void comprarProducto(){

        boolean dispo = false;
        Mesa m = null;

        if(cafe.getMapaReservas().get(cliente.getCedula()) == null ||
                cafe.getMapaReservas().get(cliente.getCedula()).size() == 0){
            throw new MostrarException("El cliente no tiene reservas activas");
        }

        if(cafe.getMapaReservas().get(cliente.getCedula()).size() > 0){
            for(Reserva res : cafe.getMapaReservas().get(cliente.getCedula())){
                if(res.getFechaReserva().isEqual(LocalDate.now())){
                    dispo = true;
                    m = res.getMesaReserva();
                    break;
                }
            }
        }

        if(!dispo){
            throw new MostrarException("El cliente no tiene reservas activas para el dia de hoy");
        }

        ArrayList<Item> items = new ArrayList<Item>();

        boolean salir = false;

        while(!salir){
            mostrarMenuProductos();
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

    public static ArrayList<Item> comprarJuegos(){

        ArrayList<Item> s = new ArrayList<>();

        System.out.println("----- COMPRAR JUEGOS -----");
        int cantJ = leerInt("Ingrese la cantidad del juegos a comprar: ");
        sc.nextLine();

        int i = 0;

        while (i<cantJ) {
            cafe.mostrarJuegosVenta();
            int opcionJ = leerIntRango("Ingrese una opcion: ", 0, cafe.getInventarioJuegosVenta().size() - 1);
            int units = leerIntRango("Ingrese la cantidad: ", 1, cafe.getInventarioJuegosVenta().get(opcionJ).getStock());

            if(opcionJ >= 0 && opcionJ < cafe.getInventarioJuegosVenta().size()){
                JuegoVenta jv = cafe.getInventarioJuegosVenta().get(opcionJ);
                s.add(new Item(units, jv));
                i += 1;

            }
        }
        return s;
    }

    public static ArrayList<Item> comprarPlatillos(){

        ArrayList<Item> p = new ArrayList<>();

        System.out.println("----- COMPRAR PLATILLOS -----");
        int cantP = leerInt("Ingrese la cantidad del patillos a comprar: ");
        sc.nextLine();

        int i = 0;

        while(i<cantP){
            cafe.mostrarMenu();
            int opcionP = leerIntRango("Ingrese una opcion: ", 0, cafe.getMenú().size() - 1);
            int units = leerInt("Ingrese la cantidad: ");

            if(opcionP >= 0 && opcionP < cafe.getMenú().size()){
                Platillos pla = cafe.getMenú().get(opcionP);
                p.add(new Item(units, pla));
                i += 1;
                }
            }

        return p;
        }


    public static void verVentaTotal(ArrayList<Item> items){
        System.out.println("----- VER TOTAL VENTA -----");

        if(items.isEmpty()){
            throw new VentaNoPermitidaException("No hay items para vender");
        }

        System.out.println("Los productos actualmente seleccionados son: ");
        mostrarProductos(items);
        int conf = leerIntRango("Confirme si esta correcto (1. SI 2. NO): ", 1, 2);
        boolean opcionPun = leerIntRango("¿Desea usar puntos? (1. SI 2. NO): ", 1, 2) == 1;
        boolean opcionCod = leerIntRango("¿Desea usar codigo descuento? (1. SI 2. NO): ", 1,2)== 1;

        if(conf == 2){
            return;
        }
        else if(conf == 1){
            Mesa m = null;
            ArrayList<Reserva> reservas = cafe.getMapaReservas().get(cliente.getCedula());
            if(reservas != null && reservas.size() > 0){
                for(Reserva res : cafe.getMapaReservas().get(cliente.getCedula())){
                    if(res.getFechaReserva().isEqual(LocalDate.now())){
                        m = res.getMesaReserva();
                        break;
                    }
                }

            }
            try{
                cafe.crearVenta(items, cliente, LocalDate.now(),m,opcionPun, opcionCod);
                System.out.println("Venta exitosa");
            } catch (VentaNoPermitidaException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public static void mostrarProductos(ArrayList<Item> items){

        for(Item i: items){
            String nom = "";
            Producto p = i.getProductoAsociado();
            if(p instanceof JuegoVenta){
                JuegoVenta jv = (JuegoVenta) p;
                nom = jv.getInfoJuegoVenta().getNombre();
            }
            else if(p instanceof Platillos){
                Platillos pla = (Platillos) p;
                nom = pla.getNombrePlatillo();
            }

            System.out.println(
                    "Nombre: " + nom + "\n"+
                    "Precio: " + p.getPrecio()+ "\n"+
                    "Cantidad: " + i.getCantidad()
                    );

        }

    }

    private static void incribirTorneo(){
        System.out.println("----- INSCRIBIRSE AL TORNEO -----");

        ArrayList<UsuarioComprador> usuarios = new ArrayList<>();
        usuarios.add(cliente);

        if (cafe.getTorneos().isEmpty()) {
            throw new TorneosException("No hay torneos disponibles");
        }

        cafe.mostrarTorneos();
        int opcionT = leerIntRango("Ingrese la opcion del torneo que desea inscribirse: ", 0, cafe.getTorneos().size() - 1);
        if(opcionT < 0 || opcionT >= cafe.getTorneos().size()){
            throw new TorneosException("Opcion invalida");
        }

        int cantUs = leerInt("Ingrese la cantidad de usuarios que desea inscribir: ");

        if(cantUs <= 2){
            int i = 0;
            while(i<cantUs){
                int tipo = leerIntRango("Es cliente o Empleado? (1. Cliente 2. Empleado): ", 1, 2);

                if(tipo == 1){
                    String login = leerString("Ingrese el login del cliente: ");
                    Cliente u = cafe.getMapaClientes().get(login);

                    if(u == null){
                        throw new TorneosException("El cliente no existe");
                    }

                    usuarios.add(u);
                }
                else if(tipo == 2){
                    String login = leerString("Ingrese el login del empleado: ");
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

        t.inscribir(cliente,usuarios);
        System.out.println("Inscripcion exitosa");


    }

    private static void desinscribirTorneo(){
        System.out.println("----- DESINSCRIBIRSE AL TORNEO -----");

        ArrayList<Torneo> tor = new ArrayList<>();
        Torneo torn = null;

        try{
            tor = cafe.mostrarTorneosCliente(cliente);
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


        int opcionT = leerIntRango("Ingrese la opcion del torneo que desea desinscribirse: ", 0, tor.size() - 1);
        torn = tor.get(opcionT);

        int opcionD = leerIntRango("¿Desea desinscribirse de torneo? (1. SI 2. NO): ", 1, 2);

        if (opcionD == 1) {
            try{
                torn.desinscribir(cliente);
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

    private static void consultarPuntos(){
        System.out.println("----- CONSULTA DE PUNTOS -----");
        System.out.println("Puntos Disponibles: "+ cliente.getPuntosFidelidad());
        return;
    }

    private static void verJuegosFavs() {
        System.out.println("----- VER JUEGOS FAVORITOS -----");

        if(cliente.getJuegosFav().isEmpty() || cliente.getJuegosFav() == null){
            System.out.println("El cliente no tiene juegos favoritos");
            return;
        }

        System.out.println("Juegos favoritos del cliente: ");
        for (Juego j : cliente.getJuegosFav()) {
            System.out.println(
                    "Nombre: " + j.getNombre() + "\n" +
                            "Año: " + j.getAnio() + "\n" +
                            "Empresa: " + j.getEmpresa() + "\n" +
                            "Cantidad de Jugadores: " + j.getCantidadJugadores() + "\n" +
                            "¿Es Dificil?: " + j.isEsDificil() + "\n" +
                            "Es Apto para: " + j.getApto() + "\n" +
                            "Tipo: " + j.getTipo() + "\n"
            );
        }
    }



    public static void mostrarMenuProductos(){
        System.out.println("----- MENU PRODUCTOS -----");
        System.out.println("1. Juegos");
        System.out.println("2. Platillos");
        System.out.println("3. Ver Venta Total");
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
