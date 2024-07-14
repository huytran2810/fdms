package org.SWP391_FUHL_SE1825.FDMS.dto.respone;

public interface EWResponse {
    Long getId();
    Long getRoomId();
    Long getSemesterId();
    Long getElectricOld();
    Long getWaterOld();
    Long getElectricNew();
    Long getWaterNew();
    Integer getMonth();
    Integer getYear();
}
