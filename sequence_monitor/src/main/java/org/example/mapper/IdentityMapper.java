package org.example.mapper;

import org.example.domain.ParsedIdentity;
import org.example.dto.IdentityParserRequest;
import org.example.dto.IdentityParserResponse;
import org.springframework.stereotype.Component;

@Component
public class IdentityMapper {

    public IdentityParserResponse toResponse(
            ParsedIdentity identity
    ){
        IdentityParserResponse response = new IdentityParserResponse();

        response.setIdNumber(
                maskIdNumber(
                        identity.getIdNumber()
                )
        );

        response.setBirthDate(
                identity.getBirthDate()
        );

        response.setAge(
                identity.getAge()
        );

        response.setGender(
                identity.getGender()
        );

        response.setCitizenship(
                identity.getCitizenship()
        );

        response.setCitizenshipDescription(
                identity
                        .getCitizenship()
                        .getDescription()
        );

        response.setObsoleteDigit(
                identity.getObsoleteDigit()
        );

        response.setChecksumDigit(
                identity.getChecksumDigit()
        );

        response.setChecksumValid(
                identity.isChecksumValid()
        );

        response.setValid(
                identity.isValid()
        );

        return response;
    }

    /**
     * Masks the sensitive ID number before returning it
     * from the API
     *
     * example: 9003155800084
     * becomes: 900315*****84
     */
    private String maskIdNumber(String idNumber){
        if (idNumber == null || idNumber.length() != 13) {
            return null;
        }
        return idNumber.substring(0,6)
                + "******"
                + idNumber.substring(12);
    }

}
