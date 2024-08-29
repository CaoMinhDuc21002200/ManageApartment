package com.cmd.manageapartment.manageapartment.api.controller;

import com.cmd.manageapartment.manageapartment.api.models.Fee;
import com.cmd.manageapartment.manageapartment.api.models.PaymentStatus;
import com.cmd.manageapartment.manageapartment.api.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/fees")
public class FeeController {


        private final FeeService feeService;

        @Autowired
        public FeeController(FeeService feeService) {
            this.feeService = feeService;
        }

        //Post
        //Fix the method to update: use apartmentId
        // and {electricityUsage,waterUsage,status}
        //Remove status parameter later...
        @PostMapping("apartment/{apartmentNumber}")
        public ResponseEntity<String> createFeeForApartment(@PathVariable String apartmentNumber,@RequestBody Fee fee){
            feeService.createFeeForApartment(apartmentNumber,fee);
            return new ResponseEntity<>("Created Fee Successfully.", HttpStatus.CREATED);
        }

        //Get
        @GetMapping("/{id}")
        public ResponseEntity<Fee> getGeeById(@PathVariable UUID id){

            Optional<Fee> fee = feeService.getFeeById(id);
            return fee.map(ResponseEntity::ok).orElseGet(
                    () -> ResponseEntity.notFound().build());
        }

        @GetMapping()
        public ResponseEntity<List<Fee>> getAllFees(){
            List<Fee> fees = feeService.getAllFees();
            return ResponseEntity.ok(fees);

        }



        @GetMapping("/apartment/{apartmentNumber}")
        public ResponseEntity<List<Fee>> getFeesByApartmentNumber(@PathVariable String apartmentNumber) {
        List<Fee> fees = feeService.getFeesByApartmentNumber(apartmentNumber);
        if (fees == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fees);

    }
        @GetMapping("/payment-status/{status}")
        public ResponseEntity<List<Fee>> getFeesByPaymentStatus(@PathVariable PaymentStatus status) {
        List<Fee> fees = feeService.getFeesByPaymentStatus(status);
        if (fees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fees);
    }


        //Put
        @PutMapping("/{id}")
        public ResponseEntity<String> updateFeeById(@PathVariable("id") UUID id,@RequestBody Fee feeDetails){
            Fee updatedFee = feeService.updateFeeById(id,feeDetails);
            return new ResponseEntity<>("Updated Fee Successfully."
                    , HttpStatus.OK);
        }

        @PutMapping("/{id}/payment-status")
        public ResponseEntity<Fee> updatePaymentStatusById(@PathVariable UUID id, @RequestParam PaymentStatus status) {
        Fee updatedFee = feeService.updatePaymentStatusById(id, status);
        return ResponseEntity.ok(updatedFee);
        }




        //Delete
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteFeeById(@PathVariable UUID id){
            feeService.deleteFeeById(id);
            return new ResponseEntity<>("Delete Successfully.",HttpStatus.NO_CONTENT);
        }




}
