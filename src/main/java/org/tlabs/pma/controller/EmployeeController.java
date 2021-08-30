package org.tlabs.pma.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tlabs.pma.logging.LogTime;
import org.tlabs.pma.model.Employee;
import org.tlabs.pma.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
		
	@GetMapping
	public String displayAddEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new-employee";
	}

	@PostMapping("/save")
	public String addEmployee(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employee";
	}

	@GetMapping("/view")
	@LogTime
	public String getEmployeeList(Model model) {
		List<Employee> empList = new ArrayList<>();
		employeeService.findAllEmployees().forEach(e -> empList.add(e));
		model.addAttribute("employees", empList);
		return "list-employees";
	}

	@PutMapping("/update")
	@LogTime
	public String updateEmployee(Model model, @RequestParam("id") Long id) {
		Optional<Employee> optional = employeeService.findById(id);
		if (optional.isPresent()) {
			model.addAttribute(model.addAttribute("employee", optional.get()));
			return "new-employee";
		}
		return "list-employees";
	}
	
	@DeleteMapping("/delete")
	@LogTime
	public String deleteEmployee(Model model, @RequestParam("id") Long id) {
		employeeService.deleteById(id);
		return "redirect:/employee/view";
	}

}
