package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "electric_water_used", schema = "fdms_final")
public class ElectricWaterUsed extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "electric_water_used_id", nullable = false)
    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "semester_id")
    private Long semesterId;

    @Column(name = "electric_old")
    private Long electricOld;

    @Column(name = "water_old")
    private Long waterOld;

    @Column(name = "electric_new")
    private Long electricNew;

    @Column(name = "water_new")
    private Long waterNew;
    @Column(name="month_index")
    private Integer monthIndex;
    @Column(name="year_index")
    private Integer yearIndex;

}