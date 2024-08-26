package com.cmd.manageapartment.manageapartment.api.service;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApartmentService {

    Apartment createApartment(Apartment apartment);

    Optional<Apartment> getApartmentById(UUID id);

    Optional<Apartment> getApartmentByNumber(String apartmentNumber);

    void deleteLogicApartmentByNumber(String apartmentNumber);

    List<Apartment> getAllApartments();

    void deleteApartmentByApartmentNumber(String apartmentNumber);

    Apartment updateApartment(String apartmentNumber, Apartment apartment);
}
