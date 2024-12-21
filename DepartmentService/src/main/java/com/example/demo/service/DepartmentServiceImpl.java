package com.example.demo.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repo.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService
{

	@Autowired
	private DepartmentRepo repo;
	
	@Autowired
	private EmployeeClient empClient;
	
	@Override
	public Department saveDept(Department dept)
	{
		return repo.save(dept);
	}

	@Override
	public List<Department> getAllDepartment()
	{
		List<Department> deptList=repo.findAll();
		List<Department> newList =deptList.stream().map(dept ->{ 
										dept.setEmpList(empClient.getEmployeeByDepartment(dept.getId()));
										return dept;			
			
									}).collect(Collectors.toList());
		return newList;
	}

	@Override
	public Department getDepartment(Integer id) 
	{
		Department dept= repo.findById(id).orElseThrow(()-> new RuntimeException("dept not found"));
		dept.setEmpList(empClient.getEmployeeByDepartment(dept.getId()));
		System.out.println(" dept is "+dept);
		return dept;
	}

}
