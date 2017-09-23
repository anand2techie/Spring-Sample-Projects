package coreservlets.update;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import coreservlets.Customer;
import coreservlets.CustomerUpdate;

public class SpringCustomerUpdate implements CustomerUpdate {

  private SimpleJdbcTemplate simpleJdbc;

  public SpringCustomerUpdate(DataSource dataSource) {
    this.simpleJdbc = new SimpleJdbcTemplate(dataSource);
  }

  public void save(Customer customer) {
    Map<String, Object>parameters = parameterize(customer);
    
    boolean updated = 0 < simpleJdbc.update(
      "update customer set name = :customerName where id = :customerId",
      parameters);
    
    if(updated){
      return;
    }
    
    simpleJdbc.update(
      "insert into customer (id, name) values (:customerId, :customerName)",
      parameters);
  }
  
  public void deleteById (String customerId) {
    simpleJdbc.update("delete from customer where id = ?", customerId);
  }
  
  private static Map<String, Object> parameterize(Customer customer){
    Map<String, Object> parameterMap = new HashMap<String, Object>();
    parameterMap.put("customerId", customer.getId());
    parameterMap.put("customerName", customer.getName());
    return parameterMap;
  }

}
