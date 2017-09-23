package coreservlets.parameter.sqlparametersource;

import static java.sql.Types.VARCHAR;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import coreservlets.Customer;
import coreservlets.CustomerQuery;

public class SqlParameterSourceCustomerQuery implements CustomerQuery {

  private SimpleJdbcTemplate jdbc;

  public SqlParameterSourceCustomerQuery(DataSource dataSource) {
    this.jdbc = new SimpleJdbcTemplate(dataSource);
  }

  public Customer getCustomerByName(String customerName) {
    try{
      SqlParameterSource parameterMap = parameterize(customerName);
      return this.jdbc.queryForObject(
        "select id, name from customer where name = :customerName",
        customerRowMapper,
        parameterMap);
    }
    catch(EmptyResultDataAccessException e){
      return null;
    }
  }

  private SqlParameterSource parameterize(String customerName) {
    
    MapSqlParameterSource parameterMap = new MapSqlParameterSource();
    parameterMap.addValue("customerName", customerName, VARCHAR);
    return parameterMap;
  }
  
  private ParameterizedRowMapper<Customer> customerRowMapper =
    new ParameterizedRowMapper<Customer>(){
      public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
        return new Customer(rslt.getString("id"), rslt.getString("name"));
      }
    };
}
