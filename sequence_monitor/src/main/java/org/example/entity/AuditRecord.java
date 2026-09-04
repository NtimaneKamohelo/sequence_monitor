package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_record")
public class AuditRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "identity_record_id",
            nullable = false
    )
    private Long identityRecordId;

    @Column(
            name = "operation",
            nullable = false,
            length = 50
    )
    private String operation;

    @Column(
            name = "result",
            nullable = false,
            length = 30
    )
    private String result;

    @Column(
            name = "created_at",
            nullable = false
    )
    private LocalDateTime createdAt;

    public AuditRecord(){

    }
    public AuditRecord(
            Long identityRecordId,
            String operation,
            String result
    ){
        this.identityRecordId = identityRecordId;
        this.operation = operation;
        this.result = result;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId(){
        return id;
    }

    public Long getIdentityRecordId(){
        return identityRecordId;
    }

    public String getOperation(){
        return operation;
    }

    public String getResult(){
        return result;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}
