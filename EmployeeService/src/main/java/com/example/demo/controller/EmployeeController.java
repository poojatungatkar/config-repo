package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController 
{
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/save")
	public ResponseEntity<Employee> saveemployee(@RequestBody Employee employee)
	{
		Employee employee2=service.saveEmployee(employee);
		return new ResponseEntity<Employee>(employee2,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee()
	{
		return new ResponseEntity<List<Employee>>(service.getAllEmployee(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployEntity(@PathVariable Integer id)
	{
		return new ResponseEntity<Employee>(service.getEmployee(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id)
	{
		service.deleteEmployee(id);
		return ResponseEntity.ok("employee deleted");
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Integer id)
	{
		return new ResponseEntity<Employee>(service.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	@GetMapping("/dept/{deptId}")
	public ResponseEntity<List<Employee>> getEmployeesByDeptId(@PathVariable Integer deptId)
	{
		return new ResponseEntity<List<Employee>>(service.getEmployeesByDeptId(deptId),HttpStatus.OK);
		
	}
	
}
