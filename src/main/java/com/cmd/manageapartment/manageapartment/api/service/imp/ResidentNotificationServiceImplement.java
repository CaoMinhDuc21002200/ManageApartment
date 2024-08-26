package com.cmd.manageapartment.manageapartment.api.service.imp;

import com.cmd.manageapartment.manageapartment.api.models.Fee;
import com.cmd.manageapartment.manageapartment.api.models.Residents;
import com.cmd.manageapartment.manageapartment.api.repository.FeeRepository;
import com.cmd.manageapartment.manageapartment.api.repository.ResidentsRepository;
import com.cmd.manageapartment.manageapartment.api.service.EmailService;
import com.cmd.manageapartment.manageapartment.api.service.ResidentNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ResidentNotificationServiceImplement implements ResidentNotificationService {

    private final ResidentsRepository residentsRepository;

    private final FeeRepository feeRepository;

    private final EmailService emailService;

    @Autowired
    public ResidentNotificationServiceImplement(ResidentsRepository residentsRepository,
                                                FeeRepository feeRepository,
                                                EmailService emailService) {
        this.residentsRepository = residentsRepository;
        this.feeRepository = feeRepository;
        this.emailService = emailService;
    }

    @Override
    public void notifyResidentAboutFees (){

        List<Residents> residents = residentsRepository.findAll();
        Set<UUID> processedApartments = new HashSet<>();

        for (Residents resident : residents) {
            UUID apartmentId = resident.getApartment().getId();

            if (processedApartments.contains(apartmentId)) {
                continue; 
            }

            if (resident.getRelationshipToOwner().equals("owner")){
                List<Fee> fees = feeRepository.findByApartment_Id(apartmentId);
                if (fees != null && !fees.isEmpty()) {
                    for (Fee fee : fees) {
                        emailService.sendEmailFeeNotification(
                                resident.getEmail(),
                                resident.getFullName(),
                                resident.getApartment().getApartmentNumber(),
                                fee.getElectricityUsage(),
                                fee.getWaterUsage(),
                                fee.getTotal_extra_fee(),
                                fee.getTotal_amount_due()
                                );
                    }
                    processedApartments.add(apartmentId);
                }
            }
        }
    }




}
