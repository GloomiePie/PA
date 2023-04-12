package Semana1;

import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {

        Connection con =
                DriverManager.getConnection("jdbc:h2:~/dbpa","sa", "");

        //1. Crear tabla
        //createTable(con);

        //2. Agregar datos/inserts
        //insertData(con);

        //3. Consultar los datos
        getAllData(con);

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
}
