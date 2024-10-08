package com.cmd.manageapartment.manageapartment.api.controller;

import com.cmd.manageapartment.manageapartment.api.models.Residents;
import com.cmd.manageapartment.manageapartment.api.service.ResidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/residents")
public class ResidentsController {

    private final ResidentsService residentsService;

    @Autowired
    public ResidentsController(ResidentsService residentsService) {
        this.residentsService = residentsService;
    }

    //Post
    // {fullname,sex,date_of_birth,phone,email,identity_card_number}

    @PostMapping("apartment/{apartmentNumber}")
    public ResponseEntity<Residents> createResidentWithApartmentId(@PathVariable("apartmentNumber") String apartmentNumber,@RequestBody Residents resident) {
        Residents createdResident = residentsService.createResidentWithApartmentNumber(apartmentNumber,resident);
        return new ResponseEntity<>(createdResident, HttpStatus.OK);
    }

    //Get
    @GetMapping
    public ResponseEntity<List<Residents>> getAllResidents() {
        List<Residents> residents = residentsService.getAllResidents();
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Residents> getResidentById(@PathVariable("id") UUID id) {
        Optional<Residents> resident = residentsService.getResidentById(id);
        return resident.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<Residents>> getResidentsByApartmentId(@PathVariable("apartmentId") UUID apartmentId) {
        List<Residents> residents = residentsService.getResidentByApartmentId(apartmentId);
        return ResponseEntity.ok(residents);
    }

    //Put
    @PutMapping("/{id}")
    public ResponseEntity<String> updateResidentById(@PathVariable("id") UUID id, @RequestBody Residents resident) {
        Residents updatedResident = residentsService.updateResidentById(id, resident);
        return ResponseEntity.ok("Update Resident Successfully.\n"+ updatedResident);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResidentById(@PathVariable("id") UUID id) {
        residentsService.deleteResidentById(id);
        return new ResponseEntity<>("Delete Residents Successfully!",HttpStatus.NO_CONTENT);

    }

}
