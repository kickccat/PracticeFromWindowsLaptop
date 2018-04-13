package de.is2.mtext.soap.demo.Junit5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static de.is2.mtext.soap.demo.repository.BaseNeo4jAction.SERVERURL;

public class Junit5Main {
    
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis() / 1000;
        
      /*  // Read KundenNr and Policen Node ID and save to file
        BaseNeo4jAction baseNeo4jAction = new BaseNeo4jAction();
        StringBuilder sb = baseNeo4jAction.readKundenNrAndNodeId();
        baseNeo4jAction.saveKundenNrAndNodeId(sb);*/
        
        /*// Test if it exists Kunden Nummer length than 9 or start with "0"
        System.out.println("kunden nummer länger als 9 sind " + new BaseNeo4jAction().checkKundenNrWith10Length());*/
    
        /*// Test getVertragDetails
        TestGetVertragDetails vertragDetails = new TestGetVertragDetails();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(KUNDENNR_NODE_FILE));
        String input = bufferedReader.readLine();
        String[] line;
        int emptyValue = 0;
        int sum = 0;
        Optional<String> jsonValue;
        while ((input = bufferedReader.readLine()) != null) {
            sum++;
            if (sum < 1300000) {
                continue;
            }
            line = input.split(";");
            System.out.println(line[0] + " | " + line[1]);
            jsonValue = Optional.ofNullable(vertragDetails.getVertragDetails(SERVERURL, line[0], line[1]));
            if (jsonValue.isPresent()) {
                System.out.println(jsonValue.get());
            } else {
                emptyValue++;
            }
        }*/
        
        /*BufferedReader bufferedReader = new BufferedReader(new FileReader(KUNDENNR_NODE_FILE));
        int emptyValue = 0;
        String input;
        String[] line;
        while ((input = bufferedReader.readLine()) != null) {
            line = input.split(";");
            if (line[0] != null && line[1] != null) {
                continue;
            } else {
                System.out.println("Null value: " + line[0] + "----->" + line[1]);
                emptyValue++;
            }
        }*/

//        // OutOfMemory Error
//        List<String> dataList = processInputFile(KUNDENNR_NODE_FILE);
//        System.out.println("Data: " + dataList);
    
//        System.out.println("Test values amount: " + sum);
//        System.out.println("Empty value amount: " + emptyValue);

        long endTime = System.currentTimeMillis() / 1000;
        System.out.println("Spend time: " + (endTime - startTime) + " seconds");
    }
    
    private static List<String> processInputFile(String inputFile) {
        List<String> inputList = new ArrayList<>();
        
        try {
            File input = new File(inputFile);
            InputStream inputStream = new FileInputStream(input);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return inputList;
    }
    
    private static Function<String, String> mapToItem = (line) -> {
        String[] p = line.split(";");

//        System.out.println(p[0] + " | " + p[1]);
        String jsonValue = new TestGetVertragDetails().getVertragDetails(SERVERURL, p[0], p[1]);
        
        return jsonValue;
    };
}
