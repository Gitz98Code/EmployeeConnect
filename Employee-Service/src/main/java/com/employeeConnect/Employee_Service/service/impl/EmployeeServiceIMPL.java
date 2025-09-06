package com.employeeConnect.Employee_Service.service.impl;

import com.employeeConnect.Employee_Service.dto.*;
import com.employeeConnect.Employee_Service.entity.Employee;
import com.employeeConnect.Employee_Service.exception.GlobalException;
import com.employeeConnect.Employee_Service.exception.NotFoundException;
import com.employeeConnect.Employee_Service.repo.EmployeeRepo;
import com.employeeConnect.Employee_Service.service.APIClient;
import com.employeeConnect.Employee_Service.service.EmployeeService;
import com.employeeConnect.Employee_Service.service.OrganizationAPIClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeServiceIMPL.class);

@Autowired
   private EmployeeRepo employeeRepo;
@Autowired
    private ModelMapper modelMapper;
@Autowired
     private APIClient apiClient;
@Autowired
     private OrganizationAPIClient organizationAPIClient;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee=modelMapper.map(employeeDTO,Employee.class);
        Employee savedEmployee=employeeRepo.save(employee);
        EmployeeDTO savedEmployeeDTO=modelMapper.map(savedEmployee,EmployeeDTO.class);
        return savedEmployeeDTO;
    }

    @Override
    public EmployeeUpdateDTO updateEmployee(EmployeeUpdateDTO employeeUpdateDTO) {
        if(employeeRepo.existsById(employeeUpdateDTO.getId())){
            Employee employee= employeeRepo.getReferenceById(employeeUpdateDTO.getId());
            modelMapper.map(employeeUpdateDTO, employee);


            Employee updatedEmployee= employeeRepo.save(employee);

           EmployeeUpdateDTO updatedEmployeeDto=modelMapper.map(updatedEmployee, EmployeeUpdateDTO.class);
            return updatedEmployeeDto;
        } else {
            throw new NotFoundException("No Employee found by ID: " + employeeUpdateDTO.getId());
        }
    }
//     @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
     @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDTO getEmployeeById(Long id) {
         LOGGER.info("inside getEmployeeById method");
        Employee employee=employeeRepo.findByIdEquals(id);
        if (employee == null) {
            throw new NotFoundException("No Employee found with id: " + id);
        }

        DepartmentDTO departmentDTO= apiClient.getDepartment(employee.getDepartmentCode());
        OrganizationDTO organizationDTO= organizationAPIClient.getOrganization(employee.getOrganizationCode());

        EmployeeDTO employeeDTO=modelMapper.map(employee, EmployeeDTO.class);

        ApiResponseDTO apiResponseDTO=new ApiResponseDTO();

        apiResponseDTO.setEmployeeDTO(employeeDTO);
        apiResponseDTO.setDepartmentDTO(departmentDTO);
        apiResponseDTO.setOrganizationDTO(organizationDTO);

        return apiResponseDTO;
    }

    @Override
    public PaginatedEmployeeDTO getAllEmployees(int page,int size) {
        Page<Employee> employees = employeeRepo.findAll(PageRequest.of(page, size));
        if (employees.getContent().isEmpty()) {
            throw new GlobalException("No employee found in the database.");
        }

        List<EmployeeDTO> employeeDTOList = modelMapper.map(
                employees.getContent(),
                new TypeToken<List<EmployeeDTO>>() {}.getType()
        );

        return new PaginatedEmployeeDTO(employeeDTOList, employees.getTotalElements());
    }

    @Override
    public String deleteEmployee(Long id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return "Deleted Successfuly."+id;
        } else {
            throw new NotFoundException("No employee found to delete with ID: " + id);
        }
    }


    public ApiResponseDTO getDefaultDepartment(Long id,Exception exception) {
        LOGGER.info("inside getDefaultDepartment method");
        Employee employee=employeeRepo.findByIdEquals(id);
        if (employee == null) {
            throw new NotFoundException("No Employee found with id: " + id);
        }

        DepartmentDTO departmentDTO=new DepartmentDTO();
                 departmentDTO.setDepartmentName("Default Department");
                 departmentDTO.setDepartmentCode("D-001");
                 departmentDTO.setDepartmentDescription("Development Department");

        EmployeeDTO employeeDTO=modelMapper.map(employee, EmployeeDTO.class);

        ApiResponseDTO apiResponseDTO=new ApiResponseDTO();

        apiResponseDTO.setEmployeeDTO(employeeDTO);
        apiResponseDTO.setDepartmentDTO(departmentDTO);

        return apiResponseDTO;
    }
}
