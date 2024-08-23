package com.cmd.manageapartment.manageapartment.api.service.imp;

import com.cmd.manageapartment.manageapartment.api.models.Fee;
import com.cmd.manageapartment.manageapartment.api.models.Residents;
import com.cmd.manageapartment.manageapartment.api.repository.FeeRepository;
import com.cmd.manageapartment.manageapartment.api.repository.ResidentsRepository;
import com.cmd.manageapartment.manageapartment.api.service.EmailService;
import com.cmd.manageapartment.manageapartment.api.service.ResidentNotificationService;
import com.cmd.manageapartment.manageapartment.api.service.ResidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        for (Residents resident : residents) {
            Fee fee = feeRepository.findByApartment_Id(resident.getApartment().getId());
            if (fee != null && resident.getRelationshipToOwner().equals("owner")) {
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
        }
    }




}
