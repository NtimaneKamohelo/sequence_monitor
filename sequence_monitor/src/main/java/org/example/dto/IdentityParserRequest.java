package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class IdentityParserRequest {

    @NotBlank(
            message = "ID number is required"
    )
    @Pattern(
            regexp = "\\d{13}",
            message = "ID number must contain exactly 13 digits"
    )
    private String idNumber;

    public IdentityParserRequest(){

    }

    public String getIdNumber(){
        return idNumber;
    }
    public void setIdNumber(String idNumber){
        this.idNumber = idNumber;
    }
}
