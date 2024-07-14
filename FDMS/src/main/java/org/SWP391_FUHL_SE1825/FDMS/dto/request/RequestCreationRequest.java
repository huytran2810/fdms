package org.SWP391_FUHL_SE1825.FDMS.dto.request;

import lombok.Data;

@Data
public class RequestCreationRequest {
    int type;
    float price;
    String content;
}
