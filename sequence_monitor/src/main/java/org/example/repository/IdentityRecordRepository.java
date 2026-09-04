package org.example.repository;

import org.example.entity.AuditRecord;
import org.example.entity.IdentityRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdentityRecordRepository extends JpaRepository<IdentityRecord, Long> {

    boolean existsByIdHash(String idHash);

    Optional<IdentityRecord> findByIdHash(String idHash);

}
