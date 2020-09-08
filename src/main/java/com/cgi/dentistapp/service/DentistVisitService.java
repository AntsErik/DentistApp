package com.cgi.dentistapp.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.dentistapp.dao.DentistVisitDao;
import com.cgi.dentistapp.dao.entity.DentistVisitEntity;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private DentistVisitDao dentistVisitDao;

    public void addVisit(String dentistName, LocalDateTime visitTime) {
        DentistVisitEntity visit = new DentistVisitEntity(dentistName, visitTime);
        System.out.println("EIKE: ----- print regamine ----");
        System.out.println("dentistName " + dentistName);
        System.out.println("visitTime " + visitTime);
        dentistVisitDao.create(visit);
    }

    public List<DentistVisitEntity> listVisits () {
        return dentistVisitDao.getAllVisits();
    }

}
