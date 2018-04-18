package sample;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, String> {
    
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    
    static {
        try (ObjectInputStream locFIle = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Location location = (Location) locFIle.readObject();
                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
                    System.out.println("Found " + location.getExits().size() + " exits");
                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.getMessage();
        }
    }
    
    public static void main(String[] args) {
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location : locations.values()) {
                locFile.writeObject(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int size() {
        return 0;
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }
    
    @Override
    public boolean containsKey(Object key) {
        return false;
    }
    
    @Override
    public boolean containsValue(Object value) {
        return false;
    }
    
    @Override
    public String get(Object key) {
        return null;
    }
    
    @Override
    public String put(Integer key, String value) {
        return null;
    }
    
    @Override
    public String remove(Object key) {
        return null;
    }
    
    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {
    
    }
    
    @Override
    public void clear() {
    
    }
    
    @Override
    public Set<Integer> keySet() {
        return null;
    }
    
    @Override
    public Collection<String> values() {
        return null;
    }
    
    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return null;
    }
}
