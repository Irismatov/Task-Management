package uz.pdp.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.controller.exception.BaseException;
import uz.pdp.taskmanagement.domain.request.CompanyCreateDTO;
import uz.pdp.taskmanagement.domain.view.CompanyInfoView;
import uz.pdp.taskmanagement.entity.CompanyEntity;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.repository.CompanyRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyRepository companyRepository;

    public void save(CompanyCreateDTO dto){
        UserEntity user = userService.findById(dto.getCeoId());
        CompanyEntity company = CompanyEntity.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .establishmentDate(dto.getEstablishmentDate())
                .isBlocked(false)
                .CEO(user)
                .build();

        userService.updateCeo(company, user);
        try{
            companyRepository.save(company);
        }
        catch (DataIntegrityViolationException e){
            throw new BaseException("Company already exists");
        }
    }



    public CompanyEntity findById(UUID id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new BaseException("Company not found"));
    }


    public List<CompanyInfoView> findAll(){
        return companyRepository.findAllCompanyInfo();
    }


    public void blockAndUnBlock(UUID id, boolean block){
        CompanyEntity company = findById(id);
        company.setBlocked(block);
        companyRepository.save(company);
    }
}
