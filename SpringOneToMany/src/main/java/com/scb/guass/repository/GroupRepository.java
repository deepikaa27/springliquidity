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

import com.scb.guass.model.Accounts;
import com.scb.guass.model.Groups;



@Repository
public class GroupRepository {

    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GroupRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public final static RowMapper<Groups> orderMapper = BeanPropertyRowMapper.newInstance(Groups.class);//.newInstance(Order.class);
    public final static RowMapper<Accounts> lineItemMapper = BeanPropertyRowMapper.newInstance(Accounts.class);

    public Groups findOrderWithItems(Long Id) {
        return jdbcTemplate.query("select * from accounts,groups"
                + " where accounts.customerid = groups.customerid or accounts.customerid = ?", 
                new ResultSetExtractor<Groups>() {
            public Groups extractData(ResultSet rs) throws SQLException, DataAccessException {
            	Groups order = null;
                int row = 0;
                while (rs.next()) {
                    if (order == null) {
                        order = orderMapper.mapRow(rs, row);
                    }
                    order.addItem(lineItemMapper.mapRow(rs, row));
                    row++;
                }
                return order;
            }

        }, Id);
    }

    public List<Groups> findAllOrderWithItmes() {
        return jdbcTemplate.query("select * from accounts,groups"
                + " where accounts.customerid = groups.customerid order by accounts.customerid",
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
    }

}
