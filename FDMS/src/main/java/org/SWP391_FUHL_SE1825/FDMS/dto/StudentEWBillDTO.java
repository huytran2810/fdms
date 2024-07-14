package org.SWP391_FUHL_SE1825.FDMS.dto;

import lombok.Getter;
import lombok.Setter;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.StudentEWBill;

@Getter
@Setter
public class StudentEWBillDTO implements StudentEWBill {

    private Long bedBookedId;
    private Long semesterId;
    private Long studentId;
    private Long roomId;
    private Integer month;
    private Integer year;
    private Long electricOld;
    private Long waterOld;
    private Long electricNew;
    private Long waterNew;

    public StudentEWBillDTO(Long bedBookedId, Long semesterId, Long studentId, Long roomId, Integer month, Integer year, Long electricOld, Long waterOld, Long electricNew, Long waterNew) {
        this.bedBookedId = bedBookedId;
        this.semesterId = semesterId;
        this.studentId = studentId;
        this.roomId = roomId;
        this.month = month;
        this.year = year;
        this.electricOld = electricOld;
        this.waterOld = waterOld;
        this.electricNew = electricNew;
        this.waterNew = waterNew;
    }

}
