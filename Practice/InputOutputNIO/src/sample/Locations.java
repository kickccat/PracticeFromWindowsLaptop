package sample;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile randomAccessFile;
    
    public static void main(String[] args) {
        
        Path locPath = FileSystems.getDefault().getPath("locations.dat");
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for (Location location : locations.values()) {
                locFile.writeObject(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Path locPath = FileSystems.getDefault().getPath("locations_big.txt");
//        Path dirPath = FileSystems.getDefault().getPath("directions_big.txt");
//
//        try (BufferedWriter locFile = Files.newBufferedWriter(locPath);
//             BufferedWriter dirFile = Files.newBufferedWriter(dirPath)) {
//
//            for (Location location : locations.values()) {
//                locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
//                for (String direction : location.getExits().keySet()) {
//                    if (!direction.equalsIgnoreCase("Q")) {
//                        dirFile.write(location.getLocationID() + ", " + direction + " ," + location.getExits().get(direction) + "\n");
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    
    static {
        
        Path locPath = FileSystems.getDefault().getPath("locations.dat");
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
//        Path locPath = FileSystems.getDefault().getPath("locations_big.txt");
//        Path dirPath = FileSystems.getDefault().getPath("directions_big.txt");
//
//        try (Scanner scanner = new Scanner(Files.newBufferedReader(locPath))){
//            scanner.useDelimiter(",");
//            while (scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                System.out.println("Imported loc: " + loc + ": " + description);
//                locations.put(loc, new Location(loc, description, null));
//            }
//        } catch (IOException e) {
//            System.out.println("IO Exception: " + e.getMessage());
//        }
//
//        try (BufferedReader dirFile = Files.newBufferedReader(dirPath)){
//            String input;
//            while ((input = dirFile.readLine()) != null) {
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//                System.out.println(loc + ": " + direction + ": " + destination);
//                Location location = locations.get(loc);
//                location.addExits(direction, destination);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    
    Location getLocation(int locationId) throws IOException {
        IndexRecord record = index.get(locationId);
        randomAccessFile.seek(record.getStartByte());
        int id = randomAccessFile.readInt();
        String description = randomAccessFile.readUTF();
        String exits = randomAccessFile.readUTF();
        String[] exitPart = exits.split(",");
        Location location = new Location(locationId, description, null);
        
        if (locationId != 0) {
            for (int i = 0; i < exitPart.length; i++) {
                System.out.println("exitPart = " + exitPart[i]);
                System.out.println("exitPart[+1] = " + exitPart[i + 1]);
                String direction = exitPart[i];
                int destination = Integer.parseInt(exitPart[++i]);
                location.addExits(direction, destination);
            }
        }
        return location;
    }
    
    @Override
    public int size() {
        return locations.size();
    }
    
    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }
    
    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }
    
    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }
    
    @Override
    public Location get(Object key) {
        return locations.get(key);
    }
    
    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }
    
    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }
    
    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
    
    }
    
    @Override
    public void clear() {
        locations.clear();
    }
    
    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }
    
    @Override
    public Collection<Location> values() {
        return locations.values();
    }
    
    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
    
    public void close() throws IOException {
        randomAccessFile.close();
    }
}
