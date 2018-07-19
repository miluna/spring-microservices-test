package com.mla.productsserver.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ms-gestion-usuario")
public interface IUserFeign {
}
