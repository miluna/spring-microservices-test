package com.mla.usersserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-products")
public interface IProductFeign {

    @RequestMapping(path = "/product/{id}", method = RequestMethod.DELETE)
    ResponseEntity<String> deleteProductByUserId(@PathVariable Long id);
}
