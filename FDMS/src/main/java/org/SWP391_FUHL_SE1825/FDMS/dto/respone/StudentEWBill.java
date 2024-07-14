package org.SWP391_FUHL_SE1825.FDMS.dto.respone;

public interface StudentEWBill {
    Long getBedBookedId();
    Long getSemesterId();
    Long getStudentId();
    Long getRoomId();
    Integer getMonth();
    Integer getYear();
    Long getElectricOld();
    Long getWaterOld();
    Long getElectricNew();
    Long getWaterNew();
}
