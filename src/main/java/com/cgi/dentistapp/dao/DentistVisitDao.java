package com.cgi.dentistapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;

import java.util.List;

@Repository
public class DentistVisitDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(DentistVisitEntity visit) {
        entityManager.persist(visit);
    }

    public List<DentistVisitEntity> getAllVisits() {
        return entityManager.createQuery("SELECT e FROM visits e").getResultList();
    }

    public void saveVisit(DentistVisitEntity visit) {
        //Hetkeline hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //Uue registreerimise salvestamine või eksisteeriva uuendamine sõltuvalt sellest, kas ID on null
        currentSession.saveOrUpdate(visit);
    }

    public List<DentistVisitDTO> findAll() {
        return entityManager.createQuery("SELECT e FROM DentistVisitEntity e").getResultList();
    }
}
