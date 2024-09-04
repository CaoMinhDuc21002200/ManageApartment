package com.cmd.manageapartment.manageapartment.api.service;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.ExtraFee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExtraFeeService {


    ExtraFee createExtraFeeByApartmentId(UUID apartmentId);

    Optional<ExtraFee> getExtraFeeById(UUID id);

    List<ExtraFee> getAllExtraFees();

    void deleteExtraFeeById(UUID id);

    ExtraFee updateExtraFee(UUID id,ExtraFee extraFee);

    List<ExtraFee> getExtraFeeByApartmentId(UUID apartmentId);

}
