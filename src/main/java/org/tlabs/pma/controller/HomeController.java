package org.tlabs.pma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tlabs.pma.dto.ProjectStage;
import org.tlabs.pma.logging.LogTime;
import org.tlabs.pma.model.Project;
import org.tlabs.pma.model.User;
import org.tlabs.pma.security.PMASecurityService;
import org.tlabs.pma.service.EmployeeService;
import org.tlabs.pma.service.ProjectService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/home")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Value("${version}")
	private String version;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PMASecurityService pMASecurityService;

	@GetMapping("/page/{pageNo}")
	@LogTime
	public String displayMainDashboard(@PathVariable int pageNo, Model model) {

		displayPaginatedProjects(pageNo, model);

		model.addAttribute("employeesListProjectsCnt", employeeService.getEmployeeProjectCount());
		model.addAttribute("versionNumber", version);

		List<ProjectStage> projectStage = projectService.getProjectStages();

		String projectStageJson = projectService.convertToJsonString(projectStage);

		model.addAttribute("projectStatusCnt", projectStageJson);

		return "home";
	}

	@GetMapping("/metrics")
	public String displayMetricsPage(Model model) {

		logger.info("Metrics");
		
		List<Project> projectList = projectService.findAllProjects();

		model.addAttribute("timelineData", projectService.getTimelineMetrics(projectList));
		
		String projectCompletionStatus = projectService.projectCompletionStatus(projectList);
		
		logger.info("Project Completion Status {}" , projectCompletionStatus);
		
		model.addAttribute("projectCompletionStatus", projectCompletionStatus);

		return "metrics";
	}

	private void displayPaginatedProjects(int pageNo, Model model) {

		Page<Project> paginatedProjects = projectService.findPaginatedProjects(pageNo, 5);

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("noOfPages", paginatedProjects.getTotalPages());
		model.addAttribute("totalItems", paginatedProjects.getTotalElements());
		model.addAttribute("projectsList", paginatedProjects.getContent());

	}

	@GetMapping
	public String loginPage(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String loginHome(@RequestParam("email") String username, String password) {

		logger.info("UserName, Password {},{} ", username, password);

		boolean result = pMASecurityService.authenticateLogin(username, password);

		if (result) {
			return "redirect:/home/page/1";
		} else {
			throw new BadCredentialsException("Invalid username or password");
		}
	}

	@GetMapping("/register")
	public String registerPage(Model model) {

		logger.info("Navigate to register");

		model.addAttribute("user", new User());

		return "register";
	}

	@PostMapping("/register")
	@LogTime
	public String registerUser(User user) {

		logger.info("Registering User {} ", user);
		User savedUser = pMASecurityService.registerUser(user);

		boolean result = pMASecurityService.authenticateLogin(savedUser.getEmail(), user.getPassword());

		if (result) {
			return "redirect:/home/page/1";
		} else {
			throw new BadCredentialsException("Invalid username or password");
		}
	}

	@GetMapping("/personal")
	public String displayPersonalPage() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		if (name.equals("raghavtenneti11@gmail.com")) {
			return "personal";
		}
		return "redirect:/home/page/1";
	}
	
	/*private String convertToJsonString(List<ProjectStage> projectStage) {

	ObjectMapper mapper = new ObjectMapper();
	String jsonString = "";
	try {
		jsonString = mapper.writeValueAsString(projectStage);
	} catch (JsonProcessingException e) {
		logger.error("Error converting to json {}", e);
	}
	return jsonString;
}*/
}
