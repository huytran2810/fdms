package org.SWP391_FUHL_SE1825.FDMS.dto.respone;

public interface BedBookedResponse {
    Long getBookedId();
    Long getBedId();
    String getBedName();
    String getSemesterName();
    String getRoomName();
    String getCheckin();
    String getPrice();
    String getStatus();
    String getCheckout();
    String getCreatedDate();
}
