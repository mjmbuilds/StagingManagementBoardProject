package com.staging.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.dao.CategoryDao;
import com.staging.model.Category;

@Service
public class CategoryService {

	private final CategoryDao categoryDao;
	
	@Autowired
	public CategoryService(@Qualifier("fakeDao") CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public int addCategory(Category category) {
		return categoryDao.addCategory(category);
	}
	
	public void updateCategoryd(Category category) {
		categoryDao.updateCategory(category);
	}
	
	public void deleteCategory(UUID id) {
		categoryDao.deleteCategory(id);
	}
}
