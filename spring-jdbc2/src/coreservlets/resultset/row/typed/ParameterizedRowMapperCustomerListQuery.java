package coreservlets.resultset.row.typed;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import coreservlets.Customer;
import coreservlets.CustomerListQuery;

public class ParameterizedRowMapperCustomerListQuery
implements CustomerListQuery {

  private SimpleJdbcTemplate simpleJdbc;
  
  public ParameterizedRowMapperCustomerListQuery(DataSource dataSource) {
    this.simpleJdbc = new SimpleJdbcTemplate(dataSource);
  }

  public List<Customer> getCustomers() {
    return this.simpleJdbc.<Customer>query(
      "select id, name from customer", customerRowMapper);
  }

  private static final ParameterizedRowMapper<Customer> customerRowMapper =
      new ParameterizedRowMapper<Customer>(){
    public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
      return new Customer(rslt.getString("id"), rslt.getString("name"));
    }
  };
}
