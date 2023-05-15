package Semana6.Paquete2;

public class ThStopping {
    public static void main(String[] args) throws InterruptedException{
        TaskEx taskEx = new TaskEx();
        Thread thread = new Thread(taskEx);

        thread.start();
        Thread.sleep(1_000);

        System.out.println("Despues de sleep");
        taskEx.setKeepRunning(false);

        thread.join();

        System.out.printf("KeepRunning = %b%n", taskEx.isKeepRunning());
    }
}
