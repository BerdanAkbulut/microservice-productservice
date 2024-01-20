// package com.BerdanAkbulut.saleproductservice.controller;

// import com.BerdanAkbulut.saleproductservice.dto.ProductRequest;
// import com.BerdanAkbulut.saleproductservice.dto.ProductResponse;
// import com.BerdanAkbulut.saleproductservice.exception.ProductNotFoundException;
// import com.BerdanAkbulut.saleproductservice.service.ProductService;
// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.hamcrest.CoreMatchers;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.ResultActions;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import java.math.BigDecimal;
// import java.util.Collections;
// import java.util.List;

// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


// @WebMvcTest(controllers = ProductController.class)
// @AutoConfigureMockMvc
// @ExtendWith(MockitoExtension.class)
// public class ProductControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private ProductService productService;

//     @Autowired
//     private ObjectMapper objectMapper;

//     private ProductResponse productResponse;

//     private ProductRequest productRequest;

//     @BeforeEach
//     public void init() {
//         productResponse = ProductResponse.builder()
//                 .id(1)
//                 .description("test desc")
//                 .price(BigDecimal.valueOf(20.00))
//                 .name("test name")
//                 .brand("test brand").build();

//         productRequest = ProductRequest.builder()
//                 .name("test product")
//                 .price(BigDecimal.valueOf(20.00))
//                 .brand("test brand")
//                 .description("test desc")
//                 .build();
//     }
//     @Test
//     public void getProduct_returnProduct_test() throws Exception {
//         when(productService.getProduct(1)).thenReturn(productResponse);
//         ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")
//                 .contentType(MediaType.APPLICATION_JSON));

//         response
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productResponse.getName())));
//     }

//     @Test
//     public void getProduct_throwProductNotFoundException_test() throws Exception {
//         when(productService.getProduct(1)).thenThrow(new ProductNotFoundException(""));
//         ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")
//                 .contentType(MediaType.APPLICATION_JSON));

//         response
//                 .andExpect(MockMvcResultMatchers.status().isNotFound())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage", CoreMatchers.is("")));
//     }

//     @Test
//     public void saveProduct_Created_test() throws Exception {
//         doNothing().when(productService).saveProduct(productRequest);

//         ResultActions response = mockMvc.perform(post("/api/products")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(productRequest)));

//         response.andExpect(MockMvcResultMatchers.status().isCreated());

//     }

//     @Test
//     public void saveProduct_NullField_test() throws Exception {
//         productRequest.setName(null);
//         doNothing().when(productService).saveProduct(productRequest);

//         ResultActions response = mockMvc.perform(post("/api/products")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(productRequest)));

//         response.andExpect(MockMvcResultMatchers.status().isBadRequest())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.notNullValue()));
//     }

//     @Test
//     public void deleteProduct_test() throws Exception {
//         doNothing().when(productService).deleteProduct(1);

//         ResultActions response = mockMvc.perform(delete("/api/products/1"));
//         response.andExpect(MockMvcResultMatchers.status().isNoContent());
//     }

//     @Test
//     public void getAllProducts_test() throws Exception {
//         when(productService.getAllProducts()).thenReturn(Collections.singletonList(productResponse));

//         ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/products"));

//         MvcResult result = response.andExpect(MockMvcResultMatchers.status().isOk())
//                 .andReturn();

//         List<ProductResponse> productList = objectMapper.readValue(result.getResponse().getContentAsString(),
//                 new TypeReference<List<ProductResponse>>() {});

//         Assertions.assertEquals(1, productList.size());
//         Assertions.assertEquals(productList.get(0).getName(), productResponse.getName());
//     }

//     @Test
//     public void getAllProductsEmpty_test() throws Exception {
//         when(productService.getAllProducts()).thenReturn(Collections.emptyList());

//         ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/products"));

//         MvcResult result = response.andExpect(MockMvcResultMatchers.status().isOk())
//                 .andReturn();

//         List<ProductResponse> productList = objectMapper.readValue(result.getResponse().getContentAsString(),
//                 new TypeReference<List<ProductResponse>>() {});
//         Assertions.assertEquals(0, productList.size());
//     }
// }
