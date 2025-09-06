package com.employeeConnect.Organization_Service.service.impl;


import com.employeeConnect.Organization_Service.dto.OrganizationDTO;
import com.employeeConnect.Organization_Service.dto.OrganizationUpdateDTO;
import com.employeeConnect.Organization_Service.dto.PaginatedOrganizationDTO;
import com.employeeConnect.Organization_Service.entity.Organization;
import com.employeeConnect.Organization_Service.exception.DuplicateEntryException;
import com.employeeConnect.Organization_Service.exception.GlobalException;
import com.employeeConnect.Organization_Service.exception.NotFoundException;
import com.employeeConnect.Organization_Service.repo.OrganizationRepo;
import com.employeeConnect.Organization_Service.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceIMPL implements OrganizationService {
    @Autowired
    private OrganizationRepo organizationRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {
        if (organizationRepo.findByOrganizationCodeEquals(organizationDTO.getOrganizationCode()) != null) {
            throw new DuplicateEntryException("Organization already exists with code: "
                    + organizationDTO.getOrganizationCode());
        }

        Organization organization = modelMapper.map(organizationDTO, Organization.class);
        Organization savedOrganization = organizationRepo.save(organization);

        OrganizationDTO dto= modelMapper.map(savedOrganization, OrganizationDTO.class);
         return dto;

    }

    @Override
    public OrganizationUpdateDTO updateOrganization(OrganizationUpdateDTO organizationUpdateDTO) {
        if(organizationRepo.existsById(organizationUpdateDTO.getId())){
            Organization organization= organizationRepo.getReferenceById(organizationUpdateDTO.getId());
            modelMapper.map(organizationUpdateDTO,organization);


            Organization updatedOrganization = organizationRepo.save(organization);

            OrganizationUpdateDTO updatedOrganizationDTO=modelMapper.map(updatedOrganization, OrganizationUpdateDTO.class);
            return updatedOrganizationDTO;
        } else {
            throw new NotFoundException("No Organization found by ID: " + organizationUpdateDTO.getId());
        }



    }

    @Override
    public OrganizationDTO getOrganizationByCode(String organizationCode) {
        Organization organization=organizationRepo.findByOrganizationCodeEquals(organizationCode);
        if (organization == null) {
            throw new NotFoundException("No Organization found with code: " + organizationCode);
        }
       OrganizationDTO organizationDTO=modelMapper.map(organization, OrganizationDTO.class);
        return organizationDTO;

    }

    @Override
    public PaginatedOrganizationDTO getAllOrganizations(int page, int size) {
        Page<Organization> organizations = organizationRepo.findAll(PageRequest.of(page, size));
        if (organizations.getContent().isEmpty()) {
            throw new GlobalException("No organization found in the database.");
        }

        List<OrganizationDTO> organizationDTOList = modelMapper.map(
                organizations.getContent(),
                new TypeToken<List<OrganizationDTO>>() {}.getType()
        );

        return new PaginatedOrganizationDTO(organizationDTOList, organizations.getTotalElements());

    }

    @Override
    public String deleteOrganization(Long id) {

        if (organizationRepo.existsById(id)) {
            organizationRepo.deleteById(id);
            return "Deleted Successfuly."+id;
        } else {
            throw new NotFoundException("No organization found to delete with ID: " + id);
        }
    }
}
