package com.employeeConnect.Organization_Service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String organizationName;
    private String organizationDescription;
    @Column(nullable = false,unique = true)
    private String organizationCode;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(nullable = false)
    private String email;
    private String phoneNumber;
    private String address;
}
