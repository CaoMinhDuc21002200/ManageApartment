package com.cmd.manageapartment.manageapartment.api.controller;


import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.ExtraFee;
import com.cmd.manageapartment.manageapartment.api.service.ExtraFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/extra-fees")
public class ExtraFeeController {

    private final ExtraFeeService extraFeeService;

    @Autowired
    public ExtraFeeController(ExtraFeeService extraFeeService) {
        this.extraFeeService = extraFeeService;
    }
    //Post
    @PostMapping("{apartmentId}")
    public ResponseEntity<ExtraFee> createExtraFee(@PathVariable UUID apartmentId){
        ExtraFee createdExtraFee = extraFeeService.createExtraFeeByApartmentId(apartmentId);
        return new ResponseEntity<>(createdExtraFee,HttpStatus.CREATED);
    }
    //Get
    @GetMapping()
    public ResponseEntity<List<ExtraFee>> getAllExtraFees(){
        List<ExtraFee> extraFees = extraFeeService.getAllExtraFees();
        return new ResponseEntity<>(extraFees,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtraFee> getExtraFeeById(@PathVariable UUID id){
        Optional<ExtraFee> extraFee = extraFeeService.getExtraFeeById(id);
        return extraFee.map(fee -> new ResponseEntity<>(fee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<ExtraFee>> getExtraFeesByApartmentId(@PathVariable UUID apartmentId) {
        List<ExtraFee> extraFees = extraFeeService.getExtraFeeByApartmentId(apartmentId);
        return ResponseEntity.ok(extraFees);
    }




    //Put
    @PutMapping("/{id}")
    public ResponseEntity<ExtraFee> updateExtraFee(@PathVariable UUID id,@RequestBody ExtraFee extraFee){
        ExtraFee updatedExtraFee = extraFeeService.updateExtraFee(id, extraFee);
        return new ResponseEntity<>(updatedExtraFee,HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ExtraFee> deleteExtraFee(@PathVariable UUID id){
        extraFeeService.deleteExtraFeeById(id);
        return ResponseEntity.noContent().build();
    }


}
