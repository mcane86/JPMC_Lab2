package com.michaelcane;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {

    // Hashmaps used for String creation

    public static HashMap<Integer, String> phoneNumberMap = new HashMap<Integer, String>();
    public static HashMap<Integer, String> militaryTimeMap = new HashMap<Integer, String>();
    public static HashMap<Integer, String> utilityMap = new HashMap<Integer, String>();
    public static HashMap<Integer, String> twelveHourMap = new HashMap<Integer, String>();

    String pattern = "(?i)[AP]";
    Pattern r = Pattern.compile(pattern);

    public HashMap<Integer, String> populatePhoneNumberMap() {
        phoneNumberMap.put(0, "Zero");
        phoneNumberMap.put(1, "One");
        phoneNumberMap.put(2, "Two");
        phoneNumberMap.put(3, "Three");
        phoneNumberMap.put(4, "Four");
        phoneNumberMap.put(5, "Five");
        phoneNumberMap.put(6, "Six");
        phoneNumberMap.put(7, "Seven");
        phoneNumberMap.put(8, "Eight");
        phoneNumberMap.put(9, "Nine");
        return phoneNumberMap;
    }

    public HashMap<Integer, String> populateMilitaryTimeMap() {
        militaryTimeMap.put(2, "Twenty");
        militaryTimeMap.put(3, "Thirty");
        militaryTimeMap.put(4, "Forty");
        militaryTimeMap.put(5, "Fifty");
        return militaryTimeMap;
    }

    public HashMap<Integer, String> populateUtilityMap() {
        utilityMap.put(0, "Ten");
        utilityMap.put(1, "Eleven");
        utilityMap.put(2, "Twelve");
        utilityMap.put(3, "Thirteen");
        utilityMap.put(4, "Fourteen");
        utilityMap.put(5, "Fifteen");
        utilityMap.put(6, "Sixteen");
        utilityMap.put(7, "Seventeen");
        utilityMap.put(8, "Eighteen");
        utilityMap.put(9, "Nineteen");
        return utilityMap;

    }

    public HashMap<Integer, String> populateTwelveHourMap() {
        twelveHourMap.put(1, "Ante Meridiem");
        twelveHourMap.put(2, "Post Meridiem");
        return twelveHourMap;
    }

    // Utilities for deconstructing the input String

    /**
     * Splits the String input into an array of String elements that will only have two indexes.
     * @param input
     * @return
     */
    public String[] splitsInputOnDelimiter(String input) {
        String[] newInput = input.split(":");
        return newInput;
    }

    public boolean confirmsIfAnAmOrPmExists(String input) {
        Matcher m = r.matcher(input);
        return m.find();
    }

    public String addTheAMorPMToTheTime(String[] input) {
        if (input[1].contains("a")) {
            return twelveHourMap.get(1);
        } else {
            return twelveHourMap.get(2);
        }
    }

    public String[] addsAZeroToTheFirstIndex(String[] input) {
        if (input[0].length() == 1) {
            input[0] = "0" + input[0];
            return input;
        } else {
            return input;
        }
    }

    public boolean testsForARealInput(String[] input) {
        if (input[0].charAt(0) > '2' && input[0].charAt(1) > '4') {
            System.out.println("Improper input.");
            return false;
        } else {
            return true;
        }
    }

    // Construction methods

    public String convertsNumbersToText(char input, HashMap<Integer, String> map) {
        for (Integer key : map.keySet()) {
            if (key == Character.getNumericValue(input)) {
                return map.get(key);
            }
        }
        return " ";
    }

    public String convertPhoneNumberToText(String input) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            builder.append(convertsNumbersToText(input.charAt(i), phoneNumberMap));
        }

        return builder.toString();
    }

    public String convertTimeToText(String input) {
        StringBuilder builder = new StringBuilder();
        String[] timeToConvert = addsAZeroToTheFirstIndex(splitsInputOnDelimiter(input));

        testsForARealInput(timeToConvert);

        for (int i = 0; i < timeToConvert.length; i++) {
            if (timeToConvert[i].charAt(0) == '0') {
                builder.append(convertsNumbersToText(timeToConvert[i].charAt(0), phoneNumberMap)).append(convertsNumbersToText(timeToConvert[i].charAt(1), phoneNumberMap)).append(" ");
            } else if (timeToConvert[i].charAt(0) == '1') {
                builder.append(convertsNumbersToText(timeToConvert[i].charAt(1), utilityMap)).append(" ");
            } else {
                builder.append(convertsNumbersToText(timeToConvert[i].charAt(0), militaryTimeMap)).append(convertsNumbersToText(timeToConvert[i].charAt(1), phoneNumberMap)).append(" ");
            }
        }

        if(confirmsIfAnAmOrPmExists(timeToConvert[1])) {
            builder.append(addTheAMorPMToTheTime(timeToConvert));
        } else {
            builder.append("Hours");
        }

        return builder.toString();
    }
}
