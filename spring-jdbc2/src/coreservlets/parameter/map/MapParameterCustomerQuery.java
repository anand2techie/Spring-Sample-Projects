package coreservlets.parameter.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import coreservlets.Customer;
import coreservlets.CustomerQuery;

public class MapParameterCustomerQuery implements CustomerQuery {

  private SimpleJdbcTemplate jdbc;

  public MapParameterCustomerQuery(DataSource dataSource) {
    this.jdbc = new SimpleJdbcTemplate(dataSource);
  }

  public Customer getCustomerByName(String customerName) {
    try{
      Map<String, Object>parameterMap = parameterize(customerName);
      return this.jdbc.queryForObject(
        "select id, name from customer where name = :customerName",
        customerRowMapper,
        parameterMap);
    }
    catch(EmptyResultDataAccessException e){
      return null;
    }
  }

  private Map<String, Object> parameterize(String customerName) {
    Map<String, Object>parameterMap = new HashMap<String, Object>();
    parameterMap.put("customerName", customerName);
    return parameterMap;
  }

  private ParameterizedRowMapper<Customer> customerRowMapper =
    new ParameterizedRowMapper<Customer>(){
      public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
        return new Customer(rslt.getString("id"), rslt.getString("name"));
      }
    };
}
