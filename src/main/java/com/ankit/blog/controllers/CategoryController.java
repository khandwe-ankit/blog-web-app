package com.ankit.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.blog.payloads.ApiResponse;
import com.ankit.blog.payloads.CategoryDto;
import com.ankit.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategory() {
		List<CategoryDto> allCategory = this.categoryService.getAllCategories();
		return new ResponseEntity<>(allCategory, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") Long id) {
		CategoryDto categoryById = this.categoryService.getCategoryById(id);
		return new ResponseEntity<>(categoryById, HttpStatus.FOUND);
	}

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable(name = "id") Long id, @Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable(name = "id") Long id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<>(new ApiResponse("Category deleted successfully by id: " + id, true),
		        HttpStatus.OK);
	}
}
