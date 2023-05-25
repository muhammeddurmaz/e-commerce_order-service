package com.durmaz.orderservice.client;

import com.durmaz.orderservice.service.dto.ProductDTO;
import com.durmaz.orderservice.service.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "product-service" , path = "/api")
public interface ProductServiceClient {


    @GetMapping("/products/{id}")
    ResponseEntity<ResponseDTO<ProductDTO>> getProductById(@PathVariable(name = "id") Long id);

    @GetMapping("/products")
    ResponseEntity<List<ResponseDTO<ProductDTO>>> getAllProductByIds(@RequestBody List<Long> ids);
}
