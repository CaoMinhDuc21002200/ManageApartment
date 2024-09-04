//package com.cmd.manageapartment.manageapartment.api.controller;
//
//import com.cmd.manageapartment.manageapartment.api.models.Resident;
//import com.cmd.manageapartment.manageapartment.api.service.ResidentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/residents")
//public class ResidentController {
//
//    private ResidentService residentService;
//
//    @Autowired
//    public ResidentController(ResidentService residentService) {
//        this.residentService = residentService;
//    }
//
//    //Post
//    @PostMapping
//    public ResponseEntity<Resident> createResident(@RequestBody Resident resident) {
//        Resident createdResident = residentService.createResident(resident);
//        return new ResponseEntity<>(createdResident, HttpStatus.CREATED);
//    }
//
//    //Get
//    @GetMapping
//    public ResponseEntity<List<Resident>> getAllResidents() {
//        List<Resident> residents = residentService.getAllResidents();
//        return new ResponseEntity<>(residents, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Resident> getResidentById(@PathVariable UUID id) {
//        Optional<Resident> resident = residentService.getResidentById(id);
//        return resident.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @GetMapping("/apartment/{apartmentId}")
//    public ResponseEntity<List<Resident>> getResidentsByApartmentId(@PathVariable UUID apartmentId) {
//        List<Resident> residents = residentService.getResidentsByApartmentId(apartmentId);
//        return ResponseEntity.ok(residents);
//    }
//
//    //Put
//    @PutMapping("/{id}")
//    public ResponseEntity<Resident> updateResident(@PathVariable UUID id, @RequestBody Resident resident) {
//        Resident updatedResident = residentService.updateResident(id, resident);
//        return ResponseEntity.ok(updatedResident);
//    }
//
//    //Delete
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Resident> deleteResidentById(@PathVariable UUID id) {
//        residentService.deleteResidentById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}
