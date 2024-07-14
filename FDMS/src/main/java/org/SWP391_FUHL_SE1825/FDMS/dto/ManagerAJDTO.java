package org.SWP391_FUHL_SE1825.FDMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerAJDTO {
    private Long id;
    private String displayName;

    public ManagerAJDTO(Long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
