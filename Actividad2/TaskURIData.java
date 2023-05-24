package Actividad2;

import ec.edu.utpl.clasica.computacion.pa.s7.pe.URIData;
import ec.edu.utpl.clasica.computacion.pa.s7.pe.WebClient;

import java.io.IOException;

class TaskURIData implements Runnable{
    private String uri;
    private URIData uriData;

    public TaskURIData(String uri) {
        this.uri = uri;
    }

    public void run() {
        try {
            uriData = WebClient.getStatus(uri);
            System.out.println(uriData);
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
