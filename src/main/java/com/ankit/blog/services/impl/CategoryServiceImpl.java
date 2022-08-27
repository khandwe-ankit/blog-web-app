package com.ankit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.blog.config.ConstantsAndLiterals;
import com.ankit.blog.entitys.Category;
import com.ankit.blog.exceptions.ResourceNotFoundException;
import com.ankit.blog.payloads.CategoryDto;
import com.ankit.blog.repositories.CategoryRepo;
import com.ankit.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.categoryDto2Category(categoryDto);
		return this.category2CategoryDto(
		        this.categoryRepo.save(category));
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		return this.categoryRepo.findAll()
		        .stream()
		        .map(this::category2CategoryDto)
		        .collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(Long id) {

		return this.category2CategoryDto(
		        this.categoryRepo.findById(id)
		                .orElseThrow(() -> new ResourceNotFoundException(ConstantsAndLiterals.CATEGORY_LOWERCASE,
		                        ConstantsAndLiterals.CATEGORY_ID, id)));
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
		Category category = this.categoryDto2Category(categoryDto);
		Category fetchedcategory = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
		        ConstantsAndLiterals.CATEGORY_LOWERCASE, ConstantsAndLiterals.CATEGORY_ID, id));
		fetchedcategory.setTitle(category.getTitle());
		fetchedcategory.setDescription(category.getDescription());
		return this.category2CategoryDto(this.categoryRepo.saveAndFlush(fetchedcategory));
	}

	@Override
	public void deleteCategory(Long id) {
		Category fetchedcategory = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
		        ConstantsAndLiterals.CATEGORY_LOWERCASE, ConstantsAndLiterals.CATEGORY_ID, id));
		this.categoryRepo.delete(fetchedcategory);
	}

	private CategoryDto category2CategoryDto(Category category) {
		return this.modelMapper.map(category, CategoryDto.class);
	}

	private Category categoryDto2Category(CategoryDto categoryDto) {
		return this.modelMapper.map(categoryDto, Category.class);
	}
}
