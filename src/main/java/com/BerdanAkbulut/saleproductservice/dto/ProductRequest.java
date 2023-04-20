package com.BerdanAkbulut.saleproductservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String brand;
    @NotNull
    private BigDecimal price;
}
