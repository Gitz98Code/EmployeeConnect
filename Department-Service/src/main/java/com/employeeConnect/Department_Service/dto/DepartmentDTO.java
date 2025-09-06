package com.employeeConnect.Department_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

}
