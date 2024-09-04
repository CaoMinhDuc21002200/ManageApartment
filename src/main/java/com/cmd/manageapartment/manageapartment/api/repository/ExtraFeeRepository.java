package com.cmd.manageapartment.manageapartment.api.repository;

import com.cmd.manageapartment.manageapartment.api.models.ExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExtraFeeRepository extends JpaRepository<ExtraFee, UUID> {

    List<ExtraFee> findByApartmentId(UUID apartmentId);
}
