package org.SWP391_FUHL_SE1825.FDMS.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ManagerDTO {
    private Long id;
    private Long userId;
    private String avatarImage;
    private String email;
    private LocalDate dob;
    private String fullName;
    private String gender;
    private String phone;
    private String address;
    private String buildingName;
    private String status;
}
