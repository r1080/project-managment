package org.tlabs.pma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlabs.pma.dto.EmployeeProject;
import org.tlabs.pma.model.Employee;
import org.tlabs.pma.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeProject> getEmployeeProjectCount() {
		return employeeRepository.employeeProjects();
	}

	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public Iterable<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> findById(Long id){
		return employeeRepository.findById(id);
	}
	
	public void deleteById(Long id){
		employeeRepository.deleteById(id);
	}
	
	
}


