package com.cmd.manageapartment.manageapartment.api.service.imp;


import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.ExtraFee;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.repository.ExtraFeeRepository;
import com.cmd.manageapartment.manageapartment.api.service.ExtraFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExtraFeeServiceImplement implements ExtraFeeService {

    private final ExtraFeeRepository extraFeeRepository;
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ExtraFeeServiceImplement(ExtraFeeRepository extraFeeRepository, ApartmentRepository apartmentRepository) {
        this.extraFeeRepository = extraFeeRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public ExtraFee createExtraFeeByApartmentId(UUID apartmentId) {
        if (apartmentRepository.findById(apartmentId).isPresent()) {
            Apartment apartment = apartmentRepository.findById(apartmentId).get();

            ExtraFee extraFee = new ExtraFee(apartment);
            extraFee.recalculateFees();
            return extraFeeRepository.save(extraFee);
        }
        else throw new RuntimeException("Apartment not found");

    }


    @Override
    public List<ExtraFee> getAllExtraFees() {
        return extraFeeRepository.findAll();
    }


    @Override
    public Optional<ExtraFee> getExtraFeeById(UUID id) {
        return extraFeeRepository.findById(id);
    }

    @Override
    public ExtraFee updateExtraFee(UUID id, ExtraFee extraFee) {
        ExtraFee existingExtraFee = extraFeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ExtraFee not found with ID: " + id));
        existingExtraFee.setGeneralHygieneFee(extraFee.getGeneralHygieneFee());
        existingExtraFee.setElevatorMaintenanceFee(extraFee.getElevatorMaintenanceFee());
        existingExtraFee.setParkingFee(extraFee.getParkingFee());
        existingExtraFee.setTotalExtraFee(extraFee.getTotalExtraFee());
        return extraFeeRepository.save(existingExtraFee);
    }

    @Override
    public List<ExtraFee> getExtraFeeByApartmentId(UUID apartmentId) {
        return extraFeeRepository.findByApartmentId(apartmentId);
    }
    @Override
    public void deleteExtraFeeById(UUID id) {
        extraFeeRepository.deleteById(id);
    }

}
