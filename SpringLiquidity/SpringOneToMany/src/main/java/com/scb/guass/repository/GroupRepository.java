package com.scb.guass.repository;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

//import com.scb.guass.model.Acc;
import com.scb.guass.model.Accounts;
import com.scb.guass.model.Groups;
//import com.scb.guass.model.Student;





@Repository
public class GroupRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	public static List<Accounts> list=new ArrayList<Accounts>();
	

    
    @Autowired
    public GroupRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    
    public final static RowMapper<Groups> orderMapper = BeanPropertyRowMapper.newInstance(Groups.class);//.newInstance(Order.class);
    public final static RowMapper<Accounts> lineItemMapper = BeanPropertyRowMapper.newInstance(Accounts.class);

	/*
	 * public Groups findOrderWithItems(Long Id) { return jdbcTemplate.
	 * query("select accountnumber from accounts where groupid in(select groupid from groups where customerid=?)"
	 * , new ResultSetExtractor<Groups>() { public Groups extractData(ResultSet rs)
	 * throws SQLException, DataAccessException { Groups order = null; int row = 0;
	 * while (rs.next()) { if (order == null) { order = orderMapper.mapRow(rs, row);
	 * } order.addItem(lineItemMapper.mapRow(rs, row)); row++; } return order; }
	 * 
	 * }, Id); }
	 */
    
    public int add(Groups grp) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO \"groups\" (groupid,groupname,rateid,customerid) VALUES(?,?,?,?)";
		return jdbcTemplate.update(query, new Object[]{
				grp.getGroupId(), grp.getGroupName(),grp.getRateId(),grp.getCustomerId()

		});
	}

    public int update(Accounts acc) {
		String query = "UPDATE \"accounts\" SET groupid = ? where accountnumber=?";
		int row = jdbcTemplate.update(query,acc.getGroupId(),acc.getAccNo());
        System.out.println(acc.getGroupId());
		return row;
	}
    
	
	  public int update(Groups grp) { String query = "UPDATE \"groups\" SET groupbalance = ?, rateid=? where groupid=?"; 
	  int row = jdbcTemplate.update(query,grp.getGroupBalance(),grp.getRateId(),grp.getGroupId());
	  System.out.println(grp.getGroupId());
	  System.out.println(grp.getGroupBalance());
	  System.out.println(grp.getRateId());
	  return row; }
	 
    public List<Accounts> findOrderWithItem(Long Id)
    {
    	String sql="select * from \"accounts\" where accountnumber=?";
    	RowMapper<Accounts> acc=new AccountRowMapper();
    	list=this.jdbcTemplate.query(sql,acc,Id);
    	return list;
    	
    }
    List<Accounts> acc=new ArrayList<Accounts>();
    public List<Accounts> findAllOrderWithItems(Long Id) {
        /*return jdbcTemplate.query("select * from accounts where customerid=?",

                new ResultSetExtractor<List<Groups>>() {
                    public List<Groups> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Groups> orders = new ArrayList<Groups>();
                        Long orderId = null;
                        Groups currentOrder = null;
                        int orderIdx = 0;
                        int itemIdx = 0;
                        while (rs.next()) {
                            // first row or when order changes
                            if (currentOrder == null || !orderId.equals(rs.getLong("customerid"))) {
                                orderId = rs.getLong("customerid");
                                currentOrder = orderMapper.mapRow(rs, orderIdx++);
                                itemIdx = 0;
                                orders.add(currentOrder);
                            }
                            currentOrder.addItem(lineItemMapper.mapRow(rs, itemIdx++));
                        }
                        return orders;
                    }

                });
    }*/
    	String sql="select * from accounts where customerid=?";
    	RowMapper<Accounts> rowmapper=new AccountRowMapper();
    	
    	acc=this.jdbcTemplate.query(sql,rowmapper,Id);
    	return acc;
    	

}
    List<Groups> grp=new ArrayList<Groups>();
    public List<Groups> findAllOrderWithItems1(Long Id) {
        /*return jdbcTemplate.query("select * from accounts where customerid=?",

                new ResultSetExtractor<List<Groups>>() {
                    public List<Groups> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Groups> orders = new ArrayList<Groups>();
                        Long orderId = null;
                        Groups currentOrder = null;
                        int orderIdx = 0;
                        int itemIdx = 0;
                        while (rs.next()) {
                            // first row or when order changes
                            if (currentOrder == null || !orderId.equals(rs.getLong("customerid"))) {
                                orderId = rs.getLong("customerid");
                                currentOrder = orderMapper.mapRow(rs, orderIdx++);
                                itemIdx = 0;
                                orders.add(currentOrder);
                            }
                            currentOrder.addItem(lineItemMapper.mapRow(rs, itemIdx++));
                        }
                        return orders;
                    }

                });
    }*/
    	String sql="select * from groups where groupid=?";
    	RowMapper<Groups> rowmapper=new GroupRowMapper();
    	
    	grp=this.jdbcTemplate.query(sql,rowmapper,Id);
    	return grp;
    	

}
}
