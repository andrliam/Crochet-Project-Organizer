package crochet.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import crochet.project.controller.model.CrochetProjectCategory;
import crochet.project.controller.model.CrochetProjectData;
import crochet.project.controller.model.CrochetProjectInstruction;
import crochet.project.controller.model.CrochetProjectTools;
import crochet.project.controller.model.CrochetProjectYarn;
import crochet.project.service.CrochetProjectService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/crochet_project")
@Slf4j
public class CrochetProjectController {

		@Autowired
		private CrochetProjectService crochetProjectService;
		
		@PostMapping
		@ResponseStatus(code = HttpStatus.CREATED)
		public CrochetProjectData insertCrochetProject(@RequestBody CrochetProjectData crochetProjectData) {
			log.info("Creating crochet project {}", crochetProjectData);
			return crochetProjectService.saveCrochetProject(crochetProjectData);
		}
		
		@PostMapping("/{projectId}/yarn")
		@ResponseStatus(code = HttpStatus.CREATED)
		public CrochetProjectYarn insertYarn(@PathVariable Long projectId, @RequestBody CrochetProjectYarn crochetProjectYarn) {
			log.info("Creating yarn {}", crochetProjectYarn);
			return crochetProjectService.saveYarn(projectId, crochetProjectYarn);
		}
		
		@PutMapping("/{projectId}/tools")
		public CrochetProjectTools updateTools(@PathVariable Long projectId, @RequestBody CrochetProjectTools crochetProjectTools) {
			log.info("Updating project tools {}", crochetProjectTools);
			return crochetProjectService.saveTools(projectId, crochetProjectTools);
		}
		
		@PostMapping("/{projectId}/tools")
		@ResponseStatus(code = HttpStatus.CREATED)
		public CrochetProjectTools insertTools(@PathVariable Long projectId, @RequestBody CrochetProjectTools crochetProjectTools) {
			log.info("Creating tool set {}", crochetProjectTools);
			return crochetProjectService.saveTools(projectId, crochetProjectTools);
		}
		
		@PostMapping("/{projectId}/instruction")
		@ResponseStatus(code = HttpStatus.CREATED)
		public CrochetProjectInstruction insertInstruction(@PathVariable Long projectId, @RequestBody CrochetProjectInstruction crochetProjectInstruction) {
			log.info("Creating yarn {}", crochetProjectInstruction);
			return crochetProjectService.saveInstruction(projectId, crochetProjectInstruction);
		}
		
		@PostMapping("/{projectId}/category")
		@ResponseStatus(code = HttpStatus.CREATED)
		public CrochetProjectCategory insertCategory(@PathVariable Long projectId, @RequestBody CrochetProjectCategory crochetProjectCategory) {
			log.info("Creating category {}", crochetProjectCategory);
			return crochetProjectService.saveCategory(projectId, crochetProjectCategory);
		}
		
		@PutMapping("/{projectId}")
		public CrochetProjectData updateCrochetProject(@PathVariable Long projectId, @RequestBody CrochetProjectData crochetProjectData) {
			crochetProjectData.setProjectId(projectId);
			log.info("Updating project {}", crochetProjectData);
			return crochetProjectService.saveCrochetProject(crochetProjectData);
		}
		
		@GetMapping
		public List<CrochetProjectData> listPetStores(){
			return crochetProjectService.retrieveAllProjects();

		}
		
		@GetMapping("/{projectId}")
		public CrochetProjectData retrieveProject(@PathVariable Long projectId) {
			return crochetProjectService.retrieveProjectById(projectId);
		}
		
		@DeleteMapping("/{projectId}")
		public Map<String, String> deletePetStore(@PathVariable Long projectId) {
			log.info("Deleting project {}", projectId);
			crochetProjectService.deleteProjectById(projectId);
			return Map.of("message", "deletion of project with id: " + projectId + " was successful");
		}
}
