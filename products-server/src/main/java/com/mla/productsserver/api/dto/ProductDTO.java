package com.mla.productsserver.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {

    private Long id;
    private Long idUser;
    private String name;
    private String code;
}
