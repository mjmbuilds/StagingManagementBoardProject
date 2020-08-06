package com.staging.data;

import com.staging.model.Category;
import com.staging.model.CodeMessage;

public interface CategoryDao {
	
	CodeMessage addCategory(Category category);
	
	Category getCategory(String id);
	
	CodeMessage updateCategory(Category category);
	
	CodeMessage deleteCategory(String id);
	
}
