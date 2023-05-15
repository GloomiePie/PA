package Semana6.Paquete2;

public class TaskEx implements Runnable{

    private volatile  boolean keepRunning = true;

    public void run() {
        long count = 0;
        while(keepRunning) {
            count++;
        }
        System.out.printf("Hilo terminado. %d%n", count);
    }

    public boolean isKeepRunning(){ return keepRunning; }

    public void setKeepRunning(boolean keepRunning) {
        this.keepRunning = keepRunning;
    }
}
