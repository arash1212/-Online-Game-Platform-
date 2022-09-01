package com.salehi.messaging.moduleSpecific.dto.sms;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class MessagingSmsOutput {
    private boolean succeed;
    private int successCount;
}
