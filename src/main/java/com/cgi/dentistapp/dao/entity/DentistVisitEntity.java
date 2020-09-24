package com.cgi.dentistapp.dao.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="visits")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="dentist_name")
    public String dentistName;

    @Column(name="visit_time")
    public Timestamp visitTime;

    public DentistVisitEntity() {
    }

    public DentistVisitEntity(Long id, String dentistName, Timestamp visitTime) {
        this.id = id;
        this.dentistName = dentistName;
        this.visitTime = visitTime;
    }

    public DentistVisitEntity(String dentistName, Timestamp visitTime) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return dentistName;
    }

    public void setName(String name) {
        this.dentistName = name;
    }

    public Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }

    @Override
    public String toString() {
        return "DentistVisitEntity{" +
                "id=" + id +
                ", dentistName='" + dentistName + '\'' +
                ", visitTime=" + visitTime +
                '}';
    }
}
