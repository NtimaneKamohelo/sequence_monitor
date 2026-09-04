package org.example.util;

public final class IdentityMaskingUtil {

    private IdentityMaskingUtil(){

    }

    public static String mask(String idNumber){
        if(idNumber == null || idNumber.length() != 13){
            return "*************";
        }

        return idNumber.substring(0,6)
                + "******"
                + idNumber.substring(12);
    }



}
