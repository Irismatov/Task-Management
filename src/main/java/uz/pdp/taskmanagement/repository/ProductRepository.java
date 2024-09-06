package uz.pdp.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.taskmanagement.entity.ProductEntity;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
