package crochet.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import crochet.project.entity.Yarn;

public interface YarnDao extends JpaRepository<Yarn, Long>{

}
