package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.sqm.sql.internal.AnyDiscriminatorPathInterpretation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class EmployeeControllerTest
{
	@MockBean
	private EmployeeServiceImpl serviceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	private Employee emp1;
	private Employee emp2;
	
	@BeforeEach
	void init()
	{
		emp1 =new Employee(1,"pooja",84000.0,101);
		emp2 =new Employee(2,"prisha",84000.0,102);
	}
	
	@Test
	public void saveEmployeeTest() throws JsonProcessingException, Exception
	{
		when(serviceImpl.saveEmployee(emp1)).thenReturn(emp1);
		this.mockMvc.perform(post("/employee/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(emp1)))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.name",is(emp1.getName())))
					.andExpect(jsonPath("$.deptId",is(emp1.getDeptId())))
					.andExpect(jsonPath("$.salary",is(emp1.getSalary())));
	}
	
	@Test
	public void getAllEmployeeTest() throws Exception
	{
		List<Employee> list=new ArrayList<>();
		list.add(emp1);
		list.add(emp2);
		
		when(serviceImpl.getAllEmployee()).thenReturn(list);
		this.mockMvc.perform(get("/employee/all"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.size()",is(list.size())));
	}
	
	@Test
	public void getEmployeeTest() throws Exception
	{
		when(serviceImpl.getEmployee(1)).thenReturn(emp1);
		this.mockMvc.perform(get("/employee/{id}",1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name",is(emp1.getName())));
	}
	
	@Test
	public void deleteEmployeeTest() throws Exception
	{
		doNothing().when(serviceImpl).deleteEmployee(1);
		this.mockMvc.perform(delete("/employee/delete/{id}",1))
				.andExpect(status().isOk());
	}
	
	@Test
	public void updateEmployeeTest() throws JsonProcessingException, Exception
	{
		emp1 =new Employee(1,"pooja tungatkar",84000.0,101);

		when(serviceImpl.updateEmployee(emp1,1)).thenReturn(emp1);
		this.mockMvc.perform(put("/employee/update/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(emp1)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name",is(emp1.getName())));
	}
	
}
