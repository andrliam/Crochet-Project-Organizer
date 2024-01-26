package crochet.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Yarn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long yarnId;
	
	private String yarnMaterial;
	
	private Long yarnSize;
	
	private String yarnColor;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id")
	private Project project = new Project();
	
}
