package com.cmd.manageapartment.manageapartment.api.controller;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailabilityBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
@CrossOrigin(origins = "http://localhost:3000")
public class ApartmentController {

    private final ApartmentService apartmentService;


    @Autowired
    public ApartmentController(ApartmentService apartmentService, ApplicationAvailabilityBean applicationAvailability) {
        this.apartmentService = apartmentService;
    }

    //Post
    @PostMapping("")
    public ResponseEntity<Apartment> createApartment(@Validated @RequestBody Apartment apartment) {
        try {
            Apartment createApartment = apartmentService.createApartment(apartment);
            return new ResponseEntity<>(createApartment, HttpStatus.OK);

        }catch (Exception e) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
    }

    @GetMapping("/{apartmentNumber}")
    public ResponseEntity<Apartment> getApartmentByNumber(@PathVariable("apartmentNumber") String apartmentNumber) {
        Apartment apartment = apartmentService.getApartmentByNumber(apartmentNumber).orElseThrow(null);
        if (apartment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(apartment, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Apartment>> getAllApartments() {
        List<Apartment> apartments = apartmentService.getAllApartments();
        return new ResponseEntity<>(apartments, HttpStatus.OK);
    }

    //Put
    @PutMapping("/{num}")
    public ResponseEntity<String> updateApartment(@PathVariable("num") String num, @RequestBody Apartment apartment) {
      Apartment updatedApartment = apartmentService.updateApartment(num, apartment);
      return new ResponseEntity<>("Update Apartment Successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<String> deleteApartmentByApartmentNumber(@PathVariable("num") String num) {
        apartmentService.deleteApartmentByApartmentNumber(num);
        return new ResponseEntity<>("Delete Successfully.", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/temporary/{apartmentNumber}")
    public ResponseEntity<String> deleteLogicalApartmentByApartmentNumber(@PathVariable("apartmentNumber") String apartmentNumber) {
        apartmentService.deleteLogicApartmentByNumber(apartmentNumber);
        return new ResponseEntity<>("Delete Logically Successfully.", HttpStatus.OK);
    }



}
