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
public class CategoryService extends GenericService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final CategoryDao categoryDao;
	
	@Autowired // daoQualifier is specified in GenericService
	public CategoryService(@Qualifier(daoQualifier) CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public int addCategory(Category category) {
		log.trace("addCategory()");
		return categoryDao.addCategory(category);
	}
	
	public int updateCategoryd(Category category) {
		log.trace("updateCategoryd()");
		return categoryDao.updateCategory(category);
	}
	
	public int deleteCategory(String id) {
		log.trace("deleteCategory()");
		return categoryDao.deleteCategory(id);
	}
}
