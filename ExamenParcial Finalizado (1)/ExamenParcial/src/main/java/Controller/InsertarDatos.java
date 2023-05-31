package Controller;

import model.*;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.List;
import java.util.Random;

public class InsertarDatos {

    //Insertar Asambleista usando JDBC
    public synchronized void insertAsambleistaJDBC(Connection conn) {
        PreparedStatement statement = null;
        Random random = new Random();

        // Declarar y asignar valores posibles para la variable clasi
        String[] valoresPosibles = {"E", "P", "N"};

        // Obtener un índice aleatorio basado en la longitud del arreglo valoresPosibles
        int indiceAleatorio = random.nextInt(valoresPosibles.length);

        // Asignar el valor aleatorio a la variable clasi
        String clasi = valoresPosibles[indiceAleatorio];

        try {
            String sql = "INSERT INTO ASAMBLEISTA_ECU (CLASIFICACION) VALUES (?)";
            statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, String.valueOf(clasi));
            statement.executeUpdate();
        } catch (SQLException e) {}
    }

    public synchronized void registrarVotoEnAsambleista(EntityManager emt) throws SQLException {
        Random random = new Random();
        int TOTAL_ASAMBLEISTAS = 10;

        List<Asambleista> asambleistas = emt.createQuery("SELECT a FROM Asambleista a", Asambleista.class)
                .getResultList();

        int contadorVotos = 0;
        int x = getNumFilasTabla() - 1;

        for (int i = 0; i < TOTAL_ASAMBLEISTAS; i++) {
            emt.getTransaction().begin();

            if (contadorVotos >= TOTAL_ASAMBLEISTAS){
                return;
            }

            Asambleista asambleista = asambleistas.get(x);

            int votoAleatorio = random.nextInt(100) + 1;
            String tipoVoto;
            if (votoAleatorio >= 1 && votoAleatorio <= 24) {
                tipoVoto = "S";
            } else if (votoAleatorio >= 25 && votoAleatorio <= 50) {
                tipoVoto = "N";
            } else {
                tipoVoto = "A";
            }

            Voto voto = new Voto();
            voto.setTipoVoto(tipoVoto.charAt(0));
            asambleista.setVoto(voto);

            emt.persist(voto);

            emt.getTransaction().commit();

            x--;
            contadorVotos++;
        }

    }

    private static int getNumFilasTabla() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/ExamenParcial", "sa", "");

        // Consulta para obtener el número de filas de la tabla
        String consulta = "SELECT COUNT(*) FROM ASAMBLEISTA_ECU";
        PreparedStatement statement = connection.prepareStatement(consulta);
        int numFilas = 0;

        try {
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Obtener el resultado
            if (resultSet.next()) {
                numFilas = resultSet.getInt(1);
            }
        } finally {
            statement.close();
        }

        return numFilas;
    }
}
