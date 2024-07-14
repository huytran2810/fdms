package org.SWP391_FUHL_SE1825.FDMS.dto.respone;

import java.time.Instant;

public interface PaymentRespone {
    Long getPaymentId();
    String getFullName();
    String getBedName();
    String getSemesterName();
    String getStatus();
    Double getAmount();
    Instant getCreatedAt();
}
