package com.employeeConnect.Employee_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ApiResponseDTO {
    private DepartmentDTO departmentDTO;
    private EmployeeDTO employeeDTO;
    private OrganizationDTO organizationDTO;


}
