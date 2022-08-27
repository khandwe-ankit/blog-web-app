package com.ankit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entitys.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}
