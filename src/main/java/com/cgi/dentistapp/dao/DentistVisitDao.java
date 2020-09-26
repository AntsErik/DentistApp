package com.cgi.dentistapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<DentistVisitDTO> searchVisits(String theSearchName) {
        //Hetkeline hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query query = null;
        List<DentistVisitDTO> visits = null;

        // Otsing ainult nime järgi, juhul kui theSearchName ei ole tühi String
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // Otsingumootor arsti nime ja kuupäeva järgi ... case insensitive
            visits = entityManager.createQuery(
                    "SELECT e FROM DentistVisitEntity e " +
                            "WHERE LOWER(e.dentistName) LIKE '%"+ theSearchName.toLowerCase() + "%' " +
                            "OR e.visitTime LIKE '%" + theSearchName + "%'").getResultList();
        } else {
            // theSearchName on tühi tagasta kõik visiidid
            visits = entityManager.createQuery("SELECT e FROM DentistVisitEntity e").getResultList();
        }
        // tagasta tulemus
        return visits;
    }

    public DentistVisitEntity findById(Long theId) {

        System.out.println("-----@DAO----");
        //Hetkeline hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        System.out.println("----UURIME REGISTREERIMIST--- Tema ID on: " + theId);
        //Küsime registreeringu
        DentistVisitEntity visit = (DentistVisitEntity) entityManager.createQuery(
                "SELECT e FROM DentistVisitEntity e WHERE e.id = " + theId).getSingleResult();

        System.out.println(visit);
        //Tagastame registreeringu
        return visit;
    }

    public void deleteById(Long theId) {
        //Hetkeline hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //Kustutame visiidi vastavalt objekti primary key-le
        entityManager.createQuery("DELETE FROM DentistVisitEntity WHERE id = " + theId).executeUpdate();
    }
}

