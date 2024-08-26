package com.cmd.manageapartment.manageapartment.api.service.imp;

import com.cmd.manageapartment.manageapartment.api.models.ExtraFee;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.Fee;
import com.cmd.manageapartment.manageapartment.api.models.PaymentStatus;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.repository.FeeRepository;
import com.cmd.manageapartment.manageapartment.api.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeeServiceImplement implements FeeService {

    private final FeeRepository feeRepository;
    private final ApartmentRepository apartRepository;

    @Autowired
    public FeeServiceImplement(FeeRepository feeRepository, ApartmentRepository apartRepository) {
        this.feeRepository = feeRepository;
        this.apartRepository = apartRepository;
    }
    //no need to use this anymore
//    @Override
//    public Fee createFee(Fee fee){
//        return feeRepository.save(fee); //Save to database
//    }
    //Update:

    //Add total_extra_fee to total_amount_due
    public void createFeeForApartment(String apartmentNumber, Fee fee) {
        Apartment apartment = apartRepository.findByApartmentNumber(apartmentNumber)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));
        ExtraFee extraFee = new ExtraFee(apartment);
        extraFee.recalculateFees();
        fee.setTotal_extra_fee(extraFee.getTotalExtraFee());
        fee.setApartment(apartment);
        fee.setStatus(PaymentStatus.PENDING);
        feeRepository.save(fee);
    }

    //Get
    @Override
    public Optional<Fee> getFeeById(UUID id){
        return feeRepository.findById(id);
    }

    @Override
    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    @Override
    public Fee updateFeeById(UUID id, Fee updatedFee) {
        Fee oldFee = feeRepository.findById(id).orElseThrow(() -> new RuntimeException("Fee not found"));
        updatedFee.setId(id);
        updatedFee.setApartment(oldFee.getApartment());
        //Extra fee
        ExtraFee updateExtra = new ExtraFee(updatedFee.getApartment());
        updateExtra.recalculateFees();

        updatedFee.setTotal_extra_fee(updateExtra.getTotalExtraFee());
        updatedFee.setStatus(oldFee.getStatus());
        return feeRepository.save(updatedFee);
    }

    @Override
    public List<Fee> getFeesByApartmentNumber(String apartmentNumber) {
        Apartment apartment = apartRepository.findByApartmentNumber(apartmentNumber)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));

        return feeRepository.findByApartment_Id(apartment.getId());
    }

    @Override
    public List<Fee> getFeesByPaymentStatus(PaymentStatus status){
        return feeRepository.findAll()
                .stream().filter(fee ->fee.getStatus() == status)
                .toList();
    }

    @Override
    public void deleteFeeById(UUID id) {
        feeRepository.deleteById(id);
    }

    @Override
    public Fee updatePaymentStatusById(UUID id, PaymentStatus paymentStatus) {
        Optional<Fee> optionalFee = feeRepository.findById(id);
        if (optionalFee.isPresent()) {
            Fee fee = optionalFee.get();
            fee.setStatus(paymentStatus);
            return feeRepository.save(fee);
        } else {
            throw new RuntimeException("Fee with ID " + id + " not found.");
        }
    }

}
