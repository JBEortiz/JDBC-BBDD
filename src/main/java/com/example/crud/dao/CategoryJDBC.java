package com.example.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.crud.configuration.Configuration;
import com.example.crud.domain.Category;
import com.example.crud.interfaceDao.CategoryDao;

public class CategoryJDBC implements CategoryDao {

	private Connection conexionTransactionl;

	private static final String SQL_SELECT = "SELECT id, name FROM category";
	private static final String SQL_INSERT = "INSERT INTO category(name) VALUES(?)";
	private static final String SQL_UPDATE = "UPDATE category SET  name=? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM category WHERE id = ?";

	public CategoryJDBC() {
	}

	public CategoryJDBC(Connection conexionTransactionl) {
		this.conexionTransactionl = conexionTransactionl;
	}
	// POSIBLE VERSION PARA MINIMIZAR CODIGO

//	public Connection controlConecction() {
//		Connection conn = null;
//		try {
//			conn= this.conexionTransactionl;
//			if(this.conexionTransactionl!=null) {
//				return this.conexionTransactionl=Conexion.getConnection();
//			}else{
//			    System.out.println("un valor null");
//			}
//		} catch (Exception e) {
//			
//		}
//		 return conn;
//		
//	}

	public List<Category> select() throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Category category = null;
		List<Category> listaCategory = new ArrayList<Category>();

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
				String name = rs.getString("name");

				category = new Category();
				category.setId(id);
				category.setName(name);

				listaCategory.add(category);

			}
		} finally {
			if (this.conexionTransactionl == null) {
				Configuration.close(conn);
			}

			Configuration.close(stmt);
			Configuration.close(rs);

		}
		return listaCategory;
	}

	public int insert(Category category) throws SQLException {
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
			stmt.setString(2, category.getName());

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

	public int update(Category category) throws SQLException {
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
			stmt.setString(1, category.getName());
			stmt.setInt(2, category.getId());

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

	public int delete(Category category) throws SQLException {
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
			stmt.setInt(1, category.getId());
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
