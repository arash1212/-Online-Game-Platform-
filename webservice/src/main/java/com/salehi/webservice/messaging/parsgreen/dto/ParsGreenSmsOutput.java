package com.salehi.webservice.messaging.parsgreen.dto;

import com.salehi.webservice.messaging.interfaces.dto.sms.ISmsOutput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParsGreenSmsOutput implements ISmsOutput {
//    private Integer SuccessCount;
//    private List<ParsGreenDataList> DataList;
//    private boolean R_Success;
//    private Integer R_Code;
//    private String R_Message;

    private String response;
}
