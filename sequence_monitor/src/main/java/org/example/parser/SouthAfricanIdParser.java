package org.example.parser;

import org.example.enums.Citizenship;
import org.example.enums.Gender;

/**
 * Coordinates the parsing of a RSA ID number
 *
 * this class acts as a facade over the individual parses
 */

public class SouthAfricanIdParser {

    private final GenderParser genderParser;
    private final CitizenshipParser citizenshipParser;

    public SouthAfricanIdParser(){
        this.genderParser = new GenderParser();
        this.citizenshipParser = new CitizenshipParser();
    }

    public Gender parseGender(String idNumber){
        return genderParser.parse(idNumber);
    }

    public Citizenship parseCitizenship(String idNumber){
        return citizenshipParser.parse(idNumber);
    }
}
