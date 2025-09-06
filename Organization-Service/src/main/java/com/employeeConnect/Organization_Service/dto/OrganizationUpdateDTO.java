package com.employeeConnect.Organization_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrganizationUpdateDTO {
    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String address;
}
