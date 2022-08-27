package com.ankit.blog.services;

import java.util.List;

import com.ankit.blog.entitys.Category;
import com.ankit.blog.payloads.CategoryDto;

public interface CategoryService {

//	CRUD operations

	CategoryDto createCategory(CategoryDto categoryDto);

	List<CategoryDto> getAllCategories();

	CategoryDto getCategoryById(Long id);

	CategoryDto updateCategory(CategoryDto categoryDto, Long id);

	void deleteCategory(Long id);

	Category categoryDto2Category(CategoryDto categoryDto);

	CategoryDto category2CategoryDto(Category category);

}
