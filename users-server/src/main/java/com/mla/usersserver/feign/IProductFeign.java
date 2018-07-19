package com.mla.usersserver.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ms-products")
public interface IProductFeign {
}
