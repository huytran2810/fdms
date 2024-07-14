package org.SWP391_FUHL_SE1825.FDMS.dto.respone;

public interface BuildingListResponse {
    long getBuildingId();
    String getBuildingName();
    int getNumberOfFloors();
    String getStatus();
    int getRoomCount();
    int getAvailableRoomCount();
}
