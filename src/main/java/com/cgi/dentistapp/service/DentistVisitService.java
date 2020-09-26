package com.cgi.dentistapp.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.cgi.dentistapp.dto.DentistVisitDTO;
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
        Timestamp visitTimeStamp = Timestamp.valueOf(visitTime);
        DentistVisitEntity visit = new DentistVisitEntity(dentistName, visitTimeStamp);
        dentistVisitDao.create(visit);
        dentistVisitDao.saveVisit(visit);
    }

    public List<DentistVisitEntity> listVisits () {
        return dentistVisitDao.getAllVisits();
    }

    public List<DentistVisitDTO> findAll() {
        return dentistVisitDao.findAll();
    }

    //Delegeerime otsingu edasi DAO-sse
    public List<DentistVisitDTO> searchVisits(String theSearchName) {
        return dentistVisitDao.searchVisits(theSearchName);
    }

    //Delegeerime otsingu Primary Key järgi edasi DAO-sse
    public DentistVisitEntity findById(Long theId) {
        return dentistVisitDao.findById(theId);
    }

    //Delegeerime kustutamise edasi DAO-sse
    public void deleteById(Long theId) {
        dentistVisitDao.deleteById(theId);
    }
}
