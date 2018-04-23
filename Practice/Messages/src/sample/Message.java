package sample;

import java.util.Random;

class Message {
    private String message;
    private volatile boolean empty = true;
    
    synchronized String read() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            
            }
        }
        empty = true;
        notifyAll();
        return message;
    }
    
    synchronized void write(String message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

class Write implements Runnable {
    private Message message;
    
    Write(Message message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        String messages[] = {"Humpty Dumpty sat on a wall", "Humpty Dumpty had a great fall", "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"};
        Random random = new Random();
    
        for (String message1 : messages) {
            message.write(message1);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        message.write("Finished");
    }
}

class Reader implements Runnable {
    private Message message;
    
    Reader(Message message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        Random random = new Random();
        for (String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
