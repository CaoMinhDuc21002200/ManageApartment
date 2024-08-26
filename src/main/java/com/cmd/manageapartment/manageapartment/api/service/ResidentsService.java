package com.cmd.manageapartment.manageapartment.api.service;

import com.cmd.manageapartment.manageapartment.api.models.Residents;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResidentsService {


    Residents createResidentWithApartmentNumber(String apartmentNumber, Residents residents);

    Optional<Residents> getResidentById(UUID residentId);

    List<Residents> getAllResidents();

    List<Residents> getResidentsByName(String residentName);

    void deleteResidentById(UUID id);

    Residents updateResidentById(UUID id,Residents resident);

    List<Residents> getResidentByApartmentId(UUID apartmentId);

}