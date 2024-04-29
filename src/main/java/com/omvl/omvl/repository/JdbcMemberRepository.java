package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.domain.MemberItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcMemberRepository implements MemberRepository {

	private final DataSource dataSource;

	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Member save(Member member) {
		String sql = "insert into member (memberId, memberPassword, type) values (?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setInt(3, member.getType());

			pstmt.execute();

			return member;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}

	}

	@Override
	public Member update(String memberId, Member updateParam) {
		String sql = "update member set memberPassword = ?, type = ? where memberId = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, updateParam.getMemberPassword());
			pstmt.setInt(2, updateParam.getType());
			pstmt.setString(3, memberId);

			pstmt.execute();

			return findByMemberId(memberId);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	@Override
	public Member findById(Long id) {
		String sql = "select id, memberId, memberPassword, type from member where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return getMember(rs);
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
	public Member findByMemberId(String memberId) {
		String sql = "select id, memberId, memberPassword, type from member where memberId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return getMember(rs);
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
	public List<Member> findAll() {
		String sql = "select id, memberId, memberPassword, type from member";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Member> members = new ArrayList<>();
			while (rs.next()) {
				Member member = getMember(rs);
				members.add(member);
			}
			return members;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	@Override
	public boolean addItem(MemberItem memberItem) {
		String sql = "insert into memberItem(memberId, itemName, itemPrice, itemQuantity) values (?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberItem.getMemberId());
			pstmt.setString(2, memberItem.getItemName());
			pstmt.setInt(3, memberItem.getItemPrice());
			pstmt.setInt(4, memberItem.getItemQuantity());
			pstmt.execute();

			return true;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	@Override
	public boolean removeItem(String memberId, String itemName) {
		String sql = "delete from memberItem where memberId = ? and itemName = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, itemName);
			pstmt.execute();

			return true;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	@Override
	public List<MemberItem> findItem(String memberId) {
		String sql = "select memberId, itemName, itemPrice, itemQuantity from memberItem where memberId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			List<MemberItem> memberItems = new ArrayList<>();
			while (rs.next()) {
				MemberItem memberItem = new MemberItem();
				memberItem.setMemberId(rs.getString("memberId"));
				memberItem.setItemName(rs.getString("itemName"));
				memberItem.setItemPrice(rs.getInt("itemPrice"));
				memberItem.setItemQuantity(rs.getInt("itemQuantity"));
				memberItems.add(memberItem);
			}
			return memberItems;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	private static Member getMember(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setId(rs.getLong("id"));
		member.setMemberId(rs.getString("memberId"));
		member.setMemberPassword(rs.getString("memberPassword"));
		member.setType(rs.getInt("type"));
		return member;
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
