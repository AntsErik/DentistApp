package com.cgi.dentistapp.dao.entity;

import javax.persistence.*;

@Entity
@Table( name = "doctor")
public class DoctorEntity {

    //Defining fields
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private int id;

    @Column( name = "first_name")
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    //Defining constructors
    public DoctorEntity() {
    }

    public DoctorEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getter-Setter methods

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //To-String method
    @Override
    public String toString(){
        return "Doctor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
