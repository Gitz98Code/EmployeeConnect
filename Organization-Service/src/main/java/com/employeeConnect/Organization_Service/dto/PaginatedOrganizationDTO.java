package com.employeeConnect.Organization_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedOrganizationDTO {
    List<OrganizationDTO> organizationList;
    private long dataCount;

}
