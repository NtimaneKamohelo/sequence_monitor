package org.example.validation;

import java.util.regex.Pattern;

public class RegexValidator {

    private static final Pattern SA_ID_PATTERN = Pattern.compile("^\\d{13}$");

    public RegexValidator(){

    }
    public static boolean isValid(String idNumber){

        if (idNumber == null){
            return false;
        }
        return SA_ID_PATTERN.matcher(idNumber).matches();
    }
}
