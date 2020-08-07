package com.staging.data;

import com.staging.model.Category;
import com.staging.model.CodeMessage;
import com.staging.model.IndexList;

public interface CategoryDao {
	
	CodeMessage updateCategoryIndexList(IndexList indexList);
	
	CodeMessage addCategory(Category category);
	
	Category getCategory(String id);
	
	CodeMessage updateCategory(Category category);
	
	CodeMessage deleteCategory(String id);
	
}
