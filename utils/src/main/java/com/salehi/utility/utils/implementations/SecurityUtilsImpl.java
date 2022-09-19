package com.salehi.utility.utils.implementations;

import com.salehi.utility.utils.interfaces.ISecurityUtils;
import org.springframework.stereotype.Component;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-01 23:44
 * @since 0.0.1
 */
@Component
public class SecurityUtilsImpl implements ISecurityUtils {
    public String generateOTP() {
        int otp = (int) (Math.random() * 9000) + 100000;
        return String.valueOf(otp);
    }
}
