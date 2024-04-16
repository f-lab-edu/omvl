package com.omvl.omvl.repository;

import static org.springframework.jdbc.datasource.DataSourceUtils.getConnection;

import com.omvl.omvl.domain.Item;
import com.omvl.omvl.domain.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcItemRepository implements ItemRepository{

	private final DataSource dataSource;

	public JdbcItemRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Item findById(Long id) {
		String sql = "select id, itemName, price, type from item where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return getItem(rs);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	@Override
	public List<Item> findByType(String type) {
		String sql = "select id, itemName, price, type from item where type = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			List<Item> items = new ArrayList<>();
			while (rs.next()) {
				Item item = getItem(rs);
				items.add(item);
			}
			return items;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	private static Item getItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setItemName(rs.getString("itemName"));
		item.setPrice(rs.getInt("price"));
		item.setType(rs.getString("type"));
		return item;
	}

	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
	{
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				close(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(Connection conn) throws SQLException {
		DataSourceUtils.releaseConnection(conn, dataSource);
	}
}
