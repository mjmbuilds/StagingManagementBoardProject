package com.staging.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.data.CategoryDao;
import com.staging.model.Category;

@Service
public class CategoryService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final CategoryDao categoryDao;
	
	@Autowired
	public CategoryService(@Qualifier("fakeDao") CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public int addCategory(Category category) {
		log.trace("addCategory()");
		return categoryDao.addCategory(category);
	}
	
	public void updateCategoryd(Category category) {
		log.trace("updateCategoryd()");
		categoryDao.updateCategory(category);
	}
	
	public void deleteCategory(UUID id) {
		log.trace("deleteCategory()");
		categoryDao.deleteCategory(id);
	}
}
