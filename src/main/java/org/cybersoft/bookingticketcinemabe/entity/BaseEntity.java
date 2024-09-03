package org.cybersoft.bookingticketcinemabe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonIgnore
    private Instant updatedAt;
}
