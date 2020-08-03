package com.staging.data;

import com.staging.model.Category;

public interface CategoryDao {
	
	int addCategory(Category category);
	
	Category getCategory(String id);
	
	int updateCategory(Category category);
	
	int deleteCategory(String id);
	
}
