package com.cmd.manageapartment.manageapartment.api.service;

import com.cmd.manageapartment.manageapartment.api.models.Fee;
import com.cmd.manageapartment.manageapartment.api.models.PaymentStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeeService {
    // Perform operations such as creating,
    // retrieving,
    // updating, and deleting fees.

    //Remove
//    Fee createFee(Fee fee);

    //Fix the method to update: use apartmentId
    // and {electricityUsage,waterUsage,status}
    //Remove status parameter later...
    void  createFeeForApartment(UUID apartmentId, Fee fee);

    Optional<Fee> getFeeById(UUID id);

    List<Fee> getAllFees();

    void deleteFeeById(UUID id);

    Fee updateFeeById(UUID id, Fee fee);

    Fee getFeesByApartmentId(UUID apartmentId);

    List<Fee> getFeesByPaymentStatus(PaymentStatus paymentStatus);

    Fee updatePaymentStatusById(UUID id, PaymentStatus paymentStatus);


}
