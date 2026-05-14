package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import articulos.*;
import modelo.*;
import sujetos.*;
import torneos.*;
import exceptions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class CaféTest {

    private Café cafe;

    @BeforeEach
    void setUp() {
        Administrador admin = new Administrador("Admin", 30, 9999, new ArrayList<>(), 4545, "adminPro");
        cafe = new Café(50, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>(),
                new ArrayList<>(), admin, new HashMap<>(), 1, 1, 9, 1, 0);
        cafe.inicializarDatos();
    }
    
    @Test
    @DisplayName("Crear Reserva")
    public void testCrearReserva() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        Reserva res = cafe.crearReserva(LocalDate.of(2026, 5, 12), cliente, 2, LocalTime.of(13, 0));
        assertEquals(cliente, res.getReservadoPor());
        assertEquals(2, res.getNumPersonas());
        assertEquals(LocalTime.of(13, 0), res.getHoraReserva());
    }
    
    @Test
    @DisplayName("Crear Reserva no Exitosa por Tiempo")
    public void testCrearReservaFallaXHorario() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        assertThrows(ReservaNoExitosaException.class, () -> {
            cafe.crearReserva(LocalDate.of(2026, 5, 12), cliente, 2, LocalTime.of(7, 0));
        });
    }
    
    @Test
    @DisplayName("Crear Reserva no Exitosa por Capacidad")
    public void testCrearReservaFallaXCapacidad() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        assertThrows(ReservaNoExitosaException.class, () -> {cafe.crearReserva(LocalDate.of(2026, 5, 12), cliente, 51, LocalTime.of(13, 0));});
    }
    
    @Test
    @DisplayName("Crear Prestamo")
    public void testCrearPrestamo() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        Mesa mesa = cafe.getMesas().get(0);
        Juego juego = cafe.getCatalogoJuegos().get(0);
        Prestamo p = cafe.crearPrestamo(LocalDate.now(), LocalDate.now().plusDays(5), juego, cliente, cafe.getIdPrestamo(), mesa);
        assertEquals(juego, p.getJuegoAPrestar().getInfoJuego());
        assertEquals(cliente, p.getPrestadoA());
    }
    
    @Test
    @DisplayName("Crear Prestamo no Exitoso por Disponibilidad ")
    public void testCrearPrestamoFallaXDisponibilidad() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        Mesa mesa = cafe.getMesas().get(0);
        Juego juego = cafe.getCatalogoJuegos().get(0);
        cafe.crearPrestamo(LocalDate.now(), LocalDate.now().plusDays(5), juego, cliente, cafe.getIdPrestamo(), mesa);
        assertThrows(JuegoNoDisponibleException.class, () -> { cafe.crearPrestamo(LocalDate.now(), LocalDate.now().plusDays(5), juego, cliente, cafe.getIdPrestamo(), mesa);});
    }
    
    @Test
    @DisplayName("Crear Prestamo no Exitoso por Max Prestamos ")
    public void testCrearPrestamoFallaXMaxPrestamos() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        Mesa mesa = cafe.getMesas().get(0);
        Juego juego1 = cafe.getCatalogoJuegos().get(0);
        Juego juego3 = cafe.getCatalogoJuegos().get(2);

        JuegoPrestamo jp = new JuegoPrestamo(true, false, EstadoJuego.NUEVO, juego3, 0);
        cafe.getInventarioJuegosPrestamo().add(jp);

        cafe.crearPrestamo(LocalDate.now(), LocalDate.now().plusDays(5), juego1, cliente, cafe.getIdPrestamo(), mesa);
        cafe.crearPrestamo(LocalDate.now(), LocalDate.now().plusDays(5), juego3, cliente, cafe.getIdPrestamo(), mesa);

        Juego juego5 = cafe.getCatalogoJuegos().get(4);
        JuegoPrestamo jp2 = new JuegoPrestamo(true, false, EstadoJuego.NUEVO, juego5, 0);
        cafe.getInventarioJuegosPrestamo().add(jp2);

        assertThrows(PrestamoNoPermitidoException.class, () -> {
            cafe.crearPrestamo(LocalDate.now(), LocalDate.now().plusDays(5), juego5, cliente, cafe.getIdPrestamo(), mesa);
        });
    }
    
    @Test
    @DisplayName("Crear Venta")
    public void testCrearVenta() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        Mesa mesa = cafe.getMesas().get(0);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, cafe.getMenú().get(1)));

        Venta v = cafe.crearVenta(items, cliente, LocalDate.now(), mesa, false, false);
        assertEquals(cliente, v.getComprador());
    }
    
    @Test
    @DisplayName("Crear Venta no Exitosa por tener Alcohol y un Menor")
    public void testCrearVentaFallaXAlcoholMenor() {
        Cliente cliente = cafe.getMapaClientes().get("tomas.herrera");
        Mesa mesa = cafe.getMesas().get(0);
        mesa.getPersonasSentadas().add(cliente);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, cafe.getMenú().get(3)));

        assertThrows(VentaNoPermitidaException.class, () -> {
            cafe.crearVenta(items, cliente, LocalDate.now(), mesa, false, false);
        });
    }

    @Test
    @DisplayName("Crear Venta no Exitosa por tener Caliente y Accion")
    public void testCrearVentaXFallaBebidaCalienteAccion() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        Mesa mesa = cafe.getMesas().get(0);
        mesa.getPersonasSentadas().add(cliente);

        cafe.crearPrestamo(LocalDate.now(), LocalDate.now().plusDays(5), cafe.getCatalogoJuegos().get(1), cliente, cafe.getIdPrestamo(), mesa);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, cafe.getMenú().get(2)));

        assertThrows(VentaNoPermitidaException.class, () -> { cafe.crearVenta(items, cliente, LocalDate.now(), mesa, false, false);
        });
    }

    @Test
    @DisplayName("Crear Venta Usando Puntos")
    public void testCrearVentaConPuntos() {
        Cliente cliente = cafe.getMapaClientes().get("tomas.herrera");
        Mesa mesa = cafe.getMesas().get(0);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, cafe.getMenú().get(1)));

        Venta v = cafe.crearVenta(items, cliente, LocalDate.now(), mesa, true, false);
        assertNotNull(v);
        assertTrue(v.getTotal() < 10000);
    }

    @Test
    @DisplayName("Crear Torneo")
    public void testCrearTorneo() {
        Juego juego = cafe.getCatalogoJuegos().get(4);
        JuegoPrestamo jp = new JuegoPrestamo(true, false, EstadoJuego.NUEVO, juego, 0);
        cafe.getInventarioJuegosPrestamo().add(jp);

        Torneo t = cafe.crearTorneo(DayOfWeek.TUESDAY, "Copa Uno", 4, juego, 0, false);
        assertNotNull(t);
        assertEquals("Copa Uno", t.getNombre());
    }

    @Test
    @DisplayName("Crear Torneo no Exitoso por Falta de Copias")
    public void testCrearTorneoFallaParticipantes() {
        Juego juego = cafe.getCatalogoJuegos().get(4);
        assertThrows(TorneosException.class, () -> {cafe.crearTorneo(DayOfWeek.TUESDAY, "Copa Uno", 100, juego, 0, false);
        });
    }

    @Test
    @DisplayName("Incribirse a Torneo")
    public void testInscribirTorneo() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        Torneo t = cafe.getTorneos().get(0);

        ArrayList<UsuarioComprador> usuarios = new ArrayList<>();
        usuarios.add(cliente);

        t.inscribir(cliente, usuarios);
        assertTrue(t.getInscripciones().containsKey(cliente.getCedula()));
    }

    @Test
    @DisplayName("Incribir Empleado a Torneo")
    public void testInscribirTorneoEmpleadoEnTurno() {
        Empleado emp = cafe.getMapaEmpleados().get("carlos.lopez"); 
        Torneo t = cafe.getTorneos().get(0);

        ArrayList<UsuarioComprador> usuarios = new ArrayList<>();
        usuarios.add(emp);

        assertThrows(TorneosException.class, () -> {
            t.inscribir(emp, usuarios);
        });
    }

    @Test
    @DisplayName("Validar Cambio de Turno")
    public void testValidarCambioTurno() {
        Empleado emp = cafe.getMapaEmpleados().get("carlos.lopez");

        ArrayList<DayOfWeek> dias = new ArrayList<>();
        
        int i = 1;
        
        while(i <= 7) {
        	dias.add(DayOfWeek.of(i));
        	i += 1;
        }

        Turno nuevoTurno = new Turno(dias, LocalTime.of(13, 0), LocalTime.of(15, 0));
        CambioTurno ct = new CambioTurno(0, LocalDate.now(), EstadoSolicitud.PENDIENTE, emp, nuevoTurno);

        assertTrue(cafe.validarCambioTurno(ct));
    }


    @Test
    @DisplayName("Validar Cambio de Turno no Exitoso")
    public void testValidarCambioTurnoFalla() {
        Empleado emp = cafe.getMapaEmpleados().get("carlos.lopez");

        ArrayList<DayOfWeek> dias = new ArrayList<>();
        dias.add(DayOfWeek.MONDAY);
        Turno nuevoTurno = new Turno(dias, LocalTime.of(20, 0), LocalTime.of(23, 0));
        CambioTurno ct = new CambioTurno(0, LocalDate.now(), EstadoSolicitud.PENDIENTE, emp, nuevoTurno);

        assertFalse(cafe.validarCambioTurno(ct));
    }

    @Test
    @DisplayName("Reparar Juego")
    public void testRepararJuego() {
        JuegoPrestamo jp = cafe.getInventarioJuegosPrestamo().get(2);
        cafe.repararJuegoPrestamo(jp);
        assertEquals(EstadoJuego.NUEVO, jp.getEstado());
        assertTrue(jp.isDisponible());
    }

    @Test
    @DisplayName("Mover Juego Venta a Prestamos")
    public void testMoverVentaAPrestamo() {
        int tamañoAntes = cafe.getInventarioJuegosPrestamo().size();
        JuegoVenta jv = cafe.getInventarioJuegosVenta().get(0);
        cafe.moverVentaAPrestamo(jv);
        assertEquals(tamañoAntes + 1, cafe.getInventarioJuegosPrestamo().size());
    }

    @Test
    @DisplayName("Marcar Juego Desaparecido")
    public void testMarcarJuegoDesaparecido() {
        Cliente cliente = cafe.getMapaClientes().get("sofia.ramirez");
        Mesa mesa = cafe.getMesas().get(0);
        Juego juego = cafe.getCatalogoJuegos().get(0);

        Prestamo p = cafe.crearPrestamo(LocalDate.now().minusDays(8), LocalDate.now().plusDays(5), juego, cliente, cafe.getIdPrestamo(), mesa);

        assertTrue(cafe.marcarJuegoDesparecido(p));
        assertTrue(p.getJuegoAPrestar().isDesaparecido());
    }

    @Test
    @DisplayName("Autenticar Cliente")
    public void testAutenticarCliente() {
        Cliente c = cafe.autenticarCliente("sofia.ramirez", 1111);
        assertNotNull(c);
        assertEquals("sofia.ramirez", c.getLogin());
    }

    @Test
    @DisplayName("Autenticar Cliente no Exitoso por Credenciales Incorrectas")
    public void testAutenticarClienteFalla() {
        Cliente c = cafe.autenticarCliente("sofia.ramirez", 9999);
        assertNull(c);
    }
    
    
}



