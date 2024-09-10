package uz.pdp.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.taskmanagement.domain.view.ProductView;
import uz.pdp.taskmanagement.entity.ProductEntity;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findAllByOwnerIsNull();


    @Query("""
        select new uz.pdp.taskmanagement.domain.view.ProductInfoView(
        p.id, p.name, p.gitRepo, p.description, p.owner.username, p.team.name)
        from products p
""")
    List<ProductView> getProduct();
}