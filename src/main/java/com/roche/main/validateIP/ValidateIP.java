package com.roche.main.validateIP;

import java.util.Arrays;

public class ValidateIP {
	

	  /**
     * Check that a string represents a decimal number
     * @param string The string to check
     * @return true if string consists of only numbers without leading zeroes, false otherwise
     */
	
    public static boolean isDecimal(String string) {
        if (string.startsWith("0")) {
            if (string.length() == 1) {
                 // "0"
                 return true;
            }
            // The string has a leading zero but is not "0"
            return false;
        }
        for(char c : string.toCharArray()) {
            if(c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean isIp(String string) {
        String[] parts = string.split("\\.", -1);
        return parts.length == 4 // 4 parts
                && Arrays.stream(parts)
                .filter(ValidateIP::isDecimal) // Only decimal numbers
                .map(Integer::parseInt)
                .filter(i -> i <= 255 && i >= 0) // Must be inside [0, 255]
                .count() == 4; // 4 numerical parts inside [0, 255]
    }

}
