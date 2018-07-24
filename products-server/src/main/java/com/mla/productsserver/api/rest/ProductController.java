package com.mla.productsserver.api.rest;

import com.mla.productsserver.api.dto.ProductDTO;
import com.mla.productsserver.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public ResponseEntity<List> getAllProducts() {
        List<ProductDTO> list = productService.getAllProducts();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/products/{idUser}", method = RequestMethod.GET)
    public ResponseEntity<List> getProductsByUser(@PathVariable Long idUser){
        List<ProductDTO> list = productService.getProductsByUser(idUser);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/products/{idUser}", method = RequestMethod.POST)
    public ResponseEntity<Void> createProductForUser(
            @RequestBody ProductDTO product,
            @PathVariable Long idUser) {
        ProductDTO productDTO = productService.createProductForUser(product, idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{idUser}", method = RequestMethod.DELETE)
    public ResponseEntity<List> deleteProductsForUser (@PathVariable Long idUser){
        List<ProductDTO> erased = productService.deleteProductsForUser(idUser);
        return ResponseEntity.ok(erased);
    }
}
