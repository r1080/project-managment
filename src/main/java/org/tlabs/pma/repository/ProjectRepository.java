package org.tlabs.pma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tlabs.pma.dto.ProjectStage;
import org.tlabs.pma.model.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true,value="select stage as projectStage, count(stage) as stageCount "
			+ "from project group by stage order by 2 desc")
	public List<ProjectStage> projectStage();

}
