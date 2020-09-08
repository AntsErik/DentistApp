package com.cgi.dentistapp.dao;


import com.cgi.dentistapp.dao.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<DoctorEntity, Integer> {
}
