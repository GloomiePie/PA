package Semana6.Paquete1;

public class CounterIncRunnable implements Runnable{

    private final Sequence mySequence;

    public CounterIncRunnable(Sequence mySequence){
        this.mySequence = mySequence;
    }

    public void run() {
        for( var i = 0; i < 1_000_000; i++){
            mySequence.increment();
        }
    }

}
