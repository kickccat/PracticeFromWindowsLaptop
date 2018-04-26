package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
    
        String challenge1 = "I want a bike.";
        String challenge2 = "I want a ball.";
    
        String regExp1 = "I want a \\w+..";
        String regExp2 = "I want a (ball|bike)";
    
        Pattern pattern = Pattern.compile(regExp1);
        Matcher matcher = pattern.matcher(challenge1);
        System.out.println(matcher.matches());
    
        matcher = pattern.matcher(challenge2);
        System.out.println(matcher.matches());
    
        String challenge3 = "Replace all blanks with underscores.";
        System.out.println(challenge3.replaceAll("\\s", "_"));
    
        String challenge4 = "aaabccccccccdddefffg";
        System.out.println(challenge4.matches("[a-g]*"));
        System.out.println(challenge4.matches("[a-g]+"));
    
        String challenge5 = "abcd.135";
        System.out.println(challenge5.matches("^[A-Za-z]*\\.\\d+$"));
        System.out.println(challenge5.matches("^[A-Za-z]+\\.\\d+$"));
        System.out.println(challenge5.matches("^[A-Z]*[a-z]+\\.\\d+$"));
    
        String challenge8 = "abcd.135uvqz.7tzik.999";
        Pattern pattern8 = Pattern.compile("[A-Za-z]+\\.(\\d+)");
        Matcher mathcher8 = pattern8.matcher(challenge8);
        while (mathcher8.find()) {
            System.out.println("Occurrence: " + mathcher8.group(1));
        }
        
        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";
        Pattern pattern9 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
        Matcher matcher9 = pattern9.matcher(challenge9);
        while (matcher9.find()) {
            System.out.println("Occurrence: " + matcher9.group(1));
        }
    
        String challenge10 = "abcd.135\tuvqz.7\ttzik.999\n";
        Pattern pattern10 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
        Matcher matcher10 = pattern10.matcher(challenge10);
        while (matcher10.find()) {
            System.out.println("Occurrence: start = " + matcher10.start(1) + ", end = " + (matcher10.end(1)));
        }
    
        String challenge11 = "{0, 2}, {0, 5}, {1, 4}, {2, 9}";
        Pattern pattern11 = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher11 = pattern11.matcher(challenge11);
        while (matcher11.find()) {
            System.out.println("Occurrence: " + matcher11.group(1));
        }
        System.out.println("*********************************");
        String challenge11a = "{0, 2}, {0, 5}, {1, 4}, {2, 9}, {x, y}, {5, 7}";
        Pattern pattern11a = Pattern.compile("\\{(\\d+, \\d+)\\}");
        Matcher matcher11a = pattern11a.matcher(challenge11a);
        while (matcher11a.find()) {
            System.out.println("Occurrence: " + matcher11a.group(1));
        }
    
        String challenge12 = "11111";
        System.out.println(challenge12.matches("^\\d+$"));
        System.out.println(challenge12.matches("^\\d+(-\\d+)?$"));
    
        String challenge13 = "11111-111111";
        System.out.println(challenge13.matches("^\\d+-\\d+$"));
        System.out.println(challenge13.matches("^\\d+(-\\d+)?$"));
        
        String challenge14 = "";
    }
}
