package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;


    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;


    @Size(max = 255)
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<UserEntity> users = new LinkedHashSet<>();

}