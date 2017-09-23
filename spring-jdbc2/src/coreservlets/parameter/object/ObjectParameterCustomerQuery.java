package coreservlets.parameter.object;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import coreservlets.Customer;
import coreservlets.CustomerQuery;

public class ObjectParameterCustomerQuery implements CustomerQuery {

  private SimpleJdbcTemplate jdbc;

  public ObjectParameterCustomerQuery(DataSource dataSource) {
    this.jdbc = new SimpleJdbcTemplate(dataSource);
  }

  public Customer getCustomerByName(String customerName) {
    try{
      return this.jdbc.queryForObject(
        "select id, name from customer where name = ?",
        customerRowMapper,
        customerName);
    }
    catch(EmptyResultDataAccessException e){
      return null;
    }
  }


  private ParameterizedRowMapper<Customer> customerRowMapper =
    new ParameterizedRowMapper<Customer>(){
      public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
        return new Customer(rslt.getString("id"), rslt.getString("name"));
      }
    };
}
