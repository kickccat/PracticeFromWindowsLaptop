package sample;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new Write(message)).start();
        new Thread(new Reader(message)).start();
    }
}
