package Actividad2;

import ec.edu.utpl.clasica.computacion.pa.s7.pe.WebClient;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException, InterruptedException {
        String uri1 = "https://www.assocalciatori.it/...";
        String uri2 = "https://dlcdn.apache.org/zeppelin/zeppelin-0.10.1/...";
        String uri3 = "https://medium.com/swlh/think-like-an-...";

        Thread thread1 = new Thread(new TaskURIData(uri1));

        Thread thread2 = new Thread(new TaskURIData(uri2));

        Thread thread3 = new Thread(new TaskURIData(uri3));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }
}


