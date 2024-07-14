package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @Column(name = "amount")
    private Float amount;

    @Size(max = 255)
    @Column(name = "status")
    private String status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_application_id")
    private RequestApplication requestApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentSecondaryInformation student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @Column(name = "expiration_date")
    private Instant expirationDate;

}