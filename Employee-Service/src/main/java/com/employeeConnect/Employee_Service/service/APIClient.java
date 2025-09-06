package com.employeeConnect.Employee_Service.service;

import com.employeeConnect.Employee_Service.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url ="http://localhost:8080",value = "Department-Service")

public interface APIClient {
    @GetMapping("api/departments/{departmentCode}")
    DepartmentDTO getDepartment(@PathVariable("departmentCode") String departmentCode);
}
