package com.ankit.blog.services;

import java.util.List;

import com.ankit.blog.payloads.CategoryDto;

public interface CategoryService {

//	CRUD operations

	CategoryDto createCategory(CategoryDto categoryDto);

	List<CategoryDto> getAllCategories();

	CategoryDto getCategoryById(Long id);

	CategoryDto updateCategory(CategoryDto categoryDto, Long id);

	void deleteCategory(Long id);

}
