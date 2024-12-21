package com.example.demo.repo;

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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Employee;

public class EmployeeRepositoryTest 
{
	@Mock
	private EmployeeRepo repo;
	
	private Employee emp1;
	private Employee emp2;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		
		emp1 =new Employee(1,"pooja",84000.0,101);
		emp2 =new Employee(2,"prisha",84000.0,102);
	}
	
	@Test
	public void saveEmployeeTest()
	{
		when(repo.save(emp1)).thenReturn(emp1);
		Employee e=repo.save(emp1);
		assertNotNull(e);
		assertEquals("pooja",e.getName());
		verify(repo,times(1)).save(emp1);
		
	}
	
	@Test
	public void getAllEmployeeTest()
	{
		List<Employee> list= new ArrayList<>();
		list.add(emp1);
		list.add(emp2);
		
		when(repo.findAll()).thenReturn(list);
		List<Employee> list1=repo.findAll();
		assertNotNull(list1);
		assertEquals(2, list1.size());
		verify(repo,times(1)).findAll();
	}
	
	@Test
	public void getEmployeeTest()
	{
		when(repo.findById(1)).thenReturn(Optional.of(emp1));
		Employee e= repo.findById(1).get();
		assertNotNull(e);
		assertEquals("pooja", e.getName());
		verify(repo,times(1)).findById(1);
	}
	
	@Test
	public void deleteEmployeeTest()
	{
		doNothing().when(repo).deleteById(1);
		repo.deleteById(1);
		verify(repo,times(1)).deleteById(1);
		
	}
	
	@Test
	public void updateEmployeeTest()
	{
		when(repo.save(emp1)).thenReturn(emp1);
		emp1.setName("pooja tungatkar");
		Employee e=repo.save(emp1);
		assertNotNull(e);
		assertEquals("pooja tungatkar",e.getName());
		verify(repo,times(1)).save(emp1);
	}
}
