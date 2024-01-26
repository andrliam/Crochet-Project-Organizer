package crochet.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crochet.project.controller.model.CrochetProjectCategory;
import crochet.project.controller.model.CrochetProjectData;
import crochet.project.controller.model.CrochetProjectInstruction;
import crochet.project.controller.model.CrochetProjectTools;
import crochet.project.controller.model.CrochetProjectYarn;
import crochet.project.dao.CategoryDao;
import crochet.project.dao.InstructionDao;
import crochet.project.dao.ProjectDao;
import crochet.project.dao.ToolsDao;
import crochet.project.dao.YarnDao;
import crochet.project.entity.Category;
import crochet.project.entity.Instruction;
import crochet.project.entity.Project;
import crochet.project.entity.Tools;
import crochet.project.entity.Yarn;


@Service
public class CrochetProjectService {
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private YarnDao yarnDao;
	
	@Autowired
	private ToolsDao toolsDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private InstructionDao instructionDao;
	
//------------------ Project Methods --------------------------------------------------------------------------------
	
	@Transactional(readOnly = false)
	public CrochetProjectData saveCrochetProject(CrochetProjectData crochetProjectData) {
		Project crochetProject = findOrCreateCrochetProj(crochetProjectData.getProjectId());
		copyCrochetProjectFields(crochetProject, crochetProjectData);
		return new CrochetProjectData(projectDao.save(crochetProject));
	}



	private Project findOrCreateCrochetProj(Long projectId) {
		Project project;
		
		if(Objects.isNull(projectId)) {
			project = new Project();
		}
		else {
			project = findProjectById(projectId);
		}
		return project;
	}



	private Project findProjectById(Long projectId) {
		return projectDao.findById(projectId).orElseThrow(() -> new NoSuchElementException("Project with "
				+ "ID=" + projectId + " was not found."));
	}



	private void copyCrochetProjectFields(Project crochetProject, CrochetProjectData crochetProjectData) {
		crochetProject.setProjectId(crochetProjectData.getProjectId());
		crochetProject.setDifficulty(crochetProjectData.getDifficulty());
		crochetProject.setFinished(crochetProjectData.getFinished());
		crochetProject.setNotes(crochetProjectData.getNotes());
		crochetProject.setProjectName(crochetProjectData.getProjectName());
		crochetProject.setStartDate(crochetProjectData.getStartDate());
	}

	@Transactional(readOnly = false)
	public List<CrochetProjectData> retrieveAllProjects(){
		List<CrochetProjectData> result = new LinkedList<>();
		List<Project> projects = new LinkedList<>(projectDao.findAll());
		for( Project project : projects) {
			CrochetProjectData crochetProjectData = new CrochetProjectData(project);
			
			crochetProjectData.getCategories().clear();
			crochetProjectData.getYarns().clear();
			crochetProjectData.getInstructions().clear();
			
			result.add(crochetProjectData);
		}
		return result;
	}


	@Transactional(readOnly = false)
	public CrochetProjectData retrieveProjectById(Long projectId) {
		return new CrochetProjectData(findProjectById(projectId));
	}


	@Transactional(readOnly = false)
	public void deleteProjectById(Long projectId) {
		Project project = findProjectById(projectId);
		projectDao.delete(project);
	}

// --------------- Yarn methods ----------------------------------------------------------------------------------------
	
	@Transactional(readOnly = false)
	public CrochetProjectYarn saveYarn(Long projectId, CrochetProjectYarn crochetProjectYarn) {
		
		Project project = findProjectById(projectId);
		Long yarnId = crochetProjectYarn.getYarnId();
		Yarn yarn = findOrCreateYarn(projectId, yarnId);
		
		copyYarnFields(yarn, crochetProjectYarn);
		
		yarn.setProject(project);
		project.getYarns().add(yarn);
		
		return new CrochetProjectYarn(yarnDao.save(yarn));
	}


	private Yarn findOrCreateYarn(Long projectId, Long yarnId) {
		Yarn yarn;
		if(Objects.isNull(yarnId)) {
			yarn = new Yarn();
		}
		else {
			yarn = findYarnById(projectId, yarnId);
		}
		return yarn;
	}
	
	private Yarn findYarnById(Long projectId, Long yarnId) {
		Yarn yarn = yarnDao.findById(yarnId).orElseThrow(() -> 
		new NoSuchElementException("Yarn with ID=" + yarnId + " was not found."));
		if (projectId == yarnId) {
			return yarn;
		}
		else {
			throw new IllegalArgumentException("Yarn does not belong to the correct project");
		}
	}
	
	private void copyYarnFields(Yarn yarn, CrochetProjectYarn crochetProjectYarn) {
		yarn.setYarnColor(crochetProjectYarn.getYarnColor());
		yarn.setYarnId(crochetProjectYarn.getYarnId());
		yarn.setYarnMaterial(crochetProjectYarn.getYarnMaterial());
		yarn.setYarnSize(crochetProjectYarn.getYarnSize());
	}
	
	// ---------------------------- Tools Methods ----------------------------------------------------------
	
	@Transactional(readOnly = false)
	public CrochetProjectTools saveTools(Long projectId, CrochetProjectTools crochetProjectTools) {
		Project project = findProjectById(projectId);
		Long toolsId = crochetProjectTools.getToolsId();
		Tools tools = findOrCreateTools(projectId, toolsId);
		
		copyToolsFields(tools, crochetProjectTools);
		
		project.setTools(tools);
		return new CrochetProjectTools(toolsDao.save(tools));
		
	}
	
	private Tools findOrCreateTools(Long projectId, Long toolsId) {
		Tools tools;
		
		if(Objects.isNull(toolsId)) {
			tools = new Tools();
		}
		else {
			tools = findToolsById(projectId, toolsId);
		}
		return tools;
	}
	
	private Tools findToolsById(Long projectId, Long toolsId) {
		return toolsDao.findById(toolsId).orElseThrow(() -> 
			new NoSuchElementException("Tools with ID=" + toolsId + " was not found."));

	}
	
	private void copyToolsFields(Tools tools, CrochetProjectTools crochetProjectTools) {
		tools.setToolsId(crochetProjectTools.getToolsId());
		tools.setHookSize(crochetProjectTools.getHookSize());
		tools.setStitchMarkers(crochetProjectTools.getStitchMarkers());
	}
	
	//------------------- Instruction methods ----------------------------------------------------------
	
	@Transactional(readOnly = false)
	public CrochetProjectInstruction saveInstruction(Long projectId, CrochetProjectInstruction crochetProjectInstruction) {
		
		Project project = findProjectById(projectId);
		Long instructionId = crochetProjectInstruction.getInstructionId();
		Instruction instruction = findOrCreateInstruction(projectId, instructionId);
		
		copyInstructionFields(instruction, crochetProjectInstruction);
		
		instruction.setProject(project);
		project.getInstructions().add(instruction);
		
		return new CrochetProjectInstruction(instructionDao.save(instruction));
	}


	private Instruction findOrCreateInstruction(Long projectId, Long instructionId) {
		Instruction instruction;
		if(Objects.isNull(instructionId)) {
			instruction = new Instruction();
		}
		else {
			instruction = findInstructionById(projectId, instructionId);
		}
		return instruction;
	}
	
	private Instruction findInstructionById(Long projectId, Long instructionId) {
		Instruction instruction = instructionDao.findById(instructionId).orElseThrow(() -> 
		new NoSuchElementException("Instruction with ID=" + instructionId + " was not found."));
		if (projectId == instructionId) {
			return instruction;
		}
		else {
			throw new IllegalArgumentException("Instruction does not belong to the correct project");
		}
	}
	
	private void copyInstructionFields(Instruction instruction, CrochetProjectInstruction crochetProjectInstruction) {
		instruction.setInstructionId(crochetProjectInstruction.getInstructionId());
		instruction.setInstructionOrder(crochetProjectInstruction.getInstructionOrder());
		instruction.setInstructionText(crochetProjectInstruction.getInstructionText());	
	}
	
	// ---------------------- Category Methods ----------------------------------------------------------------------------
	@Transactional(readOnly = false)
	public CrochetProjectCategory saveCategory(Long projectId, CrochetProjectCategory crochetProjectCategory) { 
		Project project = findProjectById(projectId);
		Long categoryId = crochetProjectCategory.getCategoryId();
		Category category = findOrCreateCategory(projectId, categoryId);
		
		copyCategoryFields(category, crochetProjectCategory);
		
		category.getProjects().add(project);
		project.getCategories().add(category);
		
		return new CrochetProjectCategory(categoryDao.save(category));
	}



	private void copyCategoryFields(Category category, CrochetProjectCategory crochetProjectCategory) {
		category.setCategoryId(crochetProjectCategory.getCategoryId());
		category.setCategoryName(crochetProjectCategory.getCategoryName());
	}



	private Category findOrCreateCategory(Long projectId, Long categoryId) {
		Category category;
		if(Objects.isNull(categoryId)) {
			category = new Category();
		}
		else {
			category = findCategoryById(projectId, categoryId);
		}
		return category;
	}



	private Category findCategoryById(Long projectId, Long categoryId) {
		Category category = categoryDao.findById(categoryId).orElseThrow(() -> new NoSuchElementException(""
				+ "Category with ID=" + categoryId + " was not found."));
		
		boolean success = false;
		for (Project project : category.getProjects()) {
			if (project.getProjectId() == projectId && projectId == categoryId) {
				success = true;
			}
		}
		if(success) {return category;} 
		else {throw new IllegalArgumentException("Category does not belong to correct project");}
	}
	
}
