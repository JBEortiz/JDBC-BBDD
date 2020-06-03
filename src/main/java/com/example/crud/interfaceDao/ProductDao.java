package com.example.crud.interfaceDao;

import java.sql.SQLException;
import java.util.List;

import com.example.crud.domain.Product;

public interface ProductDao {

	public List<Product> select() throws SQLException;

	public int insert(Product product) throws SQLException;

	public int update(Product product) throws SQLException;

	public int delete(Product product) throws SQLException;

}
