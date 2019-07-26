package com.tb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;

import com.tb.model.Group;

@Repository
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * @Override public int add(final Student student) { String query =
	 * "INSERT INTO STUDENT(FIRST_NAME,LAST_NAME) VALUES(?,?)";
	 * 
	 * KeyHolder holder = new GeneratedKeyHolder(); jdbcTemplate.update(new
	 * PreparedStatementCreator() {
	 * 
	 * @Override public PreparedStatement createPreparedStatement(Connection
	 * connection) throws SQLException { PreparedStatement ps =
	 * connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	 * ps.setString(1, student.getFirstName()); ps.setString(2,
	 * student.getLastName());
	 * 
	 * return ps; } }, holder);
	 * 
	 * int newUserId = holder.getKey().intValue(); 
	 * return newUserId; 
	 * }
	 */
	static{
		System.out.println("DAO");
	
	}

	@Override
	public List <Group> list() {
		String query = "SELECT * FROM \"group\" ";
		return jdbcTemplate.query(query, new GroupMapper());

	}

	@Override
	public int update(Group group) {
		String query = "UPDATE \"group\" SET groupname = ?, rateid= ?, accno=? WHERE groupid = ?";
		int row = jdbcTemplate.update(query, group.getGroupId(),group.getGroupName(), group.getRateId(), group.getAccountNo());

		return row;
	}

	@Override
	public Group get(int groupId) {
		String query = "SELECT * FROM \"group\" WHERE groupid = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { groupId }, new GroupMapper());
	}

	
	class GroupMapper implements RowMapper<Group> {

		@Override
		public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
			Group group = new Group();
			group.setGroupId(rs.getInt("groupid"));
			group.setGroupName(rs.getString("groupname"));
			group.setRateId(rs.getString("rateid"));
			group.setAccountNo(rs.getInt("accno"));

			return group;
		}

	}

	@Override
	public int delete(int groupId) {
		String query = "DELETE FROM \"group\" WHERE groupid = ?";
		int row = jdbcTemplate.update(query, groupId);

		return row;
	}

	@Override
	public int add(Group group) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO \"group\" (groupid,groupname,rateid,accno) VALUES(?,?,?,?)";
		return jdbcTemplate.update(query, new Object[]{
				group.getGroupId(), group.getGroupName(),group.getRateId(),group.getAccountNo()
		});
	}

}
