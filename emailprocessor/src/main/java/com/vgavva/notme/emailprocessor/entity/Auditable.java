package com.vgavva.notme.emailprocessor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdDt", "modfdDt"},
        allowGetters = true
)
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> implements Serializable {    


	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false, updatable = false)
    @CreatedDate    
    private Date createdDt;  


	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modfd_dt", nullable = false, updatable = false)
    @CreatedDate 
    private Date modfdDt;
}
