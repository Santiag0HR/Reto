package org.example.challenge.product.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import org.example.challenge.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends JdbcDaoSupport {

  private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDs(DataSource dataSource) {
    setDataSource(dataSource);
  }


  public List<Product> insertAndListProducts(Product product)
      throws ParseException {
    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
        .withProcedureName("insert_product")
        .declareParameters(
            new SqlParameter("p_id", Types.INTEGER),
            new SqlParameter("p_name", Types.VARCHAR),
            new SqlParameter("p_registration_date", Types.VARCHAR),
            new SqlOutParameter("p_product_cursor", OracleTypes.CURSOR),
            new SqlOutParameter("p_return_code", Types.INTEGER),
            new SqlOutParameter("p_return_message", Types.VARCHAR)
        );
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    String formattedDate = formatter.format(new Date(System.currentTimeMillis()));
    MapSqlParameterSource inParams = new MapSqlParameterSource()
        .addValue("p_id", product.getId())
        .addValue("p_name", product.getName())
        .addValue("p_registration_date", formattedDate);

    Map<String, Object> result = simpleJdbcCall.execute(inParams);

    int returnCode = (int) result.get("p_return_code");
    String returnMessage = (String) result.get("p_return_message");

    if (returnCode == 0) {
      List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("p_product_cursor");
      List<Product> products = new ArrayList<>();
      for (Map<String, Object> row : rows) {
        Product productResult = new Product();
        productResult.setId(((BigDecimal) row.get("id")).intValue());
        productResult.setName((String) row.get("name"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date registrationDate = new Date(dateFormat.parse((String) row.get("registration_date")).getTime());
        productResult.setRegistrationDate(registrationDate);
        products.add(productResult);
      }
      return products;
    } else {
      logger.error("Error executing stored procedure: {}", returnMessage);
      return null;
    }
  }


}
