package com.FullStack.FullStackJavaSprintBoot.client;

import com.FullStack.FullStackJavaSprintBoot.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public ProductDto getProductById(Long id) {
        return restTemplate.getForObject("http://localhost:8081/api/products/" + id, ProductDto.class);
    }
}

