package com.debtrepaymentapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.debtrepaymentapp.model.User;

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    
    public UserDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	public int getUserId(String userName, String userPassword) {
		
		String sql = "SELECT user_id from user WHERE user_name=? AND password=?";
		int result = jdbcTemplate.queryForObject(sql,
                new Object[] {userName, userPassword}, Integer.class);
            
		return result;
	}
	
}
