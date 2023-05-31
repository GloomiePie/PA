package Controller;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;

import java.sql.Connection;

public class InsertarAsambleista implements Runnable {
    private Connection conn;
    private InsertarDatos insertarDatos;

    public InsertarAsambleista(Connection conn, InsertarDatos insertarDatos) {
        this.conn = conn;
        this.insertarDatos = insertarDatos;
    }

    @Override
    public void run() {

        insertarDatos.insertAsambleistaJDBC(conn);
        System.out.println("Hilo Insertar ASambleista");
    }

}
