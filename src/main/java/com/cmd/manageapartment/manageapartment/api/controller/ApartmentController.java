package com.cmd.manageapartment.manageapartment.api.controller;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailabilityBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;


    @Autowired
    public ApartmentController(ApartmentService apartmentService, ApplicationAvailabilityBean applicationAvailability) {
        this.apartmentService = apartmentService;
    }

    //Post
    @PostMapping("")
    public ResponseEntity<Apartment> createApartment(@Validated @RequestBody Apartment apartment) {
        Apartment createApartment = apartmentService.createApartment(apartment);
        return new ResponseEntity<>(createApartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable UUID id) {
        Optional<Apartment> apartment = apartmentService.getApartmentById(id);
            return apartment.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<Apartment>> getAllApartments() {
        List<Apartment> apartments = apartmentService.getAllApartments();
        return new ResponseEntity<>(apartments, HttpStatus.OK);
    }

    //Put
    @PutMapping("/{id}")
    public ResponseEntity<String> updateApartment(@PathVariable UUID id, @RequestBody Apartment apartment) {
      Apartment updatedApartment = apartmentService.updateApartment(id, apartment);
      return new ResponseEntity<>("Update Apartment Successfully.\n" + updatedApartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApartmentById(@PathVariable UUID id) {
        apartmentService.deleteApartmentById(id);
        return new ResponseEntity<>("Delete Successfully.", HttpStatus.NO_CONTENT);
    }



}
