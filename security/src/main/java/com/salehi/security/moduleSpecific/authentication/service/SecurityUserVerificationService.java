package com.salehi.security.moduleSpecific.authentication.service;

import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;
import com.salehi.messaging.moduleSpecific.providerManager.IMessagingProviderManager;
import com.salehi.security.model.mobileOtp.dto.SecurityMobileOtpOut;
import com.salehi.security.model.mobileOtp.service.SecurityMobileOtpService;
import com.salehi.security.moduleSpecific.authentication.dto.userVerification.SecurityUserMobileVerificationInput;
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

    private final SecurityMobileOtpService mobileOtpService;
    private final UsersService usersService;
    private final IMessagingProviderManager messagingProviderManager;

    @Autowired
    public SecurityUserVerificationService(SecurityMobileOtpService mobileOtpService, UsersService usersService, IMessagingProviderManager messagingProviderManager) {
        this.mobileOtpService = mobileOtpService;
        this.usersService = usersService;
        this.messagingProviderManager = messagingProviderManager;
    }

    //TODO bayad login bashe
    public Integer requestOtp() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsersOutput usersOutput = this.usersService.findByUsername(username);
        SecurityMobileOtpOut otp = this.mobileOtpService.generateOtp(username);

        MessagingSmsInput smsInput = this.getOtpSmsInput(usersOutput, otp);
        messagingProviderManager.sendSms(smsInput);
        System.out.println(otp);
        return otp.getOtp();
    }

    //TODO bayad login bashe
    public boolean verifyMobile(SecurityUserMobileVerificationInput input) {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (loggedInUser == null)
            return false;

        SecurityMobileOtpOut mobileOtp = this.mobileOtpService.findByOtp(input.getOtp());
        if (mobileOtp == null)
            throw new OpenApiResourceNotFoundException("otp : " + input.getOtp());
        if (!mobileOtp.getUsername().equals(loggedInUser))
            return false;

        this.usersService.updateMobileConfirmationDate(loggedInUser);
        return true;
    }

    private MessagingSmsInput getOtpSmsInput(UsersOutput output, SecurityMobileOtpOut otp) {
        MessagingSmsInput otpSmsInput = new MessagingSmsInput();
        otpSmsInput.setTo(Collections.singletonList(output.getMobile()));
        otpSmsInput.setMessageBody("کد تایید موبایل : " + otp.getOtp());
        return otpSmsInput;
    }
}
