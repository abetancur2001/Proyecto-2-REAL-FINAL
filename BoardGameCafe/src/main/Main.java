package main;

import modelo.*;
import sujetos.*;
import articulos.*;
import persistencia.*;

import java.util.*;
import java.io.IOException;
import java.time.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        GestorPersistencia gp = new GestorPersistencia();
        Café cafe;

        // 🔹 CARGAR / CREAR
        try {
            cafe = gp.cargarCafe();
            System.out.println("Café cargado");
        } catch (Exception e) {
            cafe = new Café(
                50,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new HashMap<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                0,0
            );

            inicializarDatos(cafe);
            gp.guardarCafe(cafe);
            System.out.println("Café nuevo creado");
        }

        ArrayList<UsuarioComprador> usuarios = cafe.getUsuarios();
        Reserva ultimaReserva = null;

        boolean run = true;

        while (run) {

            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Crear usuario");
            System.out.println("2. Ver usuarios");
            System.out.println("3. Crear reserva");
            System.out.println("4. Crear préstamo");
            System.out.println("5. Crear venta");
            System.out.println("6. Guardar");
            System.out.println("7. Ver Ventas");
            System.out.println("8. Salir");

            int op;
            try {
                op = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Entrada inválida");
                continue;
            }

            switch (op) {

                case 1:
                    System.out.println("Nombre:");
                    String nombre = sc.next();
                    System.out.println("Edad:");
                    int edad = sc.nextInt();
                    System.out.println("Cedula:");
                    int cedula = sc.nextInt();

                    Cliente nuevo = new Cliente(
                            nombre, edad, cedula,
                            new ArrayList<>(), 1234, nombre,
                            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0
                    );

                    usuarios.add(nuevo);
                    cafe.setUsuarios(usuarios);
                    System.out.println("Usuario creado");
                    break;

                case 2:
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios");
                    } else {
                        for (int i = 0; i < usuarios.size(); i++) {
                            System.out.println(i + ". " + usuarios.get(i).getNombre());
                        }
                    }
                    break;

                case 3:

                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios");
                        break;
                    }

                    System.out.println("Seleccione usuario:");
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println(i + ". " + usuarios.get(i).getNombre());
                    }

                    int idxUser3 = sc.nextInt();

                    if (idxUser3 < 0 || idxUser3 >= usuarios.size()) {
                        System.out.println("Opción inválida");
                        break;
                    }

                    UsuarioComprador u3 = usuarios.get(idxUser3);

                    if (!(u3 instanceof Cliente)) {
                        System.out.println("Debe ser cliente");
                        break;
                    }

                    Cliente cli = (Cliente) u3;

                    System.out.println("Número de personas:");
                    int num = sc.nextInt();

                    System.out.println("Use una fecha válida: por ejemplo 2026-05-18 a las 12");
                    System.out.println("Año:");
                    int y = sc.nextInt();
                    System.out.println("Mes:");
                    int m = sc.nextInt();
                    System.out.println("Día:");
                    int d = sc.nextInt();

                    System.out.println("Hora:");
                    int h = sc.nextInt();

                    try {
                        LocalDate fecha = LocalDate.of(y, m, d);
                        LocalTime hora = LocalTime.of(h, 0);

                        ultimaReserva = cafe.crearReserva(fecha, cli, num, hora);

                        System.out.println("Reserva creada");
                        System.out.println("Mesa: " + ultimaReserva.getMesaReserva().getIdMesa());

                    } catch (Exception e) {
                        System.out.println("Error reserva: " + e.getMessage());
                    }
                    break;

                case 4:

                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios");
                        break;
                    }

                    if (ultimaReserva == null) {
                        System.out.println("Primero crea una reserva");
                        break;
                    }

                    System.out.println("Seleccione usuario:");
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println(i + ". " + usuarios.get(i).getNombre());
                    }

                    int idxUser4 = sc.nextInt();

                    if (idxUser4 < 0 || idxUser4 >= usuarios.size()) {
                        System.out.println("Opción inválida");
                        break;
                    }

                    UsuarioComprador u4 = usuarios.get(idxUser4);

                    System.out.println("Juegos:");
                    for (int i = 0; i < cafe.getInventarioJuegosPrestamo().size(); i++) {
                        JuegoPrestamo jp = cafe.getInventarioJuegosPrestamo().get(i);
                        System.out.println(i + ". " + jp.getInfoJuego().getNombre());
                    }

                    int idxJuego = sc.nextInt();
                    Juego juego = cafe.getInventarioJuegosPrestamo().get(idxJuego).getInfoJuego();

                    try {

                        u4.getJuegosPrestados().removeIf(p -> p == null);

                        cafe.crearPrestamo(
                                LocalDate.of(2026,5,19),
                                LocalDate.of(2026,5,21),
                                juego,
                                u4,
                                1,
                                ultimaReserva.getMesaReserva()
                        );

                        System.out.println("Préstamo creado");

                    } catch (Exception e) {
                        System.out.println("Error préstamo: " + e.getMessage());
                    }
                    break;

                case 5:

                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios");
                        break;
                    }

                    if (ultimaReserva == null) {
                        System.out.println("Primero crea una reserva");
                        break;
                    }

                    System.out.println("Seleccione usuario:");
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println(i + ". " + usuarios.get(i).getNombre());
                    }

                    int idxUser5 = sc.nextInt();

                    if (idxUser5 < 0 || idxUser5 >= usuarios.size()) {
                        System.out.println("Opción inválida");
                        break;
                    }

                    UsuarioComprador u5 = usuarios.get(idxUser5);

                    ArrayList<Item> items = new ArrayList<>();
                    boolean seguir = true;

                    while (seguir) {
                        System.out.println("Menú:");
                        for (int i = 0; i < cafe.getMenú().size(); i++) {
                            System.out.println(i + ". " + cafe.getMenú().get(i).getNombrePlatillo());
                        }

                        int idx = sc.nextInt();
                        int cant = sc.nextInt();

                        items.add(new Item(cant, cafe.getMenú().get(idx)));

                        System.out.println("¿Otro? 1/0");
                        seguir = sc.nextInt() == 1;
                    }

                    try {
                        cafe.crearVenta(
                                items,
                                u5,
                                LocalDate.of(2026,5,19),
                                ultimaReserva.getMesaReserva(),
                                false,
                                false
                        );

                        System.out.println("Venta creada");

                    } catch (Exception e) {
                        System.out.println("Error venta: " + e.getMessage());
                    }
                    break;

                case 6:
                    gp.guardarCafe(cafe);
                    System.out.println("Guardado");
                    break;

                case 7:
                    for (Venta v : cafe.getHistorialVentas()) {
                        System.out.println(v.getComprador().getNombre() + " - " + v.getTotal());
                    }
                    break;

                case 8:
                    gp.guardarCafe(cafe);
                    run = false;
                    break;
            }
        }

        sc.close();
    }

    public static void inicializarDatos(Café cafe) {

        ArrayList<DayOfWeek> dias = new ArrayList<>();
        dias.add(DayOfWeek.MONDAY);

        Turno turno = new Turno(dias, LocalTime.of(0,0), LocalTime.of(23,59), null);

        cafe.getEmpleados().add(new Mesero("m1",25,1,new ArrayList<>(),1,"m1",
                new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),0,turno,new ArrayList<>()));

        cafe.getEmpleados().add(new Mesero("m2",26,2,new ArrayList<>(),1,"m2",
                new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),0,turno,new ArrayList<>()));

        cafe.getEmpleados().add(new Cocinero("c1",30,3,new ArrayList<>(),1,"c1",
                new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),0,turno));

        cafe.getMesas().add(new Mesa(false,1,new ArrayList<>(),false,2));
        cafe.getMesas().add(new Mesa(false,2,new ArrayList<>(),false,4));
        cafe.getMesas().add(new Mesa(false,3,new ArrayList<>(),false,6));
        cafe.getMesas().add(new Mesa(false,4,new ArrayList<>(),false,8));

        cafe.getMenú().add(new Bebida(8000,"Cafe",new ArrayList<>(),null,false,true));
        cafe.getMenú().add(new Bebida(12000,"Cerveza",new ArrayList<>(),null,true,false));
        cafe.getMenú().add(new Pasteleria(10000,"Brownie",new ArrayList<>(),null));

        Juego j1 = new JuegoSimple("UNO",2000,"Mattel",4,false,
                RestriccionEdad.TODAS_EDADES,TiposJuegos.CARTAS,4);

        cafe.getInventarioJuegosPrestamo().add(new JuegoPrestamo(true,false,EstadoJuego.NUEVO,j1,0));
    }
}