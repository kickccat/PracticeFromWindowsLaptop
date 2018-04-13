package de.is2.mtext.soap.demo.test;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Random;

@Component
public class FreischaltCodeTest {
    
    private static final String charsUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String charsLower = "abcdefghijklmnopqrstuvwxyz";
    private static final String nums = "0123456789";
    private static final String specialSymbols = "!@#$%^&*()_+-=.,/';:?><~*/-+[]{}\\`´~|";
    private static final int length = 8;
    private StringBuffer freischaltcode;
    private Random rnd = new Random();
    HashMap<Integer, String> stringHashMap;
    
    public FreischaltCodeTest() {
        this.stringHashMap = new HashMap<>();
        stringHashMap.put(0, charsUpper);
        stringHashMap.put(1, charsLower);
        stringHashMap.put(2, nums);
        stringHashMap.put(3, specialSymbols);
    }
    
    public String generate() {
        
        return generateCode(length);
    }
    
    public String generateCode(int length) {
        freischaltcode = new StringBuffer();
        while (freischaltcode.length() <= length) {
            int keyOfString = rnd.nextInt(4);
            if (keyOfString == stringHashMap.size() && freischaltcode.length() <= 4) {
                for (int i=keyOfString; i>0; i--) {
                    String rndString = stringHashMap.get(i);
                    char ca = rndString.charAt(rnd.nextInt(rndString.length()));
                    freischaltcode.append(ca);
                }
            }
            String rndString = stringHashMap.get(keyOfString);
            char ca = rndString.charAt(rnd.nextInt(rndString.length()));
    
            if (freischaltcode.toString().indexOf(ca) < 0) {
                freischaltcode.append(ca);
            }
        }
        return freischaltcode.toString();
    }
    
    public static void main(String[] args) {
        FreischaltCodeTest codeTest = new FreischaltCodeTest();
        System.out.println(codeTest);
        System.out.println(codeTest.generate());
    }
}
