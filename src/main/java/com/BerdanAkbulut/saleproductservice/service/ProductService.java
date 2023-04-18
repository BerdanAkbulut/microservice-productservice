package com.BerdanAkbulut.saleproductservice.service;

import com.BerdanAkbulut.saleproductservice.dto.ProductRequest;
import com.BerdanAkbulut.saleproductservice.dto.ProductResponse;
import com.BerdanAkbulut.saleproductservice.model.Product;
import com.BerdanAkbulut.saleproductservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public ProductResponse getProduct(long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) return null;
        return mapToProductResponse(product);
    }
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }
    public void saveProduct(ProductRequest productRequest) {
        Product product = Product
                .builder()
                .name(productRequest.getName())
                .brand(productRequest.getBrand())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        log.info("Product with id {} is saved", product.getId());
    }
    public void deleteProduct(long id) {
         productRepository.deleteById(id);
    }

    public ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
