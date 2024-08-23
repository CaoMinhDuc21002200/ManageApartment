package com.cmd.manageapartment.manageapartment.api.service;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApartmentService {

    Apartment createApartment(Apartment apartment);

    Optional<Apartment> getApartmentById(UUID id);

    List<Apartment> getAllApartments();

    void deleteApartmentById(UUID id);

    Apartment updateApartment(UUID id, Apartment apartment);
}
