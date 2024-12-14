package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repo.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService
{

	@Autowired
	private DepartmentRepo repo;
	
	@Override
	public Department saveDept(Department dept)
	{
		return repo.save(dept);
	}

	@Override
	public List<Department> getAllDepartment()
	{
		return repo.findAll();
	}

}
