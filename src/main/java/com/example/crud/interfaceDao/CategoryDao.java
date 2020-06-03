package com.example.crud.interfaceDao;

import java.sql.SQLException;
import java.util.List;

import com.example.crud.domain.Category;

public interface CategoryDao {

	public List<Category> select() throws SQLException;

	public int insert(Category category) throws SQLException;

	public int update(Category category) throws SQLException;

	public int delete(Category category) throws SQLException;

}
