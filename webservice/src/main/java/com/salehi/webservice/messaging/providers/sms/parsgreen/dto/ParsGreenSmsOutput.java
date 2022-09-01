package com.salehi.webservice.messaging.providers.sms.parsgreen.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class ParsGreenSmsOutput {
    private Integer SuccessCount;
    private List<ParsGreenDataList> DataList;
    private boolean R_Success;
    private Integer R_Code;
    private String R_Message;

    private String response;
}
