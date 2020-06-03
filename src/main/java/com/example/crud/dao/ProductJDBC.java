package com.example.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.crud.configuration.Configuration;
import com.example.crud.domain.Product;
import com.example.crud.interfaceDao.ProductDao;

public class ProductJDBC implements ProductDao {

	private Connection conexionTransactionl;

	private static final String SQL_SELECT = "SELECT id, reference, name, price, category FROM product";
	private static final String SQL_INSERT = "INSERT INTO product(reference, name, price, category) VALUES(?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE product SET reference=?, name=?, price=?, category=? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM product WHERE id = ?";

	public ProductJDBC(Connection conexionTransactionl) {
		this.conexionTransactionl = conexionTransactionl;
	}

	public ProductJDBC() {
	}

	public List<Product> select() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product products = null;
		List<Product> listaProduct = new ArrayList<Product>();
		try {
			conn = this.conexionTransactionl;
			if (this.conexionTransactionl != null) {
				this.conexionTransactionl = Configuration.getConnection();
			} else {
				System.out.println("un valor null");
			}
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("id");
				String reference = rs.getString("reference");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int category = rs.getInt("category");

				products = new Product();
				products.setId(id);
				products.setReference(reference);
				products.setName(name);
				products.setPrice(price);
				products.setCategory(category);

				listaProduct.add(products);

			}

		} finally {
			if (this.conexionTransactionl == null) {
				Configuration.close(conn);
			}
			Configuration.close(stmt);
			Configuration.close(rs);

		}
		return listaProduct;
	}

	public int insert(Product product) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int rows = 0;

		try {
			conn = this.conexionTransactionl;
			if (this.conexionTransactionl != null) {
				this.conexionTransactionl = Configuration.getConnection();
			} else {
				System.out.println("un valor null");
			}
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, product.getReference());
			stmt.setString(2, product.getName());
			stmt.setDouble(3, product.getPrice());
			stmt.setInt(4, product.getCategory());

			System.out.println("ejecutando query:" + SQL_INSERT);
			rows = stmt.executeUpdate();
			System.out.println("Registro introducidos" + rows);

		} finally {
			if (this.conexionTransactionl == null) {
				Configuration.close(conn);
			}
			Configuration.close(stmt);

		}
		return rows;

	}

	public int update(Product product) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {
			conn = this.conexionTransactionl;
			if (this.conexionTransactionl != null) {
				this.conexionTransactionl = Configuration.getConnection();
			} else {
				System.out.println("un valor null");
			}
			System.out.println("ejecutando query:" + SQL_UPDATE);
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, product.getReference());
			stmt.setString(2, product.getName());
			stmt.setDouble(3, product.getPrice());
			stmt.setInt(4, product.getCategory());
			stmt.setInt(5, product.getId());

			rows = stmt.executeUpdate();
			System.out.println("Registro modificados " + rows);

		} finally {
			if (this.conexionTransactionl == null) {
				Configuration.close(conn);
			}
			Configuration.close(stmt);

		}
		return rows;
	}

	public int delete(Product product) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = this.conexionTransactionl;
			if (this.conexionTransactionl != null) {
				this.conexionTransactionl = Configuration.getConnection();
			} else {
				System.out.println("un valor null");
			}
			System.out.println("ejecutando query:" + SQL_DELETE);
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(5, product.getId());
			rows = stmt.executeUpdate();
			System.out.println("Registro eliminados " + rows);

		} finally {
			if (this.conexionTransactionl == null) {
				Configuration.close(conn);
			}
			Configuration.close(stmt);

		}
		return rows;

	}

}
