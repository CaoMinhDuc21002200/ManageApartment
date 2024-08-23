package com.cmd.manageapartment.manageapartment.api.service;

import com.cmd.manageapartment.manageapartment.api.models.Residents;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResidentsService {


    Residents createResidentWithApartmentId(UUID apartmentId, Residents residents);

    Optional<Residents> getResidentById(UUID residentId);

    List<Residents> getAllResidents();

    void deleteResidentById(UUID id);

    Residents updateResidentById(UUID id,Residents resident);

    List<Residents> getResidentByApartmentId(UUID apartmentId);

}