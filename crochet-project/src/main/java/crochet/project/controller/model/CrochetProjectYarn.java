package crochet.project.controller.model;


import crochet.project.entity.Yarn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrochetProjectYarn {
	private Long yarnId;
	
	private String yarnMaterial;
	
	private Long yarnSize;
	
	private String yarnColor;
	
	public CrochetProjectYarn(Yarn y) {
		yarnId = y.getYarnId();
		yarnMaterial = y.getYarnMaterial();
		yarnSize = y.getYarnSize();
		yarnColor = y.getYarnColor();
	}
}
