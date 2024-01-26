package crochet.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Tools {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long toolsId;
	
	private Long hookSize;
	
	private Long stitchMarkers;
	
	@OneToOne(mappedBy = "tools")
	private Project project;
	
}
