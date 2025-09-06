package com.employeeConnect.Employee_Service.service;


import com.employeeConnect.Employee_Service.dto.OrganizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url ="http://localhost:8085",value = "Organization-Service")
public interface OrganizationAPIClient {
   @GetMapping("api/organizations/{code}")
   OrganizationDTO getOrganization(@PathVariable("code") String organizationCode);
}
