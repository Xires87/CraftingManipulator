package net.fryc.craftingmanipulator.util;


public class StringHelper {

    public static String convertToOneBigString(char delimiter, String... strings){
        StringBuilder endValue = new StringBuilder();
        for(String string : strings){
            endValue.append(string);
            endValue.append(delimiter);
        }
        //removing unnecessary delimiter at the end
        if(!endValue.isEmpty()){
            endValue.deleteCharAt(endValue.length()-1);
        }

        return endValue.toString();
    }

    public static String[] convertToStringArray(char delimiter, String bigStringWithDelimiters){
        return bigStringWithDelimiters.split(String.valueOf(delimiter));
    }
}
