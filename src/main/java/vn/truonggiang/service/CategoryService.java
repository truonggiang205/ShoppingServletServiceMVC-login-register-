package vn.truonggiang.service;

import java.util.List;
import vn.truonggiang.model.Category;

public interface CategoryService {
	void edit(Category newCategory);
    void insert(Category category);
    void delete(int id);
    Category get(int id);
	Category get(String name);
	List<Category> getAll();
	List<Category> search(String keyword);
}
