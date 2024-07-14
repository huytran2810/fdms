package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admin_secondary_information")
public class AdminSecondaryInformation extends Base {
    @Id
    @Column(name = "admin_id", columnDefinition = "VARCHAR(36)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long admin_id;

    @Size(max = 200)
    @Column(name = "department", length = 200)
    private String department;


    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;



}