package Semana7;

public class Main {
    public static void main(String[] args) {
        String[] data = {"dato1", "dato2", "dato3"};

        for (String d : data) {
            DatabaseWriter writer = new DatabaseWriter(d);
            Thread thread = new Thread(writer);
            thread.start();
        }
    }
}

