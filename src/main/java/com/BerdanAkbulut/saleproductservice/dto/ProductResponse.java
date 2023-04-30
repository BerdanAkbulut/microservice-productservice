package com.BerdanAkbulut.saleproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse implements Serializable {
    private long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
}
