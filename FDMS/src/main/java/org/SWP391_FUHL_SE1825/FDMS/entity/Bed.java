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
@Entity
@Table(name = "bed")
public class Bed extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bed_id", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "bed_name", length = 20)
    private String bedName;

    @Column(name = "room_id")
    private Long roomId;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    @OneToMany(mappedBy = "bed")
    private Set<RequestApplication> requestApplications = new LinkedHashSet<>();
}