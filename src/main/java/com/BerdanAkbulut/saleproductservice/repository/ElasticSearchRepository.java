package com.BerdanAkbulut.saleproductservice.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.BerdanAkbulut.saleproductservice.dto.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ElasticSearchRepository {

    @Autowired
    ElasticsearchClient elasticsearchClient;

    public void addProduct(String indexName, ProductRequest product) {
        try {
            IndexRequest<ProductRequest> indexRequest = IndexRequest.of(i -> i
                    .index(indexName)
                    .id(product.getName())
                    .document(product));

            IndexResponse indexResponse = elasticsearchClient.index(indexRequest);
            log.debug("Index response: {}", indexResponse.result().jsonValue());
        } catch (IOException e) {
            log.error("ElasticSearch Index Error: {}", e.toString());
        }
    }

    public List<ProductRequest> getAllProducts(String indexName) {
        try {
            SearchRequest searchRequest = SearchRequest.of(i -> i
                    .index(indexName));

            SearchResponse<ProductRequest> searchResponse = elasticsearchClient.search(searchRequest, ProductRequest.class);
            return searchResponse.hits().hits().stream().map(Hit::source).toList();

        }catch (IOException e) {
        log.error("ElasticSearch getAllProducts error: {}", e.toString());
        }
        return Collections.emptyList();
    }
    public ProductRequest getProduct(String indexName, String name) {
        try {
            GetRequest getRequest = GetRequest.of(i -> i
                    .index(indexName)
                    .id(name)
            );
            GetResponse<ProductRequest> getResponse = elasticsearchClient.get(getRequest, ProductRequest.class);
            return getResponse.source();
        } catch (IOException e) {
            log.error("ElasticSearch GetProduct Error: {}", e.toString());
        }
        return null;
    }

}
