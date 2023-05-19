package com.BerdanAkbulut.saleproductservice.service;

import com.BerdanAkbulut.saleproductservice.dto.ProductRequest;
import com.BerdanAkbulut.saleproductservice.model.Product;
import com.BerdanAkbulut.saleproductservice.repository.ElasticSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchService {

    private final ElasticSearchRepository elasticSearchRepository;

    public void addProduct(String indexName, ProductRequest product) {
        if(!StringUtils.hasLength(indexName) || product == null) {
            return;
        }
        elasticSearchRepository.addProduct(indexName, product);
    }

    public List<ProductRequest> getAllProducts(String indexName) {
        return elasticSearchRepository.getAllProducts(indexName);
    }

}
