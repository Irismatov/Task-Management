package uz.pdp.taskmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.domain.request.FeatureRequest;
import uz.pdp.taskmanagement.domain.response.FeatureResponse;
import uz.pdp.taskmanagement.service.FeatureService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping
    public FeatureResponse saveFeature(@RequestBody FeatureRequest featureRequest) {
        return featureService.createFeature(featureRequest);
    }

    @GetMapping
    public List<FeatureResponse> getAllFeatures() {
        return featureService.getAll();
    }

    @GetMapping("/{featureId}")
    public FeatureResponse getFeatureById(@PathVariable UUID featureId) {
        return featureService.findById(featureId);
    }

    @PutMapping("/{featureId}")
    public FeatureResponse updateFeature(@Valid @RequestBody FeatureRequest featureRequest,
                                         @PathVariable UUID featureId) {
        return featureService.updateFeature(featureRequest, featureId);
    }

    @DeleteMapping("/{featureId}")
    public void deleteFeature(@PathVariable UUID featureId) {
        featureService.deleteFeature(featureId);
    }

}
