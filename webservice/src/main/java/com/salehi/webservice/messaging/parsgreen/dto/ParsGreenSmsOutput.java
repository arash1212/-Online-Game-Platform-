package com.salehi.webservice.messaging.parsgreen.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
