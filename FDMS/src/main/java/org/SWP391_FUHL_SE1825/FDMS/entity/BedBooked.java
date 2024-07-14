package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "bed_booked")
public class BedBooked extends Base{
    @Id
    @Column(name = "bed_booked_id", nullable = false)
    private Long id;
    @Column(name = "bed_id")
    private Long bedId;

    @Column(name = "semester_id")
    private Long semesterId;

    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "status")
    private String status;

    @ColumnDefault("0")
    @Column(name = "amount_subvention")
    private Float amountSubvention;

}
