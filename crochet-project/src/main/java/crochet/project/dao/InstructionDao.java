package crochet.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import crochet.project.entity.Instruction;

public interface InstructionDao extends JpaRepository<Instruction, Long>{

}
