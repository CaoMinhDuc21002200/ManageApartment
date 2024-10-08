package com.cmd.manageapartment.manageapartment.api.controller;


import com.cmd.manageapartment.manageapartment.api.service.imp.ResidentNotificationServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private ResidentNotificationServiceImplement residentNotificationServiceImplement;

    @PostMapping("send-fee-notification")
    public ResponseEntity<String> sendFeeNotification() {
        residentNotificationServiceImplement.notifyResidentAboutFees();
        return ResponseEntity.ok("Fee notifications sent successfully");
    }


}
