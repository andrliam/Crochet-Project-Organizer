package crochet.project.controller.model;

import java.util.HashSet;
import java.util.Set;

import crochet.project.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrochetProjectCategory {

	
	private Long categoryId;
	
	private String categoryName;
	
	private Set<CrochetProjectData> projects = new HashSet<>();
	
	public CrochetProjectCategory(Category c) {
		categoryId = c.getCategoryId();
		categoryName = c.getCategoryName();
		
	}
}
