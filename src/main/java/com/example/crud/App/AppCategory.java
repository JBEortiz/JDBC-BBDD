package com.example.crud.App;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.crud.configuration.Configuration;
import com.example.crud.dao.CategoryJDBC;
import com.example.crud.dao.ProductJDBC;
import com.example.crud.domain.Category;
import com.example.crud.domain.Product;
import com.example.crud.interfaceDao.CategoryDao;
import com.example.crud.interfaceDao.ProductDao;

public class AppCategory {

	public static void main(String[] args) throws SQLException {
		// TRASACTIONAL
		app();

	}

	public static void app() throws SQLException {
		// TRASACTIONAL

		Connection conexion = Configuration.getConnection();
		try {

			if (conexion.getAutoCommit()) {
				conexion.setAutoCommit(false);
			}

			CategoryDao categoryDao = new CategoryJDBC(conexion);
			Category category1 = new Category();

			// ------- SELECT----
			List<Category> listaCategory = categoryDao.select();
			for (Category category : listaCategory) {
				System.out.println(category);
			}

			// ------- UPDATE----
			category1.setId(4);
			category1.setName("Service");
			categoryDao.update(category1);

			// ------- INSERT----

			Category category = new Category();
			category.setName("Repository");
			categoryDao.insert(category);

			// -------- DELETE----
			category1.setId(9);
			categoryDao.delete(category1);

			ProductDao productDao = new ProductJDBC(conexion);
			Product product = new Product();
			// ------- SELECT----
			List<Product> listaProduct = productDao.select();
			for (Product products : listaProduct) {
				System.out.println(products);
			}

			// ------- INSERT----
			product.setReference("4030");
			product.setName("Rayzen 7 3600x");
			product.setPrice(384.90);
			product.setCategory(1);
			productDao.insert(product);

			// ------- UPDATE----
			product.setId(6);
			product.setReference("1035");
			product.setName("SSD e.B 480 GB");
			product.setPrice(94.30);
			product.setCategory(1);
			productDao.update(product);

			// ------- DELETE----
			productDao.update(product);
			product.setId(13);
			productDao.delete(product);
			for (Product products : listaProduct) {
				System.out.println(products);
			}

			conexion.commit();

		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
