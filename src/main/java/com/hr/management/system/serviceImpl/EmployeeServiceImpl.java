package com.hr.management.system.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.management.system.model.Employee;
import com.hr.management.system.service.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService {

//	@Autowired
//	private EmployeeRepo employeeRepository;

	@Autowired
    private RestTemplate restTemplate;
	 @Autowired
	  private ObjectMapper mapper;
	@Override
	public List<Employee> getAllEmployees() {
		
		System.out.println("in employee");
		   ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://localhost:8083/employee", Object[].class);
           Object[] objects = responseEntity.getBody();
           List<Employee> employees = Arrays.stream(objects).map(object -> mapper.convertValue(object, Employee.class))
                   .collect(Collectors.toList());
           System.out.println(employees.size());
           return employees;
	}
	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		System.out.println("Save emplyee");
		 restTemplate.postForEntity("http://localhost:8083/employee", employee, String.class);
	}


}
