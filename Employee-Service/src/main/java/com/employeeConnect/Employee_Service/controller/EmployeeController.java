package com.employeeConnect.Employee_Service.controller;

import com.employeeConnect.Employee_Service.dto.ApiResponseDTO;
import com.employeeConnect.Employee_Service.dto.EmployeeDTO;
import com.employeeConnect.Employee_Service.dto.EmployeeUpdateDTO;
import com.employeeConnect.Employee_Service.dto.PaginatedEmployeeDTO;
import com.employeeConnect.Employee_Service.service.EmployeeService;
import com.employeeConnect.Employee_Service.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;


@Valid
@RestController
@RequestMapping("api/employees")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    ResponseEntity<StandardResponse>saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee=employeeService.saveEmployee(employeeDTO);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(201,"Success",savedEmployee), HttpStatus.CREATED);
        return response;
    }
    @PutMapping("/update")
    public ResponseEntity<StandardResponse>updateEmployee(@RequestBody EmployeeUpdateDTO employeeUpdateDTO){
        EmployeeUpdateDTO updateEmployee=employeeService.updateEmployee(employeeUpdateDTO);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",updateEmployee), HttpStatus.OK);
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDTO>getEmployeeById(@PathVariable(value = "id") Long id){
        ApiResponseDTO apiResponseDTO= employeeService.getEmployeeById(id);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-employees", params = {"page","size"})
    public ResponseEntity<StandardResponse>getAllEmployees(@RequestParam(value = "page") int page,
                                                           @RequestParam(value = "size") @Max(50) int size){
        PaginatedEmployeeDTO employeeList = employeeService.getAllEmployees(page,size);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",employeeList),HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete-employee-by-id/{id}")
    public ResponseEntity<StandardResponse>deleteEmoloyee(@PathVariable(value = "id") Long id ){
        String message= employeeService.deleteEmployee(id);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",message),HttpStatus.OK);
        return response;
    }
}
