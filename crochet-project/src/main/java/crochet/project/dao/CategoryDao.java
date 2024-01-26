package crochet.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import crochet.project.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long>{

}
