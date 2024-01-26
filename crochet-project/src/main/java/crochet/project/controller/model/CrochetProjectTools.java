package crochet.project.controller.model;

import crochet.project.entity.Tools;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrochetProjectTools {
	
	private Long toolsId;
	
	private Long hookSize;
	
	private Long stitchMarkers;
	
	public CrochetProjectTools(Tools t) {
		toolsId = t.getToolsId();
		hookSize = t.getHookSize();
		stitchMarkers = t.getStitchMarkers();
	}
}
