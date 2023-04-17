package Semana1;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Connection con =
                DriverManager.getConnection("jdbc:h2:~/dbpa","sa", "");

        //1. Crear tabla
        //createTable(con);

        //2. Agregar datos/inserts
        //insertData(con);

        //3. Consultar los datos
        //getAllData(con);

        //4. Obtener promedio
        //System.out.println(promedioEdad(con));

        //5. Obtener nombres inician por J
        //nombresJ(con);

        //6.

        Scanner lector = new Scanner(System.in);
        System.out.println("Ingrese el ID: ");
        String id2search = lector.nextLine();
        searchById(id2search, con);

        con.close();
    }
    private static void getAllData(Connection con) throws  SQLException{
        String select = "SELECT ID, FIRST_NAME, LAST_NAME, AGE FROM REGISTRATION";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(select)
        ) {
            while(rs.next()){
                System.out.printf("%d - %s - %s - (%d)\n",
                        rs.getInt("ID"),
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME"),
                        rs.getInt("AGE"));
            }

        }
    }

    private static void insertData(Connection con) throws  SQLException{
        var data = """
                INSERT INTO REGISTRATION VALUES (1, 'Jose', 'Mr', 45);
                INSERT INTO REGISTRATION VALUES (2, 'Pablo', 'Reyes', 19);
                INSERT INTO REGISTRATION VALUES (3, 'Karla', 'Mosquera', 43);
                INSERT INTO REGISTRATION VALUES (4, 'Jose', 'Torres', 50);
                INSERT INTO REGISTRATION VALUES (5, 'Martha', 'Reyes', 47);
                INSERT INTO REGISTRATION VALUES (6, 'Daniel', 'Romero', 31);
                """;

        try (Statement st = con.createStatement()) {
            int count = st.executeUpdate(data);
            System.out.println(count);
        }
    }

    private static void createTable(Connection con) throws SQLException {

        try (Statement st = con.createStatement()) {

            var sqlCreateTable = """
                    CREATE TABLE IF NOT EXISTS REGISTRATION
                    (
                    ID INTEGER NOT NULL,
                    FIRST_NAME VARCHAR(255),
                    LAST_NAME VARCHAR (255),
                    AGE INTEGER,
                    CONSTRAINT REGISTRATION_pkey PRIMARY KEY (ID)
                    );
                    """;

            st.executeUpdate(sqlCreateTable);
            st.close();

            System.out.println("Tabla creada");
        }
    }

    private static double promedioEdad(Connection con) throws SQLException {
        String select = "SELECT AVG(AGE) FROM REGISTRATION";
        double salida = 0;
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(select)
        ) {
            rs.next();
            salida = rs.getDouble("AVG(AGE)");

        }
        return salida;
    }

    private static void nombresJ(Connection con) throws SQLException {
        String select = "SELECT FIRST_NAME FROM REGISTRATION WHERE FIRST_NAME LIKE 'J%'";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(select)
        ) {
            while (rs.next()){
                System.out.printf("%s%n",
                        rs.getString("FIRST_NAME"));
            }

        }
    }

    private static void searchById(String id, Connection con) throws  SQLException{
        var selectBase = """
                SELECT ID, FIRST_NAME, LAST_NAME, AGE 
                FROM REGISTRATION
                WHERE ID = ?
                """;
        var select = String.format(selectBase, id);
        try (PreparedStatement pst = con.prepareStatement(select)){
            pst.setString(1, id);
            try( ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    System.out.printf("%d - %s - %s - (%d)\n",
                            rs.getInt("ID"),
                            rs.getString("LAST_NAME"),
                            rs.getString("FIRST_NAME"),
                            rs.getInt("AGE"));
                }
            }
        }
    }
}
