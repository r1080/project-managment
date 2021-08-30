package org.tlabs.pma.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.tlabs.pma.validator.ValidDate;

@Entity
@Table(name = "project")
@ValidDate
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	@Column
	@NotBlank(message="Project Name Must Not Be Null")
	private String name;
	@Column
	private String stage; // - NOTSTARTED, COMPLETED, INPROGRESS
	@Column
	@Size(max = 70)
	private String description;
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date targetDate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinTable(name = "project_employee", joinColumns = { @JoinColumn(name = "project_id") }, inverseJoinColumns = {
			@JoinColumn(name = "employee_id") })
	private List<Employee> employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Project() {

	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", stage=" + stage + ", description="
				+ description + ", startDate=" + startDate + ", endDate=" + endDate + ", targetDate=" + targetDate
				+ ", employeeList=" + employeeList + "]";
	}

}
