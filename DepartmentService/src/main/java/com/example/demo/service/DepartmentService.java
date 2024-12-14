package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Department;

public interface DepartmentService 
{
	public Department saveDept(Department dept);
	public List<Department> getAllDepartment();
}
