package com.cgi.dentistapp.dao;


import com.cgi.dentistapp.dao.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
