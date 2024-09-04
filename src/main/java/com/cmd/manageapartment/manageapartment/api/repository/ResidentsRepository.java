package com.cmd.manageapartment.manageapartment.api.repository;

import com.cmd.manageapartment.manageapartment.api.models.Residents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResidentsRepository extends JpaRepository<Residents, UUID> {

    List<Residents> findByApartmentId(UUID apartmentId);

    List<Residents> findByFullName(String fullName);
}