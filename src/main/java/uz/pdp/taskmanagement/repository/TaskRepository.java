package uz.pdp.taskmanagement.repository;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.taskmanagement.entity.TaskEntity;

import java.util.UUID;

@Registered
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
}
