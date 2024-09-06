package uz.pdp.taskmanagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.controller.exception.BaseException;
import uz.pdp.taskmanagement.domain.request.ProductRequest;
import uz.pdp.taskmanagement.domain.response.ProductResponse;
import uz.pdp.taskmanagement.entity.ProductEntity;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    public ProductResponse saveProduct(ProductRequest productRequest) {
        ProductEntity product = modelMapper.map(productRequest, ProductEntity.class);

        if (Objects.nonNull(productRequest.getOwner())) {
            UserEntity owner = userService.findById(productRequest.getOwner());
            product.setOwner(owner);
            productRepository.save(product);
        } else {
            productRepository.save(product);
        }
        return modelMapper.map(product, ProductResponse.class);
    }


    public List<ProductResponse> getAllProducts() {
            return modelMapper.map(productRepository.findAll(),
                    new TypeReference<List<ProductResponse>>() {
                    }.getType());
    }

    public List<ProductResponse> getAllProductsWithoutOwner() {
        return modelMapper.map(productRepository.findAllByOwnerIsNull(),
                new TypeReference<List<ProductResponse>>() {
        }.getType());
    }

    public void assignOwnerToProduct(UUID productId, UUID ownerId) {
        ProductEntity product = findById(productId);
        UserEntity owner = userService.findById(ownerId);

        product.setOwner(owner);
        productRepository.save(product);
    }


    public ProductEntity findById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new BaseException("Product not found"));
    }


}
