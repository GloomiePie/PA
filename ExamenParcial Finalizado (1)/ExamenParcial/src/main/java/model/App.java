package model;

import Controller.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        /*
        <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
           Para Dropear Crear la base - Agregar al persistence.xml
         */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eP");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Connection connection = DriverManager.getConnection("jdbc:h2:~/ExamenParcial", "sa", "");

        int TOTAL_ASAMBLEISTAS = 10; // Número de asambleístas

        // Crear hilos para los asambleístas y ejecutar la simulación
        InsertarDatos insertarDatos = new InsertarDatos();

        for (int i = 0; i < TOTAL_ASAMBLEISTAS; i++) {

            InsertarAsambleista insertAsam = new InsertarAsambleista(connection, insertarDatos);
            Thread thread = new Thread(insertAsam);

            thread.start();
        }

        RegistrarVotos regisVotos = new RegistrarVotos(entityManager, insertarDatos);
        Thread thread2 = new Thread(regisVotos);

        thread2.start();
        thread2.join();

        // Generar resumen utilizando JPA o JDBC
        imprimirResultados(entityManager);
    }

    public static void imprimirResultados(EntityManager entityManager) {
        System.out.println("VOTO\t| CANTIDAD");
        System.out.println("----------------------------");

        // Obtener la cantidad de votos por tipo
        TypedQuery<Object[]> countVotosQuery = entityManager.createQuery(
                "SELECT v.tipoVoto, COUNT(*) FROM Voto v GROUP BY v.tipoVoto", Object[].class);
        List<Object[]> countVotosResult = countVotosQuery.getResultList();

            for (Object[] row : countVotosResult) {
                char tipoVoto = (char) row[0];
                long cantidadVotos = (long) row[1];
                System.out.printf("%s\t| %d%n", tipoVoto, cantidadVotos);
            }

            System.out.println("----------------------------");

            // Obtener la cantidad de votos por clasificación
            TypedQuery<Object[]> countClasificacionQuery = entityManager.createQuery(
                    "SELECT a.clasi, SUM(CASE WHEN a.voto.tipoVoto = 'S' THEN 1 ELSE 0 END) AS SI," +
                            " SUM(CASE WHEN a.voto.tipoVoto = 'N' THEN 1 ELSE 0 END) AS NO," +
                            " SUM(CASE WHEN a.voto.tipoVoto = 'A' THEN 1 ELSE 0 END) AS ABSTENCION" +
                            " FROM Asambleista a GROUP BY a.clasi", Object[].class);
            List<Object[]> countClasificacionResult = countClasificacionQuery.getResultList();

            System.out.println("   | SI\t| NO\t| ABSTENCIÓN");
            System.out.println("----------------------------");

            for (Object[] row : countClasificacionResult) {
                char clasificacion = (char) row[0];
                long si = (long) row[1];
                long no = (long) row[2];
                long abstencion = (long) row[3];
                System.out.printf("%s\t| %d\t| %d\t| %d%n", clasificacion, si, no, abstencion);
            }
            System.out.println("----------------------------");
    }
}
