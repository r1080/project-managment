package org.tlabs.pma.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tlabs.pma.logging.LogTime;
import org.tlabs.pma.model.Employee;
import org.tlabs.pma.model.Project;
import org.tlabs.pma.repository.EmployeeRepository;
import org.tlabs.pma.repository.ProjectRepository;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/new")
	@LogTime
	public String displayProjectPage(Model model) {

		List<Employee> employeeList = new ArrayList<>();
		employeeRepository.findAll().forEach(e -> employeeList.add(e));

		Project project = new Project();
		model.addAttribute("project", project);
		model.addAttribute("allEmployees", employeeList);

		return "new-project";
	}

	@PostMapping("/save")
	@LogTime
	public String saveProject(Model model, @Valid Project project, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "new-project";
		}

		projectRepository.save(project);

		return "redirect:/project/view";
	}
	
	@GetMapping("/view")
	@LogTime
	public String displayProjects(Model model){
		
		model.addAttribute("projects", projectRepository.findAll());
		
		return "list-projects";
	}
	
	@GetMapping("/update")
	@LogTime
	public String updateProject(Model model,@RequestParam("id") Long projectId){
		Optional<Project> optional = projectRepository.findById(projectId);
		if(optional.isPresent()){
			model.addAttribute("project", optional.get());
			
			List<Employee> employeeList = new ArrayList<>();
			employeeRepository.findAll().forEach(e -> employeeList.add(e));
			
			model.addAttribute("allEmployees", employeeList);
			return "new-project";
		}
		return "list-projects";		
	}
	
	@GetMapping("/delete")
	@LogTime
	public String deleteProject(Model model, @RequestParam("id") Long projectId){
		projectRepository.deleteById(projectId);
		return "redirect:/project/view";
	}
}
