package com.employeeConnect.Department_Service.repo;

import com.employeeConnect.Department_Service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface DepartmentRepo extends JpaRepository<Department,Long> {
    Department findByDepartmentCodeEquals(String departmentCode);
}
