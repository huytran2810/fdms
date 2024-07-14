package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "building")
public class Building extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "building_name")
    private String buildingname;

    @Column(name = "number_floor")
    private Integer numberFloor;

    @Column(name = "number_room_of_floor")
    private Integer numberRoomOfFloor;

    @Column(name = "status")
    private String status;


    @OneToMany(mappedBy = "building")
    private Set<ManagerSecondaryInformation> managerSecondaryInformations = new LinkedHashSet<>();
}