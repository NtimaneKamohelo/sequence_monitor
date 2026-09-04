package org.example.service;

import org.example.domain.ParsedIdentity;
import org.example.entity.AuditRecord;
import org.example.entity.IdentityRecord;
import org.example.exceptions.InvalidIdentityException;
import org.example.mapper.IdentityRecordMapper;
import org.example.repository.AuditRecordRepository;
import org.example.repository.IdentityRecordRepository;
import org.example.security.IdentityHashService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.example.parser.SouthAfricanIdParser;
import org.springframework.stereotype.Service;

@Service
public class IdentityService {

    private final SouthAfricanIdParser parser;
    private final IdentityRecordRepository identityRepository;
    private final AuditRecordRepository auditRepository;
    private final IdentityRecordMapper identityRecordMapper;
    private final IdentityHashService hashService;

    public IdentityService(
            SouthAfricanIdParser parser,
            IdentityRecordRepository identityRepository,
            AuditRecordRepository auditRepository,
            IdentityRecordMapper identityRecordMapper,
            IdentityHashService hashService
    ){
        this.parser = parser;
        this.identityRepository = identityRepository;
        this.auditRepository = auditRepository;
        this.identityRecordMapper = identityRecordMapper;
        this.hashService = hashService;
    }

    @Transactional
    public ParsedIdentity parserAndRegister(String idNumber){

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

        String hash = hashService.hash(idNumber);

        /**
         * Application level duplicate check
         */
        if(identityRepository.existsByIdHash(hash)) {
            throw new InvalidIdentityException(
                    "This identity number has already been registered"
            );
        }

        IdentityRecord entity = identityRecordMapper.toEntity(
                parsedIdentity
        );

        try {
            IdentityRecord saved = identityRepository.save(entity);

            AuditRecord audit = new AuditRecord(
                    saved.getId(),
                    "IDENTITY_REGISTER",
                    "SUCCESS"
            );

            auditRepository.save(audit);
            return parsedIdentity;

        } catch (DataIntegrityViolationException exception) {

            /**
             * This catches a duplicate that may have
             * occured because another request inserted
             * the same identity concurrently.
             */
            throw new InvalidIdentityException(
                    "This identity number has already been registered"
            );

        }
    }
}
