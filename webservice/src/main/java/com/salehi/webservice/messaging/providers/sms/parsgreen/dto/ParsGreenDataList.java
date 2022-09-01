package com.salehi.webservice.messaging.providers.sms.parsgreen.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class ParsGreenDataList {
    private Integer sendStatus;
    private String mobile;
    private String reqID;
}
