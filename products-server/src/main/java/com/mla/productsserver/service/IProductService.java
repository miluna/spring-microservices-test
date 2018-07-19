package com.mla.productsserver.service;

import com.mla.productsserver.api.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getProductsByUser(Long idUser);

    ProductDTO createProductForUser(ProductDTO productDTO, Long idUser);

    List<ProductDTO> deleteProductsForUser(Long idUser);

}
