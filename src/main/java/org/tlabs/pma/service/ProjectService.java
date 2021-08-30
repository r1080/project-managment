package org.tlabs.pma.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlabs.pma.dto.ProjectStage;
import org.tlabs.pma.model.Project;
import org.tlabs.pma.repository.ProjectRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	
	public String getTimelineMetrics() {

		List<Project> projectList = projectRepository.findAll();

		List<List<String>> timeLineData = new ArrayList<>();

		//TODO: Add a check against null empty date and start date
		int counter = 0;
		for (Project project : projectList) {

			List<String> rowData = new ArrayList<>();

			rowData.add(0, project.getName());
			rowData.add(1, formatDate(project.getStartDate()));
			rowData.add(2, formatDate(project.getEndDate()));

			timeLineData.add(counter++, rowData);
		}
		
		String timeLineJsonString = convertToJsonString(timeLineData);
		
		return timeLineJsonString;
	}
	
	
	public List<Project> findAllProjects(){
		return projectRepository.findAll();
	}

	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String output = sdf.format(date);
		return output;
	}
	
	
	public List<ProjectStage> getProjectStages(){
		return projectRepository.projectStage();
	}
	
	private String convertToJsonString(List<List<String>> timeLineData) {

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(timeLineData);
		} catch (JsonProcessingException e) {
			
		}
		return jsonString;
	}
	
	
	public Page<Project> findPaginatedProjects(int pageNo, int size){
		Sort sort = Sort.by("targetDate").ascending();
		Pageable page = PageRequest.of(pageNo-1, size,sort);
		Page<Project> pageProjects = projectRepository.findAll(page);
		return pageProjects;
	}
	
	
}
