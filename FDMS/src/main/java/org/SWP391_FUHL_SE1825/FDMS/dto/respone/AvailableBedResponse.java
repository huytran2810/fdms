package org.SWP391_FUHL_SE1825.FDMS.dto.respone;

public interface AvailableBedResponse {
    int getBuildingId();
    String getBuildingName();
    int getRoomId();
    String getRoomName();
    int getTotalBed();
    int getAvailableBed();
    int getUsedBed();
}

