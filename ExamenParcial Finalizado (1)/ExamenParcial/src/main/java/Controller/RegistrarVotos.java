package Controller;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class RegistrarVotos implements Runnable {

    private EntityManager entityManager;
    private InsertarDatos insertarDatos;

    private static final int MAX_DELAY = 10000; // Tiempo máximo de demora en milisegundos

    public RegistrarVotos(EntityManager entityManager, InsertarDatos insertarDatos) {
        this.entityManager = entityManager;
        this.insertarDatos = insertarDatos;
    }

    @Override
    public void run() {

        long delay = (long) (Math.random() * MAX_DELAY);
        try{
            Thread.sleep(delay);
        } catch (InterruptedException e){
            e.getStackTrace();
        }

        try {
            insertarDatos.registrarVotoEnAsambleista(entityManager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
