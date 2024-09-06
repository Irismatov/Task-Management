package uz.pdp.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.taskmanagement.entity.SprintEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface SprintRepository extends JpaRepository<SprintEntity, UUID> {
    boolean existsByStart(LocalDateTime startDate);
}
