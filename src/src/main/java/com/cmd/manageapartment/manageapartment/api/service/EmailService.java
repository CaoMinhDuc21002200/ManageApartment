package com.cmd.manageapartment.manageapartment.api.service;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface EmailService {

    public void sendEmailFeeNotification(String emailAddress, String residentName, String apartmentNumber,
                                         double electricityUsage, double waterUsage, BigDecimal totalExtraFees,
                                         BigDecimal totalAmountDue);
}
