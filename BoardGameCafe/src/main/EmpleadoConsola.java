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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
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

    private static void comprarProducto(){
        ArrayList<Item> items = new ArrayList<Item>();

        boolean salir = false;

        while(!salir){
            ClienteConsola.mostrarMenuProductos();
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
            try{
                cafe.crearVenta(items, empleado, LocalDate.now(),null,opcionPun, opcionCod);
            } catch (VentaNoPermitidaException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean iniciarSesion(){

        System.out.println("----- LOGIN EMPLEADO -----");
        System.out.println("Usuario: ");
        String login = sc.next();
        System.out.println("Clave: ");
        int pass = sc.nextInt();

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
        int opcionT = sc.nextInt();
        torn = tor.get(opcionT);

        System.out.println("¿Desea desinscribirse de torneo? (1. SI 2. NO): ");
        int opcionD = sc.nextInt();

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
        System.out.println("Ingrese la opcion del torneo que desea inscribirse: ");
        int opcionT = sc.nextInt();
        if(opcionT < 0 || opcionT >= cafe.getTorneos().size()){
            throw new TorneosException("Opcion invalida");
        }

        System.out.println("Ingrese la cantidad de usuarios que desea inscribir: ");
        int cantUs = sc.nextInt();

        if(cantUs <= 2){
            int i = 0;
            while(i<cantUs){
                System.out.println("Es cliente o Empleado? (1. Cliente 2. Empleado): )");
                int tipo = sc.nextInt();

                if(tipo == 1){
                    System.out.println("Ingrese el login del cliente: )");
                    String login = sc.next();
                    Cliente u = cafe.getMapaClientes().get(login);

                    if(u == null){
                        throw new TorneosException("El cliente no existe");
                    }

                    usuarios.add(u);
                }
                else if(tipo == 2){
                    System.out.println("Ingrese el login del empleado: )");
                    String login = sc.next();
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

        System.out.println("Ingrese el login del empleado que desea intercambiar: ");
        String login = sc.nextLine();

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
            System.out.println("¿Es? alcoholica : " );
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

        cafe.crearSugerenciaPlatillo(empleado, plat);
        System.out.println("Sugerencia creada exitosamente" );

    }

    private static void solicitarCambioTurno(){
        System.out.println("----- SOLICITAR CAMBIO TURNO -----");

        ArrayList<DayOfWeek> days = new ArrayList<>();
        System.out.println("--- DATOS PARA EL NUEVO TURNO ---" );

        System.out.println("¿Cuantos dias trabajará?: " );
        int dias = sc.nextInt();

        if(dias < 0 || dias > 7){
            throw new MostrarException("Opcion invalida");
        }

        int w = 0;

        while(w < dias){
            System.out.println("Ingrese una opcion de dia de la semana: " );
            System.out.println("Dias: 1. Lunes 2. Martes 3. Miercoles 4. Jueves 5. Viernes 6. Sabado 7. Domingo ");

            int opcionD = sc.nextInt();

            if(opcionD < 1 || opcionD > 7){
                throw new MostrarException("Opcion invalida");
            }

            days.add(DayOfWeek.of(opcionD));
            w += 1;
        }

        System.out.println("Ingrese la hora de inicio de trabajo (hh): " );
        int horaE = sc.nextInt();
        System.out.println("Ingrese los minutos de inicio de trabajo (mm): " );
        int minE = sc.nextInt();
        System.out.println("Ingrese la hora de fin de trabajo (hh): " );
        int horaF = sc.nextInt();
        System.out.println("Ingrese los minutos de fin de trabajo (mm): " );
        int minF = sc.nextInt();

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
        System.out.println("Ingrese su Nombre Completo: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su Edad: ");
        int edad = sc.nextInt();
        System.out.println("Ingrese su Numero de Cedula: ");
        int cedula = sc.nextInt();
        System.out.println("¿Cuantos Juegos Favoritos quiere añadir?: ");
        int cantidadJ = sc.nextInt();

        if (cantidadJ != 0) {
            cafe.mostrarCatalogoJuegos();
        }

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

        System.out.println("Ingrese el tipo de empleado (1. Mesero 2. Cocinero): " );
        int tipo = sc.nextInt();

        System.out.println("¿Cuantos dias trabajará?: " );
        int dias = sc.nextInt();

        if(dias < 0 || dias > 7){
            throw new MostrarException("Opcion invalida");
        }

        int w = 0;

        while(w < dias){
            System.out.println("Ingrese una opcion de dia de la semana: " );
            System.out.println("Dias: 1. Lunes 2. Martes 3. Miercoles 4. Jueves 5. Viernes 6. Sabado 7. Domingo ");

            int opcionD = sc.nextInt();

            if(opcionD < 1 || opcionD > 7){
                throw new MostrarException("Opcion invalida");
            }

            days.add(DayOfWeek.of(opcionD));
            w += 1;
        }

        System.out.println("Ingrese la hora de inicio de trabajo (hh): " );
        int horaE = sc.nextInt();
        System.out.println("Ingrese los minutos de inicio de trabajo (mm): " );
        int minE = sc.nextInt();
        System.out.println("Ingrese la hora de fin de trabajo (hh): " );
        int horaF = sc.nextInt();
        System.out.println("Ingrese los minutos de fin de trabajo (mm): " );
        int minF = sc.nextInt();

        LocalTime horaEntrada = LocalTime.of(horaE, minE);
        LocalTime horaSalida = LocalTime.of(horaF, minF);

        Turno turno = new Turno(days, horaEntrada, horaSalida);

        if(tipo == 1){
            System.out.println("¿Cuantos juegos sabe enseñar?");
            int numJ = sc.nextInt();
            if(numJ > 0){
                cafe.mostrarCatalogoJuegos();
                int t = 0;
                while(t < numJ){
                    System.out.println("Ingrese una opcion: ");
                    int opcionJ = sc.nextInt();
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
        System.out.println("0. Salir");

    }

}
