package crochet.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import crochet.project.entity.Project;

public interface ProjectDao extends JpaRepository<Project, Long>{

}
