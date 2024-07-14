package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request_application_type")
public class RequestApplicationType extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_application_type_id", nullable = false)
    private Long id;

    @Size(max = 300)
    @Column(name = "request_application_type_name", length = 300)
    private String requestApplicationTypeName;

    @OneToMany(mappedBy = "requestApplicationType")
    private Set<RequestApplication> requestApplications = new LinkedHashSet<>();

    @Column(name = "amount")
    private Float amount;

    @Size(max = 20)
    @Column(name = "permistion", length = 20)
    private String permistion;

}