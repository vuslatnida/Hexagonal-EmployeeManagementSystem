package com.tr.employee.repository;

import com.tr.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);

}