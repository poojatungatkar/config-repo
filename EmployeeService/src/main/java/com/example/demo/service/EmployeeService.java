package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmployeeService
{
	public Employee saveEmployee(Employee employee);
	public List<Employee> getAllEmployee();
	public Employee getEmployee(Integer id);
	public void deleteEmployee (Integer id);
	public Employee updateEmployee(Employee employee, Integer id);
	public List<Employee> getEmployeesByDeptId(int deptId);
}
