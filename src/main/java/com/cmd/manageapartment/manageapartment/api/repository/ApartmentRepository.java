package com.cmd.manageapartment.manageapartment.api.repository;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

    Optional<Apartment> findByApartmentNumber(String apartmentNumber);

    boolean existsByApartmentNumber(String apartmentNumber);

}
