CREATE TABLE identity_record(
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_number VARCHAR(13) NOT null,
    id_hash VARCHAR(64) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(20) NOT NULL,
    citizenship VARCHAR(30) NOT NULL,
    obsolete_digit TINYINT NOT NULL,
    checksum_digit TINYINT NOT NULL,
    created_at TIMESTAMP NOT NULL default current_timestamp,
    updated_at TIMESTAMP NOT NULL default current_timestamp
        ON UPDATE current_timestamp,

    PRIMARY KEY (id),

    CONSTRAINT uk_identity_record_id_hash
        UNIQUE (id_hash)
);

CREATE TABLE audit_record(
    id BIGINT NOT NULL auto_increment,
    identity_record_id BIGINT NOT NULL,
    operation VARCHAR(50) NOT NULL,
    result VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL default  current_timestamp ,

    PRIMARY KEY (id),

    CONSTRAINT fk_audit_identity
        FOREIGN KEY (identity_record_id)
        REFERENCES identity_record(id)
);