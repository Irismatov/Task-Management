package uz.pdp.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.taskmanagement.domain.view.CompanyInfoView;
import uz.pdp.taskmanagement.entity.CompanyEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {



    @Query("select new uz.pdp.taskmanagement.domain.view.CompanyInfoViewImpl (c.id, c.name, c.address, c.establishmentDate, c.isBlocked, c.CEO.username)" +
            " from companies c")
    List<CompanyInfoView> findAllCompanyInfo();



}
