package com.staging.data;

import java.util.UUID;

import com.staging.model.Category;

public interface CategoryDao {
	
	int addCategory(Category category);
	
	void updateCategory(Category category);
	
	void deleteCategory(UUID id);
	
}
