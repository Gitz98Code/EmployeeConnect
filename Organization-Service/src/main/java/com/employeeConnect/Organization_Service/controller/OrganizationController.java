package com.employeeConnect.Organization_Service.controller;

import com.employeeConnect.Organization_Service.dto.OrganizationDTO;
import com.employeeConnect.Organization_Service.dto.OrganizationUpdateDTO;
import com.employeeConnect.Organization_Service.dto.PaginatedOrganizationDTO;
import com.employeeConnect.Organization_Service.service.OrganizationService;
import com.employeeConnect.Organization_Service.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;


@RestController
@RequestMapping("api/organizations")
@CrossOrigin
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;


    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveOrganization(@RequestBody OrganizationDTO organizationDTO){
        OrganizationDTO savedOrganizationDTO=organizationService.saveOrganization(organizationDTO);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(201,"Success",savedOrganizationDTO), HttpStatus.CREATED);

        return response;
    }


    @PutMapping("/update")
    public ResponseEntity<StandardResponse>updateOrganization(@RequestBody OrganizationUpdateDTO organizationUpdateDTO){
        OrganizationUpdateDTO updateOrganization=organizationService.updateOrganization(organizationUpdateDTO);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",updateOrganization), HttpStatus.OK);
        return response;
    }


    @GetMapping("{code}")
    public ResponseEntity<OrganizationDTO>getOrganizationByCode(@PathVariable(value = "code")String organizationCode){
        OrganizationDTO getSavedOrganization=organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(getSavedOrganization,HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-organizations", params = {"page","size"})
    public ResponseEntity<StandardResponse>getAllOrganizations(@RequestParam(value = "page") int page,
                                                               @RequestParam(value = "size") @Max(50) int size){
        PaginatedOrganizationDTO organizationList  = organizationService.getAllOrganizations(page,size);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",organizationList),HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete-organization-by-id/{id}")
    public ResponseEntity<StandardResponse>deleteOrganization(@PathVariable(value = "id") Long id ){
        String message= organizationService.deleteOrganization(id);
        ResponseEntity<StandardResponse>response=new ResponseEntity<StandardResponse>
                (new StandardResponse(200,"Success",message),HttpStatus.OK);
        return response;
    }


}
