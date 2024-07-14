package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_secondary_information")
public class StudentSecondaryInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;


    @Column(name = "created_at")
    private Instant createdAt;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @Size(max = 100)
    @Column(name = "parent_name", length = 100)
    private String parentName;

    @Size(max = 10)
    @Column(name = "roll_number", length = 10)
    private String rollNumber;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "is_in_dorm", length = 50)
    private String isInDorm;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userInfor;

    @OneToMany(mappedBy = "student")
    private Set<Payment> payments = new LinkedHashSet<>();


}