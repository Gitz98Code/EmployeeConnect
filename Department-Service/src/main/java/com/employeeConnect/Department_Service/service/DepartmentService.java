package com.employeeConnect.Department_Service.service;

import com.employeeConnect.Department_Service.dto.DepartmentDTO;
import com.employeeConnect.Department_Service.dto.DepartmentUpdateDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    DepartmentUpdateDTO updateDepartment(DepartmentUpdateDTO departmentUpdateDTO);

    DepartmentDTO getDepartmentByCode(String departmentCode);

    List<DepartmentDTO> getAllDepartments();

    String deleteDepartment(Long departmentId);
}
