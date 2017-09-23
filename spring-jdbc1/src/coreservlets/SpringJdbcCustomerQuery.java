package coreservlets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * Spring JDBC API query demonstration.
 */
public class SpringJdbcCustomerQuery implements CustomerQuery {

  private SimpleJdbcTemplate jdbc;

  public SpringJdbcCustomerQuery(DataSource dataSource) {
    this.jdbc = new SimpleJdbcTemplate(dataSource);
  }

  public Customer getCustomerByName(String customerName) {
    try{
      return this.jdbc.queryForObject(
        "select id, name from customer where name = ?"
        , customerRowMapper
        , customerName);
    }
    catch(EmptyResultDataAccessException e){
      return null;
    }
  }

  public List<Customer> getCustomers() {
    return this.jdbc.<Customer>query(
      "select id, name from customer",
      customerRowMapper);
  }

  private ParameterizedRowMapper<Customer> customerRowMapper =
    new ParameterizedRowMapper<Customer>(){
      public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
        return new Customer(rslt.getString("id"), rslt.getString("name"));
      }
    };
}
