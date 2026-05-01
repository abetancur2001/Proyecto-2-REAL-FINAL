package persistencia;

import java.io.*;

import modelo.Café;

public class GestorPersistencia {

    private static final String RUTA = System.getProperty("user.dir") + File.separator + "cafe.dat";

    public void guardarCafe(Café cafe) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(RUTA))) {
            out.writeObject(cafe);
            System.out.println("Guardado en: " + RUTA);
        }
    }

    public Café cargarCafe() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(RUTA))) {
            return (Café) in.readObject();
        }
    }
}