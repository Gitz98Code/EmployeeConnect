package com.employeeConnect.Organization_Service.repo;

import com.employeeConnect.Organization_Service.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface OrganizationRepo extends JpaRepository<Organization,Long> {
    Organization findByOrganizationCodeEquals(String organizationCode);
}