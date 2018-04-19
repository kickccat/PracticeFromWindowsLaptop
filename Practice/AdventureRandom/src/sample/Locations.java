package sample;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile randomAccessFile;
    
    public static void main(String[] args) {
//        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
//            for (Location location : locations.values()) {
//                locFile.writeObject(location);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (RandomAccessFile accessFile = new RandomAccessFile("locations_rand.dat", "rwd")) {

            accessFile.writeInt(locations.size());
            int indexSize = locations.size() * 3 * Integer.BYTES;
            int locationStart = (int) (indexSize + accessFile.getFilePointer() + Integer.BYTES);
            accessFile.writeInt(locationStart);

            long indexStart = accessFile.getFilePointer();
            int startPointer = locationStart;
            accessFile.seek(startPointer);
            for (Location location : locations.values()) {
                accessFile.writeInt(location.getLocationID());
                accessFile.writeUTF(location.getDescription());
                StringBuilder builder = new StringBuilder();
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        builder.append(direction);
                        builder.append(",");
                        builder.append(location.getExits().get(direction));
                        builder.append(",");
                    }
                }
                accessFile.writeUTF(builder.toString());
                IndexRecord record = new IndexRecord(startPointer, Math.toIntExact(accessFile.getFilePointer() - startPointer));
                index.put(location.getLocationID(), record);
                startPointer = Math.toIntExact(accessFile.getFilePointer());
            }
            
            accessFile.seek(indexStart);
            for (Integer locationID : index.keySet()) {
                accessFile.writeInt(locationID);
                accessFile.writeInt(index.get(locationID).getStartByte());
                accessFile.writeInt(index.get(locationID).getLength());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static {
        try {
            randomAccessFile = new RandomAccessFile("locations_rand.dat", "rwd");
            int numLocations = randomAccessFile.readInt();
            long locationStartpoint = randomAccessFile.readInt();
            
            while (randomAccessFile.getFilePointer() < locationStartpoint) {
                int locationId = randomAccessFile.readInt();
                int locationStart = randomAccessFile.readInt();
                int locationLength = randomAccessFile.readInt();
                
                IndexRecord record = new IndexRecord(locationStart, locationLength);
                index.put(locationId, record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
//            boolean eof = false;
//            while (!eof) {
//                try {
//                    Location location = (Location) locFile.readObject();
//                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
//                    System.out.println("Found " + location.getExits().size() + " exits");
//                    locations.put(location.getLocationID(), location);
//                } catch (EOFException e) {
//                    eof = true;
//                }
//            }
//        } catch (ClassNotFoundException | IOException e) {
//            e.getMessage();
//        }
    }
    
    public Location getLocation(int locationId) throws IOException {
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
