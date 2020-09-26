package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    public DentistAppController(DentistVisitService theDentistVisitService) {
        dentistVisitService=theDentistVisitService;
    }

    //Mapping kõikide olemasolevate registreerimiste kuvamiseks
    @GetMapping("/visits")
    public String showAllVisits(Model theModel) {
        //Registreerimised andmebaasist
        List<DentistVisitDTO> theVisits = dentistVisitService.findAll();
        //Lisamine spring mudelile
        theModel.addAttribute("visits", theVisits);

        return "visits";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO) {
        return "form";
    }


    @PostMapping("/")
    public String postRegisterForm(@ModelAttribute("visits") DentistVisitDTO dentistVisitDTO) {

        dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitTime());

        return "results";
    }
    //Mappingu lisamine otsingumootori jaoks
    @GetMapping("/search")
    public String searchVisits(@RequestParam("theSearchName") String theSearchName, Model theModel) {

        // Otsime visiteid Service-st
        List<DentistVisitDTO> theVisits = dentistVisitService.searchVisits(theSearchName);

        // lisamine registreerimiste mudelile
        theModel.addAttribute("visits", theVisits);

        return "visits";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("visitId") Long theId, Model theModel) {
        System.out.println("----@Controller----");
        //laeme registreeringu Service-st
        DentistVisitEntity visit = dentistVisitService.findById(theId);

        //lisame registreeringu mudelina, et automaatselt täita Registreerimisvormi väljad
        theModel.addAttribute("visit", visit);

        System.out.println("Näitame registreeringut: " + visit);
        System.out.println(theModel);
        //send over to our form
        return "detail-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("visitId") Long theId) {
        //kusuta registreering
        dentistVisitService.deleteById(theId);
        //redirect to employees list
        return "redirect:/visits";
    }

}
