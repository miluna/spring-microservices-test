package com.mla.productsserver.service;

import com.mla.productsserver.api.dto.ProductDTO;
import com.mla.productsserver.entities.ProductEntity;
import com.mla.productsserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("ProductService")
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productRepository
                .findAll()
                .forEach(productEntity -> productDTOList.add(new ProductDTO(
                        productEntity.getId(),
                        productEntity.getIdUser(),
                        productEntity.getName(),
                        productEntity.getCode())
                ));

        return productDTOList;
    }

    @Override
    public List<ProductDTO> getProductsByUser(Long idUser) {
        return productRepository.findByIdUser(idUser)
                .stream()
                .map(productEntity -> new ProductDTO(
                        productEntity.getId(),
                        productEntity.getIdUser(),
                        productEntity.getName(),
                        productEntity.getCode()
                )).collect(Collectors.toList());

    }

    @Override
    public ProductDTO createProductForUser(ProductDTO productDTO, Long idUser) {
        ProductEntity productEntity = new ProductEntity(
                null,
                idUser,
                productDTO.getName(),
                productDTO.getCode()
        );
        ProductEntity result = productRepository.save(productEntity);

        return new ProductDTO(
                result.getId(),
                result.getIdUser(),
                result.getName(),
                result.getCode()
        );
    }

    @Override
    public List<ProductDTO> deleteProductsForUser(Long idUser) {
        List<ProductDTO> deleted = new ArrayList<>();
        productRepository.findByIdUser(idUser)
                .forEach(productEntity -> {
                    deleted.add(new ProductDTO(
                            productEntity.getId(),
                            productEntity.getIdUser(),
                            productEntity.getName(),
                            productEntity.getCode()
                    ));
                    productRepository.delete(productEntity);
                });
        return deleted;
    }
}
