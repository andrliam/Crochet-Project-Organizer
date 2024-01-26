package crochet.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import crochet.project.entity.Tools;

public interface ToolsDao extends JpaRepository<Tools, Long>{

}
