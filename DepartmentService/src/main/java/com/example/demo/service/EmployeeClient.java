package com.example.demo.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Employee;

//@FeignClient(url="http://localhost:8082", value="Employee-Client")


@FeignClient(name="EMPLOYEESERVICE")
public interface EmployeeClient 
{
	@GetMapping("/employee/dept/{deptId}")
	public List<Employee> getEmployeeByDepartment(@PathVariable Integer deptId);
}
