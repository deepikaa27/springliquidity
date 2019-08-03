package com.scb.guass.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.scb.guass.model.Accounts;
import com.scb.guass.model.Customer;
import com.scb.guass.model.Groups;

@Repository
public class GroupRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private ResultSet rs = null;
	public static List<Accounts> list = new ArrayList<Accounts>();
	public static List<Customer> list1 = new ArrayList<Customer>();
	public static List<Groups> list2 = new ArrayList<Groups>();
	
    
	@Autowired
	public GroupRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public final static RowMapper<Groups> orderMapper = BeanPropertyRowMapper.newInstance(Groups.class);// .newInstance(Order.class);
	public final static RowMapper<Accounts> lineItemMapper = BeanPropertyRowMapper.newInstance(Accounts.class);
	public final static RowMapper<Customer> lineItemMapper1 = BeanPropertyRowMapper.newInstance(Customer.class);
	

	
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
		return jdbcTemplate.update(query,
				new Object[] { grp.getGroupId(), grp.getGroupName(), grp.getRateId(), grp.getCustomerId()

				});
	}

	public int update(Accounts acc) {
		String query = "UPDATE \"accounts\" SET groupid = ? where accountnumber=?";
		int row = jdbcTemplate.update(query, acc.getGroupId(), acc.getAccNo());
		System.out.println(acc.getGroupId());
		return row;
	}

	public int update(Groups grp) {
		String query = "UPDATE \"groups\" SET groupbalance = ?, rateid=? where groupid=?";
		int row = jdbcTemplate.update(query, grp.getGroupBalance(), grp.getRateId(), grp.getGroupId());
		System.out.println(grp.getGroupId());
		System.out.println(grp.getGroupBalance());
		System.out.println(grp.getRateId());
		return row;
	}

	public List<Accounts> findOrderWithItem(Long Id) {
		String sql = "select * from \"accounts\" where accountnumber=?";
		RowMapper<Accounts> acc = new AccountRowMapper();
		list = this.jdbcTemplate.query(sql, acc, Id);
		return list;

	}

	List<Accounts> acc = new ArrayList<Accounts>();

	public List<Accounts> findAllOrderWithItems(Long Id) {
		/*
		 * return jdbcTemplate.query("select * from accounts where customerid=?",
		 * 
		 * new ResultSetExtractor<List<Groups>>() { public List<Groups>
		 * extractData(ResultSet rs) throws SQLException, DataAccessException {
		 * List<Groups> orders = new ArrayList<Groups>(); Long orderId = null; Groups
		 * currentOrder = null; int orderIdx = 0; int itemIdx = 0; while (rs.next()) {
		 * // first row or when order changes if (currentOrder == null ||
		 * !orderId.equals(rs.getLong("customerid"))) { orderId =
		 * rs.getLong("customerid"); currentOrder = orderMapper.mapRow(rs, orderIdx++);
		 * itemIdx = 0; orders.add(currentOrder); }
		 * currentOrder.addItem(lineItemMapper.mapRow(rs, itemIdx++)); } return orders;
		 * }
		 * 
		 * }); }
		 */
		String sql = "select * from accounts where customerid=?";
		RowMapper<Accounts> rowmapper = new AccountRowMapper();

		acc = this.jdbcTemplate.query(sql, rowmapper, Id);
		return acc;

	}

	List<Groups> grp = new ArrayList<Groups>();

	public List<Groups> findAllOrderWithItems1(Long Id) {
		String sql = "select * from groups where customerid=?";
		RowMapper<Groups> rowmapper = new GroupRowMapper();

		grp = this.jdbcTemplate.query(sql, rowmapper, Id);
		return grp;

	}
	public List<Customer> findOrderWithItems2(Long Id) {
		String sql = "select * from \"customer\" where customerid=?";
		RowMapper<Customer> stud = new CustomerRowMapper();
		list1 = this.jdbcTemplate.query(sql, stud, Id);
		return list1;

	}
	public List<Groups> findOrderWithItem1(Long Id) {
		String sql1 = "select * from \"groups\" where groupid=?";
		RowMapper<Groups> grp = new GroupRowMapper();
		list2 = this.jdbcTemplate.query(sql1,grp, Id);
		return list2;

	}
	List<Customer> stud = new ArrayList<Customer>();

	public List<Customer> findAllCustomer() {
		String sql = "select * from \"customer\"";
		RowMapper<Customer> rowmapper = new CustomerRowMapper();

		stud = this.jdbcTemplate.query(sql, rowmapper);
		return stud;

	}
	
	  List<Accounts> acc1 = new ArrayList<Accounts>();
	  Integer a;
	  
	  public List<Accounts> calculateAccountBalance(int Id) {
	  
	  String sql = "select SUM(balance) from accounts where groupid=?";
	  RowMapper<Accounts> rowmapper = new AccountRowMapper(); 
	  System.out.println("Testing ");
	  System.out.println("Test1 :" + this.jdbcTemplate.query(sql, rowmapper,new Integer(Id)));
	  list=this.jdbcTemplate.query(sql, rowmapper,new Integer(Id));
	  
	  System.out.println("sql query :"+sql); 
	  return list;
	  
	  }
	  
	  
	  List<Accounts> acc2 = new ArrayList<Accounts>();
	  
	  
	  
	  
	  public boolean calculateAccountBalance1(int id) {
		  
		  String sql = "select SUM(balance) from \"accounts\" where \"groupid\"=?";
		  RowMapper<Double> rowmapper = new DoubleRowMapper(); 
		  
		  List<Double> acc=this.jdbcTemplate.query(sql, rowmapper,id);
		  
		  Iterator<Double> itr=acc.iterator();
		  while(itr.hasNext())
		  {
			  System.out.println(itr.next());
		  }
		  
	      return true;
		  
		  }
	  public int updatebalance(Groups grp) {
			String query = "UPDATE \"groups\" SET groupbalance = ? rateid=? where groupid=?";
			int row = jdbcTemplate.update(query, grp.getGroupBalance(),grp.getRateId(), grp.getGroupId());
			System.out.println(grp.getGroupId());
			System.out.println(grp.getRateId());
			System.out.println(grp.getGroupBalance());
			return row;
		}
	
	 public boolean calculateInterest(int id,Groups grp1)
	 {
		 
		String sql = "select groupbalance,creditpercent,debitpercent from \"groups\" where \"groupid\"=?";
		RowMapper<Groups> rowmapper = new InterestCalculationRowMapper(); 
		  
		  List<Groups> grp=this.jdbcTemplate.query(sql, rowmapper,id);
		  
		  Iterator<Groups> itr=grp.iterator();
		  while(itr.hasNext())
		  {
			  System.out.println(itr.next());
		     if(grp1.getGroupBalance()>0)
		     {
			   double interest=(grp1.getGroupBalance()*grp1.getCreditpercent())/(365*100);
			   System.out.println("interest:"+interest);
			   double totalbalance=grp1.getGroupBalance()+interest;
			   System.out.println("Total balance:"+totalbalance);
		     }
		  }
		return true;
		 
	 }

	
}





