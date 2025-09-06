package com.employeeConnect.Employee_Service.service;

import com.employeeConnect.Employee_Service.dto.ApiResponseDTO;
import com.employeeConnect.Employee_Service.dto.EmployeeDTO;
import com.employeeConnect.Employee_Service.dto.EmployeeUpdateDTO;
import com.employeeConnect.Employee_Service.dto.PaginatedEmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeUpdateDTO updateEmployee(EmployeeUpdateDTO employeeUpdateDTO);

    ApiResponseDTO getEmployeeById(Long id);

    PaginatedEmployeeDTO getAllEmployees(int page,int size);

    String deleteEmployee(Long id);
}
