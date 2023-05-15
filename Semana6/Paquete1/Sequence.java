package Semana6.Paquete1;

public class Sequence {

    private int c = 0;

    public synchronized void increment(){
        System.out.printf("%s - %d%n ",
                Thread.currentThread().getName(),
                c);
        synchronized (this){
            c++;
        }


    }

    public void decrement(){
        c--;
    }

    public int value(){
        return c;
    }

}
