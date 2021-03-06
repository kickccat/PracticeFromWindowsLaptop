package sample;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Main {
    
    public static void main(String[] args) {
        
        try {
            Pipe pipe = Pipe.open();
            Runnable writer = () -> {
              try {
                  Pipe.SinkChannel sinkChannel = pipe.sink();
                  ByteBuffer buffer = ByteBuffer.allocate(56);
    
                  for (int i = 0; i < 10; i++) {
                      String currentTime = "The time is: " + System.currentTimeMillis();
                      buffer.put(currentTime.getBytes());
                      buffer.flip();
    
                      while (buffer.hasRemaining()) {
                          sinkChannel.write(buffer);
                      }
                      buffer.flip();
                      Thread.sleep(100);
                  }
              } catch (IOException | InterruptedException e) {
                  e.printStackTrace();
              }
            };
            
            Runnable reader = () -> {
              try {
                  Pipe.SourceChannel sourceChannel = pipe.source();
                  ByteBuffer buffer = ByteBuffer.allocate(56);
    
                  for (int i = 0; i < 10; i++) {
                      int byteRead = sourceChannel.read(buffer);
                      byte[] timeString = new byte[byteRead];
                      buffer.flip();
                      buffer.get(timeString);
                      System.out.println("Reader thread: " + new String(timeString));
                      buffer.flip();
                      Thread.sleep(100);
                  }
              } catch (IOException | InterruptedException e) {
                  e.printStackTrace();
              }
            };
            
            new Thread(writer).start();
            new Thread(reader).start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
