package com.employeeConnect.Department_Service.service.impl;

import com.employeeConnect.Department_Service.dto.DepartmentDTO;
import com.employeeConnect.Department_Service.dto.DepartmentUpdateDTO;
import com.employeeConnect.Department_Service.entity.Department;
import com.employeeConnect.Department_Service.exception.DuplicateEntryException;
import com.employeeConnect.Department_Service.exception.GlobalException;
import com.employeeConnect.Department_Service.exception.NotFoundException;
import com.employeeConnect.Department_Service.repo.DepartmentRepo;
import com.employeeConnect.Department_Service.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceIMPL implements DepartmentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        if (departmentRepo.findByDepartmentCodeEquals(departmentDTO.getDepartmentCode()) != null) {
            throw new DuplicateEntryException("Department already exists with code: " + departmentDTO.getDepartmentCode());
        }
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department savedDepartment = departmentRepo.save(department);
        DepartmentDTO departmentDto=modelMapper.map(savedDepartment, DepartmentDTO.class);
          return departmentDto;
    }

    @Override
    public DepartmentUpdateDTO updateDepartment(DepartmentUpdateDTO departmentUpdateDTO) {
        if(departmentRepo.existsById(departmentUpdateDTO.getDepartmentId())){
            Department department= departmentRepo.getReferenceById(departmentUpdateDTO.getDepartmentId());
            modelMapper.map(departmentUpdateDTO, department);


            Department updatedDepartment = departmentRepo.save(department);

           DepartmentUpdateDTO updatedDepartmentDTO=modelMapper.map(updatedDepartment, DepartmentUpdateDTO.class);
            return updatedDepartmentDTO;
        } else {
            throw new NotFoundException("No Department found by ID: " + departmentUpdateDTO.getDepartmentId());
        }
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {

        Department department=departmentRepo.findByDepartmentCodeEquals(departmentCode);
        if (department == null) {
            throw new NotFoundException("No Department found with code: " + departmentCode);
        }
        DepartmentDTO departmentDTO=modelMapper.map(department, DepartmentDTO.class);
        return departmentDTO;
    }





    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepo.findAll();

        if (departments.isEmpty()) {
            throw new GlobalException("No departments found in the database.");
        }


        List<DepartmentDTO> departmentDtoList= modelMapper.map(departments, new TypeToken<List<DepartmentDTO>>() {}.getType());
        return departmentDtoList;
    }

    @Override
    public String deleteDepartment(Long departmentId) {
        if (departmentRepo.existsById(departmentId)) {
            departmentRepo.deleteById(departmentId);
            return "Deleted Successfuly."+departmentId;
        } else {
            throw new NotFoundException("No department found to delete with ID: " + departmentId);
        }
    }
}
