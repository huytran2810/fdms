package org.SWP391_FUHL_SE1825.FDMS.dto.request;


import lombok.Data;



@Data
public class BuildingCreationRequest {
    private String buildingName;
    private Integer numberFloor;
    private Integer numberRoom;
}
