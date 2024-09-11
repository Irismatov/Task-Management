package uz.pdp.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.entity.enumerators.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByRole(UserRole role);

    List<UserEntity> getAllByRoleAndTeamIsNull(UserRole role);

}
