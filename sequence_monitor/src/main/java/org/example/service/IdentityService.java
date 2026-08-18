package org.example.service;

import org.example.domain.ParsedIdentity;
import org.example.exceptions.InvalidIdentityException;
import org.springframework.transaction.annotation.Transactional;
import org.example.parser.SouthAfricanIdParser;
import org.springframework.stereotype.Service;

@Service
public class IdentityService {

    private final SouthAfricanIdParser parser;

    public IdentityService(SouthAfricanIdParser parser){
        this.parser = parser;
    }

    @Transactional(readOnly = true)
    public ParsedIdentity parser(String idNumber){
        ParsedIdentity parsedIdentity = parser.parse(idNumber);

        /**
         * Structural parsing succeeded, but the checksum
         * tells us whether the complete ID is mathematically valid.
         */
        if (!parsedIdentity.isChecksumValid()){
            throw new InvalidIdentityException(
                    "South African ID failed checksum validation"
            );
        }
        return parsedIdentity;
    }

}
