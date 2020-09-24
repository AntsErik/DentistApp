package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by serkp on 2.03.2017.
 */
public class DentistVisitDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    //Validation that letters, whitespace and "-" can only be used for the name
    //Lisa täpitähtede lubamine
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-zA-Z-\\s]+",message="tähestikutähed ja - ainult!")
    @Column(name="dentist_name")
    String dentistName;

    //Kuupäeva fomatting paigas, talletub 2020-09-09T16:00
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name="visit_time")
    LocalDateTime visitTime;

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(String dentistName, LocalDateTime visitTime) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }
}
