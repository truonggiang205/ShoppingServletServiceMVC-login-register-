package vn.truonggiang.service.impl;

import java.util.List;
import java.io.File;
import vn.truonggiang.dao.CategoryDAO;
import vn.truonggiang.dao.impl.CategoryDAOImpl;
import vn.truonggiang.model.Category;
import vn.truonggiang.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	CategoryDAO categoryDao = new CategoryDAOImpl();

	@Override
	public void edit(Category newCategory) {
		Category oldCate = categoryDao.get(newCategory.getCateid());
		oldCate.setCatename(newCategory.getCatename());
		categoryDao.edit(oldCate);
	}

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<Category> search(String catename) {
		return categoryDao.search(catename);
	}


}
