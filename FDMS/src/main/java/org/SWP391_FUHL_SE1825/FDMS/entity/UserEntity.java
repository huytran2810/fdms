package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 300)
    @Column(name = "avartar_image", length = 300)
    private String avatarImage;

    @Size(max = 100)
    @Column(name = "address", length = 100)
    private String address;


    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 100)
    @Column(name = "full_name", length = 100)
    private String fullName;

    @Size(max = 20)
    @Column(name = "gender", length = 20)
    private String gender;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Size(max = 100)
    @Column(name = "user_name", length = 20)
    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "userEntity")
    private Set<AdminSecondaryInformation> adminSecondaryInformations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userEntity")
    private Set<ManagerSecondaryInformation> managerSecondaryInformations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userInfor")
    private Set<StudentSecondaryInformation> studentSecondaryInformations = new LinkedHashSet<>();
}