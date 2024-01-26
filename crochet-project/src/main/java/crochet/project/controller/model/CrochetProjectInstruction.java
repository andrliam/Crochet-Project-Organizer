package crochet.project.controller.model;

import crochet.project.entity.Instruction;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrochetProjectInstruction {

	private Long instructionId;
	
	private String instructionText;
	
	private Long instructionOrder;
	
	public CrochetProjectInstruction(Instruction i) {
		instructionId = i.getInstructionId();
		instructionText = i.getInstructionText();
		instructionOrder = i.getInstructionOrder();
	}
}
