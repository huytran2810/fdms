package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "request_application")
public class RequestApplication extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_application_id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "request_content")
    private String requestContent;

    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Size(max=255)
    @Column(name = "text_response")
    private String textResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "take_by_manager_id")
    private ManagerSecondaryInformation takeByManager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_application_type_id", insertable=false, updatable=false)
    private RequestApplicationType requestApplicationType;

    @Column(name = "request_application_type_id")
    private int requestApplicationTypeId;

    @Column(name = "bed_booked_id")
    private long bedBookedId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bed_booked_id", insertable=false, updatable=false)
    private Bed bed;

    @OneToMany(mappedBy = "requestApplication")
    private Set<Payment> payments = new LinkedHashSet<>();

}