package Actividad1;

import ec.edu.utpl.clasica.computacion.pa.s7.pe.WebClient;

import java.io.IOException;

public class TaskWebClient implements Runnable{
    private String uri;

    public TaskWebClient(String uri){
        this.uri = uri;
    }

    @Override
    public void run() {
        try {
            var result = WebClient.getStatus(uri);
            System.out.println(result);
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
