package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class Base {
//    @Id
//    @Column(name = "id",columnDefinition = "VARCHAR(36)")
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @JdbcTypeCode(SqlTypes.VARCHAR)
//    protected String id;


    @Column(name = "created_at" )
    @CreationTimestamp
    protected Instant createdAt;


    @Column(name = "updated_at" )
    @CreationTimestamp
    protected Instant updatedAt;
}
