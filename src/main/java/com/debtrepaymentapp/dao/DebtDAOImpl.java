package com.debtrepaymentapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.debtrepaymentapp.model.Debt;
import com.debtrepaymentapp.model.User;


public class DebtDAOImpl implements DebtDAO {

    private JdbcTemplate jdbcTemplate;
    
    public DebtDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    public void saveOrUpdate(Debt debt,Integer userID) {
        if (debt.getDebtID() > 0) {
            // update
            String sql = "UPDATE debt SET debt_name=?, payment=?, rate=?, balance=? WHERE debt_id=?";
            jdbcTemplate.update(sql, debt.getPayment(), debt.getRate(),
            		debt.getBalance(), debt.getDebtName(), debt.getDebtID());
        } else {
            // insert
            String sql = "INSERT INTO debt (user_id, debt_name, payment, rate, balance)"
                        + " VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, userID, debt.getDebtName(), debt.getPayment(),
            		debt.getRate(), debt.getBalance());
        }
     
    }
 
    public void delete(int debtId) {
    	   String sql = "DELETE FROM debt WHERE debtId=?";
    	    jdbcTemplate.update(sql, debtId);
    	    
    }
 
    public List<Debt> list(final int userID) {
        String sql = "SELECT * FROM debt WHERE user_id =?";
        System.out.println(sql);
        /*List<Debt> listDebts = jdbcTemplate.query(sql, new Object[] {1}, new RowMapper<Debt>() {*/
            List<Debt> listDebts = jdbcTemplate.query(sql, new Object[] {userID}, new RowMapper<Debt>() {
     
            public Debt mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Debt d = new Debt();
            	d.setUserID(userID);
                d.setDebtID(rs.getInt("debt_id"));
                d.setDebtName(rs.getString("debt_name"));
                d.setPayment(rs.getDouble("payment"));
                d.setRate(rs.getDouble("rate"));
                d.setBalance(rs.getDouble("balance"));
                System.out.println("d = " + d.getDebtName());
                return d;
            }
            
     
        });
     
        return listDebts;
    }

    public Debt get(int debtId) {
        String sql = "SELECT * FROM debt WHERE debt_id=" + debtId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Debt>() {
     
            public Debt extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                	
                	Debt d2 = new Debt();
                	d2.setDebtID(rs.getInt("debt_id"));
                	d2.setDebtName(rs.getString("debt_name"));
                	d2.setPayment(rs.getDouble("payment"));
                	d2.setRate(rs.getDouble("rate"));
                	d2.setBalance(rs.getDouble("balance"));
                	
                    return d2;
                }
     
                return null;
            }
     
        });
    }
    
    public void createUser(User user) {
    	
        String sql = "INSERT INTO user (user_name, email, password, user_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserName(), user.getUserEmail(), user.getUserPassword(), "");
    }
}
