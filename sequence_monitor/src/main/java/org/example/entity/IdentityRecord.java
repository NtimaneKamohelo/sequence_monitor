package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.Citizenship;
import org.example.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "identity_record",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_identity_record_id_hash",
                        columnNames = "id_hash"
                )
        }
)
@Setter
@Getter
public class IdentityRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "id_number",
            nullable = false,
            length = 13
    )
    private String idNumber;

    @Column(
            name = "id_hash",
            nullable = false,
            length = 64,
            unique = true
    )
    private String idHash;

    @Column(
            name = "birth_date",
            nullable = false
    )
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "gender",
            nullable = false,
            length = 20
    )
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "citizenship",
            nullable = false,
            length = 30
    )
    private Citizenship citizenship;

    @Column(
            name = "obsolete_digit",
            nullable = false
    )
    private int obsoleteDigit;

    @Column(
            name = "checksum_digit",
            nullable = false
    )
    private int checksumDigit;

    @Column(
            name = "checksum_valid",
            nullable = false
    )
    private boolean checksumValid;

    @Column(
            name = "created_at",
            nullable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;

    public IdentityRecord() {
    }

}
