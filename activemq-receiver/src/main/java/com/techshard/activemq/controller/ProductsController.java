package com.techshard.activemq.controller;

import com.techshard.activemq.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Scope("singleton")
@RestController
@RequestMapping("/products")
@EnableJms
public class ProductsController {
    private final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private Product product;

    @PostConstruct
    public void init(){
        product = new Product();
        product.setId("T");
        product.setName("Tarjeta");
    }

    @GetMapping("{product-id}")
    public ResponseEntity<String> getProduct(@PathVariable("product-id") final String productId){
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @JmsListener(destination = "test-queue")
    public void listener(String cardId){
        logger.info("Card received {} ", cardId);
        product.incrementTotalContracted();
        logger.info("Product details {} ", product);
    }

}
