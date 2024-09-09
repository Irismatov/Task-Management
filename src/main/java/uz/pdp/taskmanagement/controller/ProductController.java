package uz.pdp.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.taskmanagement.service.ProductService;

@RestController
@RequestMapping("/products")

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.domain.request.ProductRequest;
import uz.pdp.taskmanagement.domain.view.ProductInfoView;
import uz.pdp.taskmanagement.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    private ResponseEntity<Void> saveProducts(@RequestBody ProductRequest productRequest) {
        productService.saveProduct(productRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    private List<ProductInfoView> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("without-owner")
    private List<ProductInfoView> getProductsWithoutOwner() {
        return productService.getAllProductsWithoutOwner();
    }

    @PostMapping("/assign/{productId}/{ownerId}")
    private ResponseEntity<Void> assignOwner(@PathVariable("productId") UUID productId, @PathVariable("ownerId") UUID ownerId) {
        productService.assignOwnerToProduct(productId, ownerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateProduct(@PathVariable("id") UUID id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
