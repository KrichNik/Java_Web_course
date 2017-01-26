package practice3.part5;

import java.util.NavigableMap;
import java.util.TreeMap;

public class Part5 {

    private static final NavigableMap<Integer, String> MAP_ROMAN = new TreeMap<>();

    static {
        MAP_ROMAN.put(1000, "M");
        MAP_ROMAN.put(900, "CM");
        MAP_ROMAN.put(500, "D");
        MAP_ROMAN.put(400, "CD");
        MAP_ROMAN.put(100, "C");
        MAP_ROMAN.put(90, "XC");
        MAP_ROMAN.put(50, "L");
        MAP_ROMAN.put(40, "XL");
        MAP_ROMAN.put(10, "X");
        MAP_ROMAN.put(9, "IX");
        MAP_ROMAN.put(5, "V");
        MAP_ROMAN.put(4, "IV");
        MAP_ROMAN.put(1, "I");
    }

    public static void main(String[] args) {
    	String roman = null;
    	int decimal = 0;
        for(int i=1;i<=100;i++) {
        	roman = decimal2Roman(i);
        	decimal = roman2Decimal(roman);
        	System.out.println(i + " ====> " + roman + " ====> " + decimal);
        }
    }

    public static String decimal2Roman(int x) {
        int l =  MAP_ROMAN.floorKey(x);
        if (x == l) {
            return MAP_ROMAN.get(x);
        }
        return MAP_ROMAN.get(l) + decimal2Roman(x-l);
    }

    public static int roman2Decimal(String s) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = s.toUpperCase();
        for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNumeral.charAt(x);
            switch (convertToDecimal) {
                case 'C': {
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;
                }
                case 'L': {
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;
                }
                case 'X': {
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;
                }
                case 'V': {
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;
                }
                case 'I': {
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
                }
                default: {
                	throw new IllegalArgumentException("Invalid value!");
                }
            }
        }
        return decimal;
    }

    public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        }
        return lastDecimal + decimal;
    }
}
