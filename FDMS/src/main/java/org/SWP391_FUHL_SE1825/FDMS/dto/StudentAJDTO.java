package org.SWP391_FUHL_SE1825.FDMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAJDTO {
    private Long id;
    private String displayName;

    public StudentAJDTO(Long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
