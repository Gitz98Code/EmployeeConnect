package com.employeeConnect.Organization_Service.service;


import com.employeeConnect.Organization_Service.dto.OrganizationDTO;
import com.employeeConnect.Organization_Service.dto.OrganizationUpdateDTO;
import com.employeeConnect.Organization_Service.dto.PaginatedOrganizationDTO;

public interface OrganizationService {


    OrganizationDTO saveOrganization(OrganizationDTO organizationDTO);

    OrganizationUpdateDTO updateOrganization(OrganizationUpdateDTO organizationUpdateDTO);

    OrganizationDTO getOrganizationByCode(String organizationCode);

    PaginatedOrganizationDTO getAllOrganizations(int page, int size);

    String deleteOrganization(Long id);
}
