package com.staging.data;

import java.util.UUID;

import com.staging.model.Category;

public interface CategoryDao {
	
	int addCategory(Category category);
	
	int updateCategory(Category category);
	
	int deleteCategory(String id);
	
}
