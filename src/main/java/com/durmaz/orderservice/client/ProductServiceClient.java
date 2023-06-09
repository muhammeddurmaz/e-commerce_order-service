package com.durmaz.orderservice.client;

import com.durmaz.orderservice.service.dto.ProductDTO;
import com.durmaz.orderservice.service.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service" , path = "/api")
public interface ProductServiceClient {


    @GetMapping("/products/{id}")
    ResponseEntity<ResponseDTO<ProductDTO>> getProductById(@PathVariable(name = "id") Long id);

    @GetMapping("/products")
    ResponseEntity<ResponseDTO<List<ProductDTO>>> getAllProductByIds(@RequestBody List<Long> ids);

    @GetMapping("/product-price/{id}")
    ResponseEntity<ResponseDTO<Double>> getProductPrice(@PathVariable(name = "id") Long id);

    @GetMapping("/product-sumPrice")
    ResponseEntity<ResponseDTO<Double>> getSumProductPrice(@RequestParam List<Long> ids);
}
