package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "semester")
public class Semester extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id", nullable = false)
    private Long id;


    @Column(name = "end_date")
    private LocalDate endDate;

    @Size(max = 255)
    @Column(name = "semester_name")
    private String semesterName;

    @Column(name = "start_date")
    private LocalDate startDate;
    public boolean isDateWithinRange(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }

}