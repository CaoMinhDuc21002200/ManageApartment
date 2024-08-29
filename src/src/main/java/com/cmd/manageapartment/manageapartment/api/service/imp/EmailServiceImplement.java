package com.cmd.manageapartment.manageapartment.api.service.imp;


import com.cmd.manageapartment.manageapartment.api.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.util.Properties;


@Service
public class EmailServiceImplement implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailServiceImplement(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;

        //Debug email
        if (mailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) this.mailSender;
            Properties props = mailSenderImpl.getJavaMailProperties();
            props.put("mail.debug", "true");
        }
    }

    @Override
    public void sendEmailFeeNotification(String emailAddress,
                                         String residentName,
                                         String apartmentNumber,
                                         double electricityUsage,
                                         double waterUsage,
                                         BigDecimal totalExtraFees,
                                         BigDecimal totalAmountDue) {
        //Debug


        Context context = new Context();
        context.setVariable("residentName", residentName);
        context.setVariable("apartmentNumber", apartmentNumber);
        context.setVariable("electricityUsage", electricityUsage);
        context.setVariable("waterUsage", waterUsage);
        context.setVariable("totalExtraFees", totalExtraFees);
        context.setVariable("totalAmountDue", totalAmountDue);

        String htmlContent = templateEngine.process("email/feeNotification", context);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(emailAddress);
            helper.setSubject("Apartment Fee Notification");
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }

    }
}
