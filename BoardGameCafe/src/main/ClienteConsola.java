package main;

import articulos.*;
import com.sun.security.jgss.AuthorizationDataEntry;
import exceptions.AutenticacionException;
import exceptions.CapacidadExcedidaException;
import exceptions.MostrarException;
import exceptions.VentaNoPermitidaException;
import modelo.*;
import persistencia.GestorPersistencia;
import sujetos.Cliente;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
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
            cafe = new Café(50, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>(), new ArrayList<Juego>(), null, new HashMap<>(), 1,1, 9, 1, 0);
            cafe.inicializarDatos();
        }



        boolean salir = false;
        boolean auten = false;
        while(!salir){
            mostrarMenuInicial();
            int opcionI = sc.nextInt();
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
                int opcionP = sc.nextInt();
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
                        //incribirTorneo();
                        break;
                    case 5:
                        //desinscribirTorneo();
                        break;
                    case 6:
                        //consultarPuntos();
                        break;
                    case 7:
                        //verJuegosFavs();
                        break;
                    case 0:
                        salir = true;
                        break;
                        default:
                        System.out.println("Opción Invalida");
                }
            }
        }



    }

    private static void crearCuenta(){

        ArrayList<Juego> juegos = new ArrayList<Juego>();
        ArrayList<Alergenos> a = new ArrayList<>();

        System.out.println("----- CREAR CUENTA-----");

        sc.nextLine();
        System.out.println("Ingrese su Nombre Completo: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su Edad: ");
        int edad = sc.nextInt();
        System.out.println("Ingrese su Numero de Cedula: ");
        int cedula = sc.nextInt();
        System.out.println("¿Cuantos Juegos Favoritos quiere añadir?: ");
        int cantidadJ = sc.nextInt();
        cafe.mostrarCatalogoJuegos();
        int i = 0;
        while(i < cantidadJ){
            System.out.println("Ingrese una opcion: ");
            int opcion = sc.nextInt();

            if(opcion < 0 || opcion >= cafe.getCatalogoJuegos().size()){
                throw new MostrarException("Opcion invalida");
            }

            juegos.add(cafe.getCatalogoJuegos().get(opcion));
            i+=1;
        }
        System.out.println("Ingrese una Contraseña: ");
        int pass = sc.nextInt();
        System.out.println("Ingrese un Usuario: ");
        sc.nextLine();
        String usu = sc.nextLine();
        System.out.println("¿Es alergico a algún alimento? (1. SI 2. NO): ");
        int alergico = sc.nextInt();

        if(alergico != 1 && alergico != 2){
            throw new MostrarException("Opcion invalida");
        }

        if(alergico == 1){
            System.out.println("Cantidad de alergenos (int): " );
            int cantA = sc.nextInt();
            System.out.println("Alergenos Disponibles: ");
            for (Alergenos al : Alergenos.values()) {
                System.out.println(al.ordinal() + 1 + ". " + al.name());
            }
            int in = 0;
            while(in < cantA){
                System.out.println("Ingrese una opcion de alergeno: " );
                int opcionA = sc.nextInt();
                Alergenos sel = Alergenos.values()[opcionA - 1];
                a.add(sel);
                in += 1;
            }
        }

        Cliente cli = new Cliente(nombre, edad, cedula, juegos, pass, usu, new ArrayList<Venta>(), new ArrayList<Prestamo>(), a, 0.0,0.0);

        cafe.addCliente(cli);
        cliente = cli;
        System.out.println("Cuenta creada exitosamente!");


    }

    private static boolean iniciarSesion(){

        System.out.println("----- LOGIN CLIENTE -----");
        System.out.println("Usuario: ");
        String login = sc.next();
        System.out.println("Clave: ");
        int pass = sc.nextInt();

        if(cafe.autenticarCliente(login, pass)== null){
            throw new AutenticacionException("Usuario no encontrado");
        }

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
        System.out.println("3. Comprar Producto");
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

        System.out.println("Ingrese el Dia de la Reserva (1-31): ");
        int dia = sc.nextInt();
        System.out.println("Ingrese el Mes de la Reserva (1-12): ");
        int mes = sc.nextInt();
        System.out.println("Ingrese dia de la Reserva: ");
        int anio = sc.nextInt();

        try{
            fechaRes = LocalDate.of(anio, mes, dia);
        }catch(DateTimeException e){
            System.out.println("Fecha invalida");
            return;
        }

        System.out.println("Ingrese la cantidad de personas en la Reserva: ");
        int cant = sc.nextInt();
        System.out.println("Ingrese la hora de la Reserva (hh): ");
        int hora = sc.nextInt();
        System.out.println("Ingrese los minutos de la hora de la Reserva (mm): ");
        int min = sc.nextInt();
        sc.nextLine();

        LocalTime horaRes = LocalTime.of(hora, min);

        try {
            res = cafe.crearReserva(fechaRes, cliente, cant, horaRes);
        }catch (CapacidadExcedidaException e){
            System.out.println("No hay capacidad para la reserva");
            return;
        }


        int i = 1;

        while (i < cant + 1){
            System.out.println("Ingrese el nombre del acompañante " + i + " : ");
            String nombreA = sc.nextLine();
            System.out.println("Ingrese la edad del acompañante " + i + " : ");
            int edadA = sc.nextInt();
            System.out.println("Ingrese el número de cedula del acompañante " + i + " : ");
            int cedulaA = sc.nextInt();
            Cliente clin = new Cliente(nombreA, edadA, cedulaA, null, 0, null, null, null, null, 0.0, 0.0);
            res.getMesaReserva().getPersonasSentadas().add(clin);
            i += 1;
        }

    }

    private static void solicitarPrestamo(){
        System.out.println("----- SOLICITAR PRESTAMO -----");

        boolean dispo = false;
        Mesa m = null;

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
        System.out.println("Selecione un Juego del catalogo: ");
        int opcionJ = sc.nextInt();
        System.out.println("Ingrese el dia de la entrega del juego: ");
        int diaEnt = sc.nextInt();
        System.out.println("Ingrese el mes de la entrega del juego: ");
        int mesEnt = sc.nextInt();
        System.out.println("Ingrese el anio de la entrega del juego: ");
        int anioEnt = sc.nextInt();

        LocalDate fechaEnt = null;
        try{
            fechaEnt = LocalDate.of(anioEnt, mesEnt, diaEnt);
        }catch(DateTimeException e){
            System.out.println("Fecha invalida");
            return;
        }


        cafe.crearPrestamo(LocalDate.now(), fechaEnt, cafe.getCatalogoJuegos().get(opcionJ), cliente, cafe.getIdPrestamo(), m);

    }

    private static void comprarProducto(){

        boolean dispo = false;
        Mesa m = null;

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
            System.out.println("Ingrese una opcion: ");
            int opcionP = sc.nextInt();
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

    private static ArrayList<Item> comprarJuegos(){

        ArrayList<Item> s = new ArrayList<>();

        System.out.println("----- COMPRAR JUEGOS -----");
        System.out.println("Ingrese la cantidad del juegos a comprar: ");
        int cantJ = sc.nextInt();
        sc.nextLine();

        int i = 0;

        while (i<cantJ) {
            cafe.mostrarJuegosVenta();
            System.out.println("Ingrese una opcion: ");
            int opcionJ = sc.nextInt();

            System.out.println("¿Cuantas unidades de este juego desea comprar?: ");
            int units = sc.nextInt();

            if(opcionJ >= 0 && opcionJ < cafe.getInventarioJuegosVenta().size()){
                JuegoVenta jv = cafe.getInventarioJuegosVenta().get(opcionJ);
                s.add(new Item(units, jv));
                i += 1;

            }
        }
        return s;
    }

    private static ArrayList<Item> comprarPlatillos(){

        ArrayList<Item> p = new ArrayList<>();

        System.out.println("----- COMPRAR PLATILLOS -----");
        System.out.println("Ingrese la cantidad del patillos a comprar: ");
        int cantP = sc.nextInt();
        sc.nextLine();

        int i = 0;

        while(i<cantP){
            cafe.mostrarMenu();
            System.out.println("Ingrese una opcion: ");
            int opcionP = sc.nextInt();
            System.out.println("¿Cuantas unidades de este platillo desea comprar?: ");
            int units = sc.nextInt();

            if(opcionP >= 0 && opcionP < cafe.getMenú().size()){
                Platillos pla = cafe.getMenú().get(opcionP);
                p.add(new Item(units, pla));
                i += 1;
                }
            }

        return p;
        }


    private static void verVentaTotal(ArrayList<Item> items){
        System.out.println("----- VER TOTAL VENTA -----");

        if(items.isEmpty()){
            throw new VentaNoPermitidaException("No hay items para vender");
        }

        System.out.println("Los productos actualmente seleccionados son: ");
        mostrarProductos(items);
        System.out.println("Confirme si esta correcto (1. SI 2. NO): ");
        int conf = sc.nextInt();
        System.out.println("¿Desea usar puntos? (1. SI 2. NO): ");
        boolean opcionPun = (sc.nextInt() == 1);
        System.out.println("¿Desea usar codigo descuento? (1. SI 2. NO): ");
        boolean opcionCod = (sc.nextInt() == 1);

        if(conf == 2){
            return;
        }
        else if(conf == 1){
            Mesa m = null;
            ArrayList<Reserva> reservas = cafe.getMapaReservas().get(cliente.getCedula());
            if(reservas.size() > 0 && reservas != null){
                for(Reserva res : cafe.getMapaReservas().get(cliente.getCedula())){
                    if(res.getFechaReserva().isEqual(LocalDate.now())){
                        m = res.getMesaReserva();
                        break;
                    }
                }

            }
            try{
                cafe.crearVenta(items, cliente, LocalDate.now(),m,opcionPun, opcionCod);
            } catch (VentaNoPermitidaException e){
                System.out.println(e.getMessage());
            }
        }

    }

    private static void mostrarProductos(ArrayList<Item> items){

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


    private static void mostrarMenuProductos(){
        System.out.println("----- MENU PRODUCTOS -----");
        System.out.println("1. Juegos");
        System.out.println("2. Platillos");
        System.out.println("3. Ver Venta Total");
        System.out.println("0. Salir");
    }

}
