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

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
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
            cafe = new Café(50, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>(), new ArrayList<Juego>(), null,new HashMap<>(), 1,1, 9, 1, 0);
            cafe.inicializarDatos();
        }

        System.out.println("----- LOGIN ADMIN -----");
        System.out.println("Usuario: ");
        String login = sc.next();
        System.out.println("Clave: ");
        int pass = sc.nextInt();

        if(cafe.autenticarAdmin(login, pass) != null){
            boolean exit = false;

            while (!exit) {
                mostrarMenu();
                int opcion = leerOpcion();

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

        System.out.println("Tipo de empleado (1. Mesero 2. Cocinero): ");
        int opcionTipo = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese nombre completo: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese edad: ");
        int edad = sc.nextInt();
        System.out.println("Ingrese la cedula: ");
        int cedula = sc.nextInt();
        System.out.println("Ingrese una contraseña: ");
        int pass = sc.nextInt();
        System.out.println("Ingrese un usuario: ");
        String login = sc.next();
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
        System.out.println("Cantidad de dias que va a trabajar:  ");
        int cantDias = sc.nextInt();
        int i = 0;
        System.out.println("Dias: 1. Domingo 2. Lunes 3. Martes 4. Miercoles 5. Jueves 6. Viernes 7. Sabado ");
        while(i < cantDias){
            System.out.println("Ingrese un dia (solo int): ");
            int dia = sc.nextInt();
            dias.add(DayOfWeek.of(dia));
            i += 1;
        }

        System.out.println("Ingrese la hora de entrada (hh):  ");
        int horaE = sc.nextInt();
        System.out.println("Ingrese la hora de salida (hh):  ");
        int horaS = sc.nextInt();

        LocalTime horaEntrada = LocalTime.of(horaE,00);
        LocalTime horaSalida = LocalTime.of(horaS,00);

        Turno turno = new Turno(dias, horaEntrada, horaSalida);

        return turno;

    }

    public static void registrarCliente(){
        sc.nextLine();
        System.out.println("Ingrese nombre completo: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese la cedula: ");
        int cedula = sc.nextInt();
        System.out.println("Ingrese una contraseña: ");
        int pass = sc.nextInt();
        System.out.println("Ingrese un usuario: ");
        String login = sc.next();

        Cliente cli = new Cliente(nombre, edad, cedula, new ArrayList<>(),pass, login, new ArrayList<Venta>(), new ArrayList<>(), new ArrayList<>(), 0.0, 0.0);

        cafe.addCliente(cli);

    }

    public static void verVentas(){

        mostrarMenuVentas();
        int opcionVentas = leerOpcion();

        if(opcionVentas<1 || opcionVentas>3) return;


        System.out.println("Ingrese el año: ");
        int anio = sc.nextInt();
        System.out.println("Ingrese el mes (1 - 12): ");
        int mes = sc.nextInt();
        System.out.println("Ingrese el dia (1 - 31): ");
        int dia = sc.nextInt();

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

        System.out.println("Ingrese tipo de Platillo (1. Bebida 2. Pasteleria): " );
        int opcionPla = sc.nextInt();

        System.out.println("Ingrese el precio de venta: " );
        int precio = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el nombre del Platillo: " );
        String nom = sc.nextLine();
        System.out.println("Cantidad de alergenos (int): " );
        int cantA = sc.nextInt();

        if (opcionPla == 2){
            System.out.println("Alergenos Disponibles: ");
            for (Alergenos al : Alergenos.values()) {
                System.out.println(al.ordinal() + 1 + ". " + al.name());
            }
            int i = 0;
            while(i < cantA){
                System.out.println("Ingrese una opcion de alergeno: " );
                int opcionA = sc.nextInt();
                Alergenos sel = Alergenos.values()[opcionA - 1];
                a.add(sel);
                i += 1;
            }
        }

        if(opcionPla == 1){
            System.out.println("¿Es alcoholica? : " );
            System.out.println("1. SI" );
            System.out.println("2. NO" );
            int opcionA = sc.nextInt();

            boolean esAlc = (opcionA == 1);

            System.out.println("¿Es una bebida caliente? : " );
            System.out.println("1. SI" );
            System.out.println("2. NO" );
            int opcionC = sc.nextInt();

            boolean esCal = (opcionC == 1);

            plat = new Bebida(precio, nom, a, esAlc, esCal);

        }
        else if(opcionPla == 2){

            plat = new Pasteleria(precio, nom, a);

        }

        if(plat != null){
            cafe.añadirPlatillo(plat);
        }
    }

    public static void  addJuegoPrestamo(){


        System.out.println("----- AÑADIR JUEGO PRESTAMO ----" );
        System.out.println("MOSTRANDO JUEGOS DISPONIBLES EN EL CATALOGO" );
        cafe.mostrarCatalogoJuegos();
        System.out.println("Seleccionar opcion de Juego Base: " );
        int opcionJB = sc.nextInt();

        System.out.println("Estados de Juego Disponibles: ");
        for (EstadoJuego ej : EstadoJuego.values()) {
            System.out.println(ej.ordinal() + 1 + ". " + ej.name());
        }

        System.out.println("Ingrese una opcion de estado de juego: " );
        int opcionA = sc.nextInt();
        EstadoJuego sel = EstadoJuego.values()[opcionA - 1];

        JuegoPrestamo jp = new JuegoPrestamo(true, false, sel, cafe.getCatalogoJuegos().get(opcionJB),0);
        cafe.añadirJuegoPrestamo(jp);

    }

    public static void addJuegoVenta(){

        System.out.println("----- AÑADIR JUEGO VENTA ----" );
        System.out.println("MOSTRANDO JUEGOS DISPONIBLES EN EL CATALOGO" );
        cafe.mostrarCatalogoJuegos();

        System.out.println("Seleccionar opcion de Juego Base: " );
        int opcionJB = sc.nextInt();

        System.out.println("Ingrese el Precio del Juego: " );
        int precio = sc.nextInt();

        System.out.println("Ingrese el Stock del Juego: " );
        int stock = sc.nextInt();

        JuegoVenta jv = new JuegoVenta(precio, stock, cafe.getCatalogoJuegos().get(opcionJB));
        cafe.añadirJuegoVenta(jv);

    }

    public static void moverVentaAPrestamoA(){

        System.out.println("----- MOVER JUEGO DE VENTA A PRESTAMO -----" );
        System.out.println("Los juegos disponibles actualmente en el inventario de venta:" );
        cafe.mostrarInventarioJuegosVenta();
        System.out.println("Seleccione un juego: " );
        int opcionJv = sc.nextInt();

        if(opcionJv >= cafe.getInventarioJuegosVenta().size()){
            throw new MostrarException("Opcion no valida");
        }

        cafe.moverVentaAPrestamo(cafe.getInventarioJuegosVenta().get(opcionJv));

    }

    public static void repararJuego(){

        System.out.println("----- REPARAR JUEGO PRESTAMO -----" );
        System.out.println("Los juegos disponibles actualmente en el inventario de prestamo:" );
        cafe.mostrarInventarioJuegosPrestamo();
        System.out.println("Seleccione un juego: " );
        int opcionJv = sc.nextInt();

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
        System.out.println("Ingrese un número de cedula valido (int): " );
        int opcionCed = sc.nextInt();

        cafe.mostrarComprasXUsuario(opcionCed);

    }

    public static void addMesa(){

        System.out.println("----- AGREGAR MESA AL CAFÉ -----" );
        System.out.println("Ingrese la capacidad de la nueva mesa: " );
        int cap = sc.nextInt();
        try{
            cafe.addMesa(cap);
        }catch (CapacidadExcedidaException e){
            System.out.println("No es posible añadir una mesa, no hay capacidad");
        }

    }

    public static void addJuego(){
        System.out.println("----- AGREGAR JUEGO AL CATALOGO -----" );
        System.out.print("Nombre del juego: ");
        String nombre = sc.nextLine();

        System.out.print("Año de publicación: ");
        int anio = sc.nextInt();

        System.out.print("Empresa: ");
        String empresa = sc.nextLine();

        System.out.print("Ingrese la cantidad de jugadores: ");
        int min = sc.nextInt();

        System.out.println("Categoría del juego:");
        for (TiposJuegos tj : TiposJuegos.values()) {
            System.out.println((tj.ordinal() + 1) + ". " + tj.name());
        }
        int opCat = sc.nextInt();
        TiposJuegos categoria = TiposJuegos.values()[opCat - 1];

        System.out.println("Restricción edad:");
        for (RestriccionEdad re : RestriccionEdad.values()) {
            System.out.println((re.ordinal() + 1) + ". " + re.name());
        }
        int opEdad = sc.nextInt();
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
        System.out.println("¿Desea aprobar todas las solicitudes (1. SI 2. NO)" );
        int opcionSol = sc.nextInt();

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
        System.out.println("Seleccione un Empleado Activo: " );
        int opcionEmp = sc.nextInt();

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

    public static int leerOpcion(){
        try{
            return sc.nextInt();
        } catch (Exception e){
            sc.next();
            return -1;
        }
    }
}


