package main;

import articulos.*;
import exceptions.AutenticacionException;
import exceptions.CapacidadExcedidaException;
import exceptions.JuegoNoEncontradoException;
import exceptions.MostrarException;
import modelo.*;
import persistencia.GestorPersistencia;
import sujetos.Empleado;
import sujetos.*;
import torneos.Amistoso;
import torneos.Competitivo;
import torneos.Torneo;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminConsola {

    private static Scanner sc = new Scanner(System.in);
    private static Café cafe;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
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

        System.out.println("----- LOGIN ADMIN -----");
        String login = leerString("Login: ");
        int pass = leerInt("Clave: ");

        if(cafe.autenticarAdmin(login, pass) != null){
            boolean exit = false;

            while (!exit) {
                mostrarMenu();
                int opcion = leerInt("");

                switch (opcion) {
                    case 1:
                        registrarEmpleado();
                        break;
                    case 2:
                        registrarCliente();
                        break;
                    case 3:
                        verVentas();
                        break;
                    case 4:
                        verJuegosPrestamo();
                        break;
                    case 5:
                        addPlatillo();
                        break;
                    case 6:
                        addJuegoPrestamo();
                        break;
                    case 7:
                        addJuegoVenta();
                        break;
                    case 8:
                        moverVentaAPrestamoA();
                        break;
                    case 9:
                        repararJuego();
                        break;
                    case 10:
                        ventasXCli();
                        break;
                    case 11:
                        addMesa();
                        break;
                    case 12:
                        addJuego();
                        break;
                    case 13:
                        gestionarSolicitudes();
                        break;
                    case 14:
                        modificarTurno();
                        break;
                    case 15:
                        marcarDesaparecidoJuego();
                        break;
                    case 16:
                        crearTorneo();
                        break;
                    case 17:
                        darPremioTorneo();
                        break;
                    case 0:
                    exit = true;
                    break;
                    default:
                        System.out.println("Opción Invalida");

                }

            }

            gp.guardarCafe(cafe);
            System.out.println("Datos Guardados Exitosamente");

        }
        else{
            throw new AutenticacionException("Acceso Denegado. Vuelva a Ingresar sus datos");
        }

    }

    private static void darPremioTorneo(){

        try{
            String login = leerString("Ingrese el login del usuario que hizo la inscripción: ");
            UsuarioComprador u = null;

            UsuarioComprador uc = cafe.getMapaClientes().get(login);

            if(uc == null){
                System.out.println(cafe.getMapaEmpleados().get(login));
                uc = cafe.getMapaEmpleados().get(login);
            }
            //if(uc == null){
            //    throw new AutenticacionException("Usuario no encontrado");
            //}

            cafe.mostrarTorneos();
            int opcionT = leerInt("Ingrese una opcion de torneo: ");
            Torneo t = cafe.getTorneos().get(opcionT);

            ArrayList<UsuarioComprador> participantes = t.getInscripciones().get(uc.getCedula());

            if(participantes != null){
                System.out.println("El grupo de participantes inscritos en el torneo es: ");
                int i = 0;
                for(UsuarioComprador ucc : participantes){
                    System.out.println(
                            "Opcion: " + i + "\n" +
                            "Nombre: " + ucc.getNombre() + "\n" +
                             "Cedula: " + ucc.getCedula()
                    );
                }
                int opcionU = leerIntRango("Ingrese el numero de la opcion del grupo de participantes que desea dar premio: ", 0, participantes.size() - 1);
                u = participantes.get(opcionU);

            }

            if(t instanceof Amistoso){
                t.darPremioDescuento(u);
                System.out.println("Se dio el premio con exito");
            }
            else if(t instanceof Competitivo){
                t.darPremioDinero(u);
                System.out.println("Se dio el premio con exito");
            }
        } catch (Exception e){
            System.out.println("Error al dar premio: " + e.getMessage());
        }

    }

    private static void mostrarMenu(){
        System.out.println("----- MENU ADMIN -----");
        System.out.println("1. Registrar Empleado");
        System.out.println("2. Registrar Cliente");
        System.out.println("3. Ver Ventas");
        System.out.println("4. Ver Juegos Prestamo");
        System.out.println("5. Añadir Platillos");
        System.out.println("6. Añadir Juego Prestamo");
        System.out.println("7. Añadir Juego Venta");
        System.out.println("8. Mover Juego de Venta a Prestamo");
        System.out.println("9. Reparar Juego");
        System.out.println("10. Ver Ventas por Cliente");
        System.out.println("11. Añadir Mesa");
        System.out.println("12. Añadir Juego a Catalogo");
        System.out.println("13. Gestionar Solicitudes");
        System.out.println("14. Modificar Turno");
        System.out.println("15. Marcar Desaparecido Juego");
        System.out.println("16. Crear Torneo");
        System.out.println("17. Dar Premio Torneo");
        System.out.println("0. Salir y Guardar");
    }

    private static void mostrarMenuVentas(){
        System.out.println("----- MENU VENTAS ------");
        System.out.println("1. Diarias");
        System.out.println("2. Semanales");
        System.out.println("3. Mensuales");
    }

    private static void registrarEmpleado(){

        Empleado emp = null;

        int opcionTipo = leerIntRango("Tipo de empleado (1. Mesero 2. Cocinero): ", 1, 2);

        String nombre = leerLinea("Ingrese nombre completo: ");
        int edad = leerInt("Ingrese la edad: ");
        int cedula = leerInt("Ingrese la cedula: ");
        int pass = leerInt("Ingrese una contraseña: ");
        String login = leerString("Ingrese un usuario: ");
        Turno turno = pedirDatosTurno();


        if(opcionTipo == 1){
            emp = new Mesero(nombre, edad, cedula, new ArrayList<>(), pass, login, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0,0.0,turno, new ArrayList<>());
        }
        if(opcionTipo == 2){
            emp = new Cocinero(nombre, edad, cedula, new ArrayList<>(), pass, login, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0,0.0,turno);
        }

        cafe.addEmpleado(emp);
    }

    private static Turno pedirDatosTurno(){

        ArrayList<DayOfWeek> dias = new ArrayList<>();
        System.out.println("----- SETUP TURNO -----");
        int cantDias = leerInt("Cantidad de dias que va a trabajar: ");
        int i = 0;
        System.out.println("Dias: 1. Lunes 2. Martes 3. Miercoles 4. Jueves 5. Viernes 6. Sabado 7. Domingo ");
        while(i < cantDias){
            int dia = leerInt("Ingrese un dia (solo int): ");
            dias.add(DayOfWeek.of(dia));
            i += 1;
        }

        int horaE = leerInt("Ingrese la hora de entrada (hh):  ");
        int horaS = leerInt("Ingrese la hora de salida (hh):  ");

        LocalTime horaEntrada = LocalTime.of(horaE,00);
        LocalTime horaSalida = LocalTime.of(horaS,00);

        Turno turno = new Turno(dias, horaEntrada, horaSalida);

        return turno;

    }

    public static void registrarCliente(){
        sc.nextLine();
        String nombre = leerLinea("Ingrese nombre completo: ");
        int edad = leerIntRango("Ingrese la edad: ", 1, 100);
        sc.nextLine();
        int cedula = leerInt("Ingrese la cedula: ");
        int pass = leerInt("Ingrese la contraseña: ");
        String login = leerString("Ingrese un usuario: ");

        Cliente cli = new Cliente(nombre, edad, cedula, new ArrayList<>(),pass, login, new ArrayList<Venta>(), new ArrayList<>(), new ArrayList<>(), 0.0, 0.0);

        cafe.addCliente(cli);

    }

    public static void verVentas(){

        mostrarMenuVentas();
        int opcionVentas = leerInt("");

        if(opcionVentas<1 || opcionVentas>3) return;


        int anio = leerIntRango("Ingrese el año: ", 2020, LocalDate.now().getYear());
        System.out.println("Ingrese el mes (1 - 12): ");
        int mes = leerIntRango("Ingrese el mes: ", 1, 12);
        int dia = leerIntRango("Ingrese el dia: ", 1, 31);

        LocalDate fecha = LocalDate.of(anio, mes, dia);

        switch(opcionVentas){
            case 1:
                cafe.revisarVentasDia(fecha);
                break;
            case 2:
                cafe.revisarVentasSemana(fecha);
                break;
            case 3:
                cafe.revisarVentasMes(fecha);
                break;
        }

    }

    public static void verJuegosPrestamo(){


        System.out.println("----- JUEGOS PRESTAMOS -----");
        cafe.revisarJuegos();

    }

    public static void addPlatillo(){

        ArrayList<Alergenos> a = new ArrayList<>();
        Platillos plat = null;

        int opcionPla = leerIntRango("Ingrese tipo de Platillo (1. Bebida 2. Pasteleria): ", 1, 2);

        int precio = leerInt("Ingrese el precio: ");
        sc.nextLine();
        String nom = leerLinea("Nombre: ");
        int cantA = leerInt("Cantidad: ");

        if (opcionPla == 2){
            System.out.println("Alergenos Disponibles: ");
            for (Alergenos al : Alergenos.values()) {
                System.out.println(al.ordinal() + 1 + ". " + al.name());
            }
            int i = 0;
            while(i < cantA){
                int opcionA = leerIntRango("Ingrese una opcion: ", 0, Alergenos.values().length);
                Alergenos sel = Alergenos.values()[opcionA - 1];
                a.add(sel);
                i += 1;
            }
        }

        if(opcionPla == 1){
            System.out.println("¿Es alcoholica?: " );
            System.out.println("1. SI" );
            System.out.println("2. NO" );
            int opcionA = leerIntRango("Ingrese una opcion: ", 1, 2);

            boolean esAlc = (opcionA == 1);

            System.out.println("¿Es una bebida caliente? : " );
            System.out.println("1. SI" );
            System.out.println("2. NO" );
            int opcionC = leerIntRango("Ingrese una opcion: ", 1, 2);

            boolean esCal = (opcionC == 1);

            plat = new Bebida(precio, nom, a, esAlc, esCal);

        }
        else if(opcionPla == 2){

            plat = new Pasteleria(precio, nom, a);

        }

        if(plat != null){
            cafe.añadirPlatillo(plat);
            System.out.println("Platillo agregado con exito");
        }
    }

    public static void  addJuegoPrestamo(){


        System.out.println("----- AÑADIR JUEGO PRESTAMO ----" );
        System.out.println("MOSTRANDO JUEGOS DISPONIBLES EN EL CATALOGO" );
        cafe.mostrarCatalogoJuegos();
        int opcionJB = leerIntRango("Ingrese una opcion: ", 0, cafe.getCatalogoJuegos().size() - 1);

        System.out.println("Estados de Juego Disponibles: ");
        for (EstadoJuego ej : EstadoJuego.values()) {
            System.out.println(ej.ordinal() + 1 + ". " + ej.name());
        }

        int opcionA = leerIntRango("Ingrese una opcion: ", 0, EstadoJuego.values().length);
        EstadoJuego sel = EstadoJuego.values()[opcionA - 1];

        JuegoPrestamo jp = new JuegoPrestamo(true, false, sel, cafe.getCatalogoJuegos().get(opcionJB),0);
        cafe.añadirJuegoPrestamo(jp);

    }

    public static void addJuegoVenta(){

        System.out.println("----- AÑADIR JUEGO VENTA ----" );
        System.out.println("MOSTRANDO JUEGOS DISPONIBLES EN EL CATALOGO" );
        cafe.mostrarCatalogoJuegos();

        int opcionJB = leerIntRango("Ingrese una opcion: ", 0, cafe.getCatalogoJuegos().size() - 1);

        int precio = leerInt("Ingrese el precio: ");
        int stock = leerInt("Ingrese el stock: ");

        JuegoVenta jv = new JuegoVenta(precio, stock, cafe.getCatalogoJuegos().get(opcionJB));
        cafe.añadirJuegoVenta(jv);

    }

    public static void moverVentaAPrestamoA(){

        System.out.println("----- MOVER JUEGO DE VENTA A PRESTAMO -----" );
        System.out.println("Los juegos disponibles actualmente en el inventario de venta:" );
        cafe.mostrarInventarioJuegosVenta();
        int opcionJv = leerIntRango("Ingrese una opcion: ", 0, cafe.getInventarioJuegosVenta().size() - 1);

        if(opcionJv >= cafe.getInventarioJuegosVenta().size()){
            throw new MostrarException("Opcion no valida");
        }

        cafe.moverVentaAPrestamo(cafe.getInventarioJuegosVenta().get(opcionJv));

    }

    public static void repararJuego(){

        System.out.println("----- REPARAR JUEGO PRESTAMO -----" );
        System.out.println("Los juegos disponibles actualmente en el inventario de prestamo:" );
        cafe.mostrarInventarioJuegosPrestamo();
        int opcionJv = leerIntRango("Ingrese una opcion: ", 0, cafe.getInventarioJuegosPrestamo().size() - 1);

        if(opcionJv >= 0 && opcionJv < cafe.getInventarioJuegosPrestamo().size() ){
            try{
                cafe.repararJuegoPrestamo(cafe.getInventarioJuegosPrestamo().get(opcionJv));
            } catch (JuegoNoEncontradoException e){
                System.out.println("Juego No Encontrado");
            }
        }
    }


    public static void ventasXCli(){
        System.out.println("----- CONSULTAR VENTAS POR CLIENTES -----" );
        int opcionCed = leerInt("Ingrese una cedula: ");

        cafe.mostrarComprasXUsuario(opcionCed);

    }

    public static void addMesa(){

        System.out.println("----- AGREGAR MESA AL CAFÉ -----" );
        int cap = leerInt("Ingrese la capacidad: ");
        try{
            cafe.addMesa(cap);
        }catch (CapacidadExcedidaException e){
            System.out.println("No es posible añadir una mesa, no hay capacidad");
        }

    }

    public static void addJuego() {
        System.out.println("----- AGREGAR JUEGO AL CATALOGO -----");
        String nombre = leerLinea("Ingrese el nombre del juego: ");

        int anio = leerIntRango("Ingrese el año de publicación: ", 1900, 2026);
        String empresa = leerString("Ingrese el nombre de la empresa: ");
        int min = leerInt("Ingrese la cantidad de jugadores: ");

        System.out.println("Categoría del juego:");
        for (TiposJuegos tj : TiposJuegos.values()) {
            System.out.println((tj.ordinal() + 1) + ". " + tj.name());
        }
        int opCat = leerIntRango("Ingrese una opcion: ", 1, TiposJuegos.values().length);
        TiposJuegos categoria = TiposJuegos.values()[opCat - 1];

        System.out.println("Restricción edad:");
        for (RestriccionEdad re : RestriccionEdad.values()) {
            System.out.println((re.ordinal() + 1) + ". " + re.name());
        }
        int opEdad = leerIntRango("Ingrese una opcion: ", 1, RestriccionEdad.values().length);
        RestriccionEdad edad = RestriccionEdad.values()[opEdad - 1];

        System.out.print("¿Es Dificil? (1. SI 2. NO): ");
        boolean esDificil = (sc.nextInt() == 1);

        Juego nuevoJuego = new Juego(nombre, anio, empresa, min, esDificil, edad, categoria);

        cafe.añadirJuego(nuevoJuego);

    }

    public static void gestionarSolicitudes(){
        System.out.println("----- GESTIONAR SOLICITUDES -----" );
        System.out.println("Solicitudes pendientes: " );
        cafe.mostrarSolicitudes();
        int opcionSol = leerIntRango("Ingrese una opcion: ", 1, 2);

        if(opcionSol == 1){
            cafe.aprobarSolcitudesPendientes();
        }
        else if(opcionSol == 2){
            return;
        }
        else{
            System.out.println("Opción no válida. No se realizará ninguna acción.");
        }
    }

    public static void modificarTurno(){

        System.out.println("----- MODIFICAR TURNOS -----" );
        System.out.println("Empleados Activos: " );
        cafe.mostrarEmpleados();
        int opcionEmp = leerIntRango("Ingrese una opcion: ", 0, cafe.getEmpleados().size() - 1);

        if(opcionEmp < 0 || opcionEmp >= cafe.getEmpleados().size() - 1){
            throw new MostrarException("Opción no válida.");
        }

        Empleado emp = cafe.getEmpleados().get(opcionEmp);

        Turno tur = pedirDatosTurno();

        CambioTurno ct = new CambioTurno(0, null, null, null, tur);

        if(cafe.validarCambioTurno(ct)){
            cafe.cambiarTurno(tur, emp);
            System.out.println("Cambio Exitoso.");
        }
        else{
            throw new MostrarException("No se pudo realizar el cambio de turno.");
        }
    }

    public static void marcarDesaparecidoJuego(){
        System.out.println("----- MARCAR DESAPARECIDO JUEGO -----" );

        System.out.println("Se validarán los prestamos actuales" );
        cafe.revisarPrestamos();

    }

    public static void  crearTorneo() {
        int precio = 0;
        System.out.println("----- CREAR TORNEO -----");
        sc.nextLine();
        String nombre = leerLinea("Nombre: ");

        System.out.println("Ingrese el dia que se va a hacer el torneo: ");
        int opcionD = leerIntRango("Ingrese una opcion: ", 1, 7);

        DayOfWeek dia = DayOfWeek.of(opcionD);
        Juego juego = null;

        System.out.println("Ingrese la cantidad de participantes: ");
        int cantPart = sc.nextInt();
        cafe.mostrarCatalogoJuegos();

        int opcion = leerIntRango("Ingrese una opcion: ", 0, cafe.getCatalogoJuegos().size() - 1);
        if (opcion < 0 || opcion >= cafe.getCatalogoJuegos().size()) {
            throw new MostrarException("Opcion invalida");
        }

        juego = (cafe.getCatalogoJuegos().get(opcion));

        System.out.println("¿El torneo es competitivo? (1. SI 2. NO) : " );
        boolean esComp = (sc.nextInt() == 1);

        if (esComp) {
            precio = leerInt("Precio: ");
            sc.nextLine();
        }

        try {
            cafe.crearTorneo(dia, nombre, cantPart, juego, precio, esComp);
            System.out.println("Torneo creado exitosamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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


