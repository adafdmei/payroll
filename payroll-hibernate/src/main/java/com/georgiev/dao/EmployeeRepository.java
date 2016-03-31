package com.georgiev.dao;

import com.georgiev.payroll.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
