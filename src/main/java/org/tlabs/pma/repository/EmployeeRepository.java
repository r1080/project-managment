package org.tlabs.pma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.tlabs.pma.dto.EmployeeProject;
import org.tlabs.pma.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Query(nativeQuery = true, value = "select e.first_name as firstName,e.last_name as lastName,"
			+ "count(pe.project_id) as projectCount "
			+ "from employee e "
			+ "left outer join project_employee pe on e.employee_id = pe.employee_id "
			+ "group by e.first_name,e.last_name order by 3 desc")
	public List<EmployeeProject> employeeProjects();

}
