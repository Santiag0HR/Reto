package org.example.challenge.product.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.example.challenge.product.model.Product;
import org.example.challenge.product.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  final
  ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping("/list")
  @ResponseBody
  public List<Product> insertAndListProducts(@RequestBody Product product)
      throws SQLException, ParseException {
    return productService.insertAndListProducts(product);
  }

}
