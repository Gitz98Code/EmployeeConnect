package com.employeeConnect.Department_Service.controller;

import com.employeeConnect.Department_Service.dto.DepartmentDTO;
import com.employeeConnect.Department_Service.dto.DepartmentUpdateDTO;
import com.employeeConnect.Department_Service.service.DepartmentService;
import com.employeeConnect.Department_Service.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO saveDepartment=departmentService.saveDepartment(departmentDTO) ;
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(201,"Success",saveDepartment), HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse>updateDepartment(@RequestBody DepartmentUpdateDTO departmentUpdateDTO){
          DepartmentUpdateDTO updateDepartment=departmentService.updateDepartment(departmentUpdateDTO);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",updateDepartment), HttpStatus.OK);
        return response;
    }

    @GetMapping("{departmentCode}")
    public ResponseEntity<DepartmentDTO>getDepartmentByCode(@PathVariable(value = "departmentCode")String departmentCode){
        DepartmentDTO departmentDTO= departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-departments")
    public ResponseEntity<StandardResponse>getAllDepartments(){
        List<DepartmentDTO> departmentList = departmentService.getAllDepartments();
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",departmentList),HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete-department-by-id/{id}")
    public ResponseEntity<StandardResponse>deleteDepartment(@PathVariable(value = "id") Long departmentId ){
        String message= departmentService.deleteDepartment(departmentId);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",message),HttpStatus.OK);
        return response;
    }
}
