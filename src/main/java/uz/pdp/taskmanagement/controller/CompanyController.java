package uz.pdp.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.domain.request.CompanyCreateDTO;
import uz.pdp.taskmanagement.domain.view.CompanyInfoView;
import uz.pdp.taskmanagement.service.CompanyService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Void> save(CompanyCreateDTO dto){
        companyService.save(dto);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    private List<CompanyInfoView> getAllCompany() {
        return companyService.findAll();
    }

    @PutMapping("/id")
    private ResponseEntity<Void> blockAndUnBlock(@RequestParam("id") UUID id,@RequestBody boolean block) {
        companyService.blockAndUnBlock(id, block);
        return ResponseEntity.ok().build();
    }


}
