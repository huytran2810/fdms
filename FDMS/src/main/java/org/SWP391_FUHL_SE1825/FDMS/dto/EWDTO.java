package org.SWP391_FUHL_SE1825.FDMS.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EWDTO {
    private Long roomId;
    private Long semesterId;
    private String semesterName;
    private int month;
    private int year;
    private Long electric;
    private Long water;
}
