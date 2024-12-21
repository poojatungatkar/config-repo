package com.example.demo.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
@RestController
@RequestMapping("/dept")
public class DepartmentController 
{
	Logger log=LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService service;
	
	@PostMapping("/add")
	public Department saveDept(@RequestBody Department department)
	{
		return service.saveDept(department);
	}
	
	@GetMapping("/all")
	public List<Department> getAlldepartDepartments()
	{
		return service.getAllDepartment();
	}
	
	@GetMapping("/{id}")
	public Department getDepartment(@PathVariable Integer id)
	{
		Department d=service.getDepartment(id);
		log.info("dept info : ",d);
		return d;
	}
}
