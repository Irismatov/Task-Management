package uz.pdp.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import uz.pdp.taskmanagement.entity.ProductEntity;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findAllByOwnerIsNull();

}
