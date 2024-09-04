package com.cmd.manageapartment.manageapartment.api.controller;


import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.Residents;
import com.cmd.manageapartment.manageapartment.api.request.NameRequest;
import com.cmd.manageapartment.manageapartment.api.response.ApartmentWithResidentsResponse;
import com.cmd.manageapartment.manageapartment.api.service.ApartmentService;
import com.cmd.manageapartment.manageapartment.api.service.ResidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/search")
public class SearchController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final ApartmentService apartmentService;

    private final ResidentsService residentsService;

    @Autowired
    public SearchController(ApartmentService apartmentService,
                            ResidentsService residentsService) {
        this.apartmentService = apartmentService;
        this.residentsService = residentsService;
    }

    @GetMapping("name")
    public ResponseEntity<List<Residents>> searchByResidentsName(@Validated @RequestBody NameRequest nameRequest) {
        List<Residents> foundResident = residentsService.getResidentsByName(nameRequest.getFullname());
        logger.info("Residents found: " + foundResident);
        if(foundResident == null||foundResident.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(foundResident, HttpStatus.OK);
    }

    @GetMapping("apartment/{apartmentNumber}")
    public ResponseEntity<ApartmentWithResidentsResponse> searchByApartmentNumber(@Validated @PathVariable String apartmentNumber) {
        Optional<Apartment> apartmentOptional = apartmentService.getApartmentByNumber(apartmentNumber);

        if (apartmentOptional.isPresent()) {
            Apartment apartment = apartmentOptional.get();
            List<Residents> residentsInApartment = residentsService.getResidentByApartmentId(apartment.getId());
            ApartmentWithResidentsResponse response = new ApartmentWithResidentsResponse(apartment, residentsInApartment);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}
