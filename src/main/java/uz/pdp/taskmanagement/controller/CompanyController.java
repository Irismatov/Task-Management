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
    public ResponseEntity<Void> save(@RequestBody CompanyCreateDTO dto) {
        companyService.save(dto);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    private List<CompanyInfoView> getAllCompany() {
        return companyService.findAll();
    }

    @PutMapping("/{id}/block")
    private ResponseEntity<String> block(@PathVariable("id") UUID id) {
        companyService.blockAndUnBlock(id, true);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/unblock")
    private ResponseEntity<String> unblock(@PathVariable("id") UUID id) {
        companyService.blockAndUnBlock(id, false);
        return ResponseEntity.ok().build();
    }


}
