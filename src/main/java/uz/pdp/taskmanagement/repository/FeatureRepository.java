package uz.pdp.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.taskmanagement.entity.FeatureEntity;

import java.util.UUID;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, UUID> {
}
