package crochet.project.controller.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import crochet.project.entity.Category;
import crochet.project.entity.Instruction;
import crochet.project.entity.Project;
import crochet.project.entity.Yarn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrochetProjectData {
	
	private Long projectId;
	
	private String projectName;
	
	private Boolean finished;
	
	private LocalDate startDate;
	
	private String notes;
	
	private Long difficulty;
	
	private Set<CrochetProjectCategory> categories = new HashSet<>();
	
	private Set<CrochetProjectYarn> yarns = new HashSet<>();
	
	private CrochetProjectTools tools;
	
	private Set<CrochetProjectInstruction> instructions = new HashSet<>();
	
	public CrochetProjectData(Project j) {
		projectId = j.getProjectId();
		projectName = j.getProjectName();
		finished = j.getFinished();
		startDate = j.getStartDate();
		notes = j.getNotes();
		difficulty = j.getDifficulty();
		
		for(Category c : j.getCategories()) {
			categories.add(new CrochetProjectCategory(c));
		}
		for(Yarn y : j.getYarns()) {
			yarns.add(new CrochetProjectYarn(y));
		}
		tools = new CrochetProjectTools(j.getTools());
		
		for(Instruction i : j.getInstructions()) {
			instructions.add(new CrochetProjectInstruction(i));
		}
	}
}
