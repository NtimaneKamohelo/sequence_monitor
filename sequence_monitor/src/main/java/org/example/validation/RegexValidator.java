package org.example.validation;

import java.util.regex.Pattern;

public class RegexValidator {

    private static final Pattern SA_ID_PATTERN = Pattern.compile("^\\d{13}$");
    public static boolean isValid(String id){
        return SA_ID_PATTERN.matcher(id).matches();
    }
}
