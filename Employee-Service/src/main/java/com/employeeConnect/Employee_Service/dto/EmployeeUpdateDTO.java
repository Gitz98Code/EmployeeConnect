package com.employeeConnect.Employee_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeUpdateDTO {
    private Long id;
    private String email;
    private String contactNumber;
}
