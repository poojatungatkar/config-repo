package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepo repo;
	
	@Override
	public Employee saveEmployee(Employee employee)
	{
		return repo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() 
	{
		return repo.findAll();
	}

	@Override
	public Employee getEmployee(Integer id)
	{
		return repo.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee not found with id: "+id));
	}

	@Override
	public void deleteEmployee(Integer id)
	{
		repo.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee not found with id: "+id));
		
	}

	@Override
	public Employee updateEmployee(Employee employee, Integer id) 
	{
		Employee emp=repo.findById(id).get(); 
		emp.setName(employee.getName());
		emp.setDeptId(employee.getDeptId());
		emp.setSalary(employee.getSalary());
		
		return repo.save(emp);
	}

	@Override
	public List<Employee> getEmployeesByDeptId(int deptId) 
	{
		return repo.findByDeptId(deptId);
	}
	
}
