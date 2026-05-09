package main;

import articulos.Alergenos;
import articulos.Juego;
import exceptions.MostrarException;
import modelo.Café;
import modelo.Prestamo;
import modelo.Turno;
import modelo.Venta;
import persistencia.GestorPersistencia;
import sujetos.Cliente;
import sujetos.Cocinero;
import sujetos.Empleado;
import sujetos.Mesero;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

        if(){
            //crear menu principal
        }

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
        System.out.println("0. Salir");

    }

}
