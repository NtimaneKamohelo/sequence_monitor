package org.example.mapper;

import org.example.domain.ParsedIdentity;
import org.example.entity.IdentityRecord;
import org.example.security.IdentityHashService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class IdentityRecordMapper {

    private final IdentityHashService hashService;

    public IdentityRecordMapper(
            IdentityHashService hashService
    ){
        this.hashService = hashService;
    }

    public IdentityRecord toEntity(
            ParsedIdentity parsedIdentity
    ){
        IdentityRecord record = new IdentityRecord();

        record.setIdNumber(
                parsedIdentity.getIdNumber()
        );

        record.setIdHash(
                hashService.hash(
                        parsedIdentity.getIdNumber()
                )
        );

        record.setBirthDate(
                parsedIdentity.getBirthDate()
        );

        record.setGender(
                parsedIdentity.getGender()
        );

        record.setCitizenship(
                parsedIdentity.getCitizenship()
        );

        record.setObsoleteDigit(
                parsedIdentity.getObsoleteDigit()
        );

        record.setChecksumDigit(
                parsedIdentity.getChecksumDigit()
        );

        record.setChecksumValid(
                parsedIdentity.isChecksumValid()
        );

        record.setCreatedAt(
                LocalDateTime.now()
        );

        record.setUpdatedAt(
                LocalDateTime.now()
        );
        return record;
    }

}
