package org.example.challenge.product.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import org.example.challenge.product.model.Product;
import org.example.challenge.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductControllerTest {


  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

  @Test
  public void testInsertAndListProducts() throws SQLException, ParseException {
    Product product = new Product();
    product.setId(1);
    product.setName("Test Product");
    List<Product> expectedProducts = Collections.singletonList(product);
    when(productService.insertAndListProducts(product)).thenReturn(expectedProducts);
    List<Product> actualProducts = productController.insertAndListProducts(product);
    assertEquals(expectedProducts, actualProducts);
  }

  @Test
  public void testInsertAndListProductsThrowsException() throws SQLException, ParseException {
    Product product = new Product();
    product.setId(1);
    product.setName("Test Product");
    when(productService.insertAndListProducts(product)).thenThrow(SQLException.class);
    assertThrows(SQLException.class, () -> productController.insertAndListProducts(product));
  }
}