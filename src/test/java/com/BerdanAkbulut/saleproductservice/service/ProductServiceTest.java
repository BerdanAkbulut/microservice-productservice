package com.BerdanAkbulut.saleproductservice.service;

import com.BerdanAkbulut.saleproductservice.dto.ProductResponse;
import com.BerdanAkbulut.saleproductservice.exception.ProductNotFoundException;
import com.BerdanAkbulut.saleproductservice.model.Product;
import com.BerdanAkbulut.saleproductservice.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private List<Product> productList = new ArrayList<>();

    @Test
    public void getProductNotNull_test() throws ProductNotFoundException {
        Product product = Product.builder()
                .name("product1")
                .price(BigDecimal.valueOf(150.00))
                .brand("brand").description("good product")
                .build();
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        ProductResponse productResponse = productService.getProduct(1);

        Assertions.assertThat(productResponse).isNotNull();
        Assertions.assertThat(product.getName().equals(productResponse.getName())).isTrue();
    }

    @Test
    public void getProductNull_test(){
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(ProductNotFoundException.class, () ->{
            productService.getProduct(Mockito.anyLong());
        });
    }

    @Test
    public void getAllProducts_test() {
        Product product1 = new Product(1, "product1", "brand1", "desc1", BigDecimal.valueOf(150.00));
        Product product2 = new Product(2, "product2", "brand2", "desc2", BigDecimal.valueOf(250.00));
        productList.add(product1);
        productList.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(productList);
        List<ProductResponse> productResponseList =  productService.getAllProducts();

        Assertions.assertThat(productResponseList.size()).isEqualTo(2);
    }
}
