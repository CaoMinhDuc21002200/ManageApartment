package com.cmd.manageapartment.manageapartment.api.component;


import com.cmd.manageapartment.manageapartment.api.service.imp.ResidentNotificationServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

    @Autowired
    private ResidentNotificationServiceImplement residentNotificationServiceImplement;

    @Scheduled(cron = "0 0 9 1 * ?")
    public void sendFeeNotification() {
        residentNotificationServiceImplement.notifyResidentAboutFees();
    }

}
