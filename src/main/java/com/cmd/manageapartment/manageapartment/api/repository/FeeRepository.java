package com.cmd.manageapartment.manageapartment.api.repository;

import com.cmd.manageapartment.manageapartment.api.models.Fee;
import com.cmd.manageapartment.manageapartment.api.models.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeeRepository extends JpaRepository<Fee, UUID> {

    List<Fee> findByApartment_Id(UUID apartmentId);


    List<Fee> findByStatus(PaymentStatus status);
}
