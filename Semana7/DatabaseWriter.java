package Semana7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWriter implements Runnable {
    private String data;

    public DatabaseWriter(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        // Establecer la conexión a la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:h2://localhost:3306/mydatabase", "username", "password")) {
            // Crear una declaración preparada para la inserción
            String sql = "INSERT INTO mytable (column1) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Establecer el valor del parámetro
                statement.setString(1, data);

                // Ejecutar la inserción
                statement.executeUpdate();

                System.out.println("Dato insertado correctamente: " + data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

