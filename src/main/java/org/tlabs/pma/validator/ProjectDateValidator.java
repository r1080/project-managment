package org.tlabs.pma.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.tlabs.pma.model.Project;

public class ProjectDateValidator implements ConstraintValidator<ValidDate, Project>{
	
	@Override
	public boolean isValid(Project project, ConstraintValidatorContext context) {
		
		if(project.getStartDate() == null){
			context.buildConstraintViolationWithTemplate("Start Date should not be empty").addPropertyNode("startDate").addConstraintViolation();
			return false;
		}
		
		if(project.getTargetDate() == null){
			context.buildConstraintViolationWithTemplate("Target Date should not be empty").addPropertyNode("startDate").addConstraintViolation();
			return false;
		}
			
		LocalDate startDate = getLocalDate(project.getStartDate());
		LocalDate targetDate = getLocalDate(project.getTargetDate());
		
		context.disableDefaultConstraintViolation();
		
		if(targetDate.isBefore(startDate)){
			context.buildConstraintViolationWithTemplate("Target Date should be after Start Date").addConstraintViolation();
			return false;
		}
		
		if(project.getEndDate() != null) {
			LocalDate endDate = getLocalDate(project.getEndDate());
			
			if(endDate.isBefore(startDate)){
				context.buildConstraintViolationWithTemplate("End Date should be after Start Date").addConstraintViolation();
				return false;
			}
			
        }
		return true;
	}

	private LocalDate getLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
