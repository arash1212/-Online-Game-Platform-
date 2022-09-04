package com.salehi.security.moduleSpecific.authentication.service;

import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.messaging.moduleSpecific.dto.email.MessagingEmailInput;
import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;
import com.salehi.messaging.moduleSpecific.providerManager.IMessagingProviderManager;
import com.salehi.security.model.otp.dto.SecurityOtpInput;
import com.salehi.security.model.otp.dto.SecurityOtpOutput;
import com.salehi.security.model.otp.service.SecurityOtpService;
import com.salehi.security.moduleSpecific.authentication.dto.userVerification.SecurityUserVerificationInput;
import com.salehi.user.model.user.dto.UsersOutput;
import com.salehi.user.model.user.service.UsersService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 10:01
 * @since 0.0.1
 */
@Service
public class SecurityUserVerificationService {

    private final SecurityOtpService securityOtpService;
    private final UsersService usersService;
    private final IMessagingProviderManager messagingProviderManager;

    @Autowired
    public SecurityUserVerificationService(SecurityOtpService securityOtpService, UsersService usersService, IMessagingProviderManager messagingProviderManager) {
        this.securityOtpService = securityOtpService;
        this.usersService = usersService;
        this.messagingProviderManager = messagingProviderManager;
    }

    //TODO
    public Integer sendOtp(SecurityOtpInput input) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsersOutput usersOutput = this.usersService.findByUsername(username);
        SecurityOtpOutput otp = this.securityOtpService.generateOtp(username, input.getType());

        if (input.getType().equals(MessageTypeEnum.SMS)) {
            MessagingSmsInput smsInput = this.getOtpSmsInput(usersOutput, otp);
            messagingProviderManager.sendSms(smsInput);
        } else if (input.getType().equals(MessageTypeEnum.EMAIL)) {
            MessagingEmailInput emailInput = this.getOtpEmailInput(usersOutput, otp);
            messagingProviderManager.sendEmail(emailInput);
        }

        return otp.getOtp();
    }

    public boolean verifyMobile(SecurityUserVerificationInput input) {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (loggedInUser == null)
            return false;

        SecurityOtpOutput otp = this.securityOtpService.findByOtp(input.getOtp());
        if (otp == null)
            throw new OpenApiResourceNotFoundException("otp : " + input.getOtp());
        if (!otp.getUsername().equals(loggedInUser))
            return false;

        if (otp.getType().equals(MessageTypeEnum.SMS))
            this.usersService.updateMobileConfirmationDate(loggedInUser);
        else if (otp.getType().equals(MessageTypeEnum.EMAIL))
            this.usersService.updateEmailConfirmationDate(loggedInUser);

        return true;
    }

    //TODO
    private MessagingSmsInput getOtpSmsInput(UsersOutput output, SecurityOtpOutput otp) {
        MessagingSmsInput otpSmsInput = new MessagingSmsInput();
        otpSmsInput.setTo(Collections.singletonList(output.getMobile()));
        otpSmsInput.setMessageBody("کد تایید موبایل : " + otp.getOtp());
        return otpSmsInput;
    }

    //TODO
    private MessagingEmailInput getOtpEmailInput(UsersOutput output, SecurityOtpOutput otp) {
        MessagingEmailInput otpEmailInput = new MessagingEmailInput();
        otpEmailInput.setTo(Collections.singletonList(output.getEmail()));
        otpEmailInput.setMessageBody("کد تایید ایمیل : " + otp.getOtp());
        otpEmailInput.setSubject("تایید ایمیل");
        return otpEmailInput;
    }
}
