package com.BerdanAkbulut.saleproductservice.controller;

import com.BerdanAkbulut.saleproductservice.dto.ProductRequest;
import com.BerdanAkbulut.saleproductservice.dto.ProductResponse;
import com.BerdanAkbulut.saleproductservice.exception.ProductNotFoundException;
import com.BerdanAkbulut.saleproductservice.service.ElasticSearchService;
import com.BerdanAkbulut.saleproductservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ElasticSearchService elasticSearchService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        elasticSearchService.addProduct("products", productRequest);
        productService.saveProduct(productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/elastic")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductRequest> getAllProductsFromElastic() {
        return elasticSearchService.getAllProducts("products");
    }
}
