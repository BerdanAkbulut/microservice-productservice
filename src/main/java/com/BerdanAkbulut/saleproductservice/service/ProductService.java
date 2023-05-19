package com.BerdanAkbulut.saleproductservice.service;

import com.BerdanAkbulut.saleproductservice.dto.ProductRequest;
import com.BerdanAkbulut.saleproductservice.dto.ProductResponse;
import com.BerdanAkbulut.saleproductservice.exception.ProductNotFoundException;
import com.BerdanAkbulut.saleproductservice.model.Product;
import com.BerdanAkbulut.saleproductservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public ProductResponse getProduct(long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found.", id)));
        return mapToProductResponse(product);
    }

    @Cacheable(value = "products")
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    @CacheEvict(value = "products", allEntries = true)
    public void saveProduct(ProductRequest productRequest) {
        Product product = Product
                .builder()
                .name(productRequest.getName())
                .brand(productRequest.getBrand())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
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
