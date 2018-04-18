package sample;

public class ShiftInt {
    
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    
    public static void main(String[] args) {
        int x = 922342959;
    
        writeInt(x);
    }
    
    private static void writeInt(int x) {
        display(x >>> 24);
        display(x >>> 16);
        display(x >>> 8);
        display(x >>> 0);
    }
    
    private static void display(int i) {
        String all = String.format("%32s", Integer.toBinaryString(i)).replace(" ", "0");
        String colouredBinary = all.substring(0, 24) + ANSI_PURPLE + all.substring(24) + ANSI_RESET;
        int x = i & 0xFF;
        String output = String.format("%10d and 0xFF is %8s \t %10d is ", x, Integer.toBinaryString(x), i) + colouredBinary;
        System.out.println(output);
    }
}
