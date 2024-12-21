package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;

public class EmployeeServiceTest 
{
	@Mock
	private EmployeeRepo repo;
	
	@InjectMocks
	private EmployeeServiceImpl serviceImpl;
	
	private Employee emp1;
	private Employee emp2;
	
	@BeforeEach
	void init()
	{
		MockitoAnnotations.openMocks(this);
		emp1 =new Employee(1,"pooja",84000.0,101);
		emp2 =new Employee(2,"prisha",84000.0,102);
	}
	
	@Test
	public void saveEmployeeTest()
	{
		when(repo.save(emp1)).thenReturn(emp1);
		Employee e=serviceImpl.saveEmployee(emp1);
		assertNotNull(e);
		assertEquals("pooja",e.getName());
	}
	
	@Test
	public void getAllEmployeeTest()
	{
		List<Employee>list=new ArrayList<>();
		list.add(emp1);
		list.add(emp2);
		when(repo.findAll()).thenReturn(list);
		List<Employee> list2=serviceImpl.getAllEmployee();
		assertNotNull(list2);
		assertEquals(2,list2.size());;
		
	}
	
	@Test
	public void getEmployeeTest()
	{
		when(repo.findById(1)).thenReturn(Optional.of(emp1));
		Employee e1=serviceImpl.getEmployee(1);
		assertNotNull(e1);
		assertThat("pooja").isEqualTo(e1.getName());
	}
	
	@Test
	public void deleteEmployeeTest()
	{
		doNothing().when(repo).deleteById(1);
		serviceImpl.deleteEmployee(1);
		verify(repo,times(1)).deleteById(1);	
	}
	
	@Test
	public void updateEmployeeTest()
	{
		when(repo.findById(1)).thenReturn(Optional.of(emp1));
		when(repo.save(emp1)).thenReturn(emp1);
		emp1.setName("yogesh");
		Employee update =serviceImpl.updateEmployee(emp1, 1);
		assertEquals("yogesh", update.getName());
	}
	
	
}
