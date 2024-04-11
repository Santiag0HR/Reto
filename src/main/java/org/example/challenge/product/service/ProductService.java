package org.example.challenge.product.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.example.challenge.product.model.Product;
import org.example.challenge.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  final
  ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> insertAndListProducts(Product product) throws SQLException, ParseException {
    return productRepository.insertAndListProducts(product);
  }

}
