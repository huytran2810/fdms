package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "room")
public class Room extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;

    @Column(name = "floor")
    private Integer floor;

    @Size(max = 20)
    @Column(name = "room_name", length = 20)
    private String roomName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id", insertable=false, updatable=false)
    private Building building;

    @Column(name = "building_id")
    private long buildingId;

    @Size(max = 200)
    @Column(name = "room_type_desciption", length = 200)
    private String room_type_desciption;

    @Column(name = "price")
    private Float price;

    @Column(name = "status")
    private String status;

    @Column(name = "room_type")
    private Long roomType;

}